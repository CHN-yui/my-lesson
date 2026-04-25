package com.mdkj.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.constant.ML;
import com.mdkj.domain.Cart;
import com.mdkj.domain.Order;
import com.mdkj.domain.OrderDetail;
import com.mdkj.dto.CreateOrderDTO;
import com.mdkj.exception.ServiceException;
import com.mdkj.mapper.CartMapper;
import com.mdkj.mapper.OrderDetailMapper;
import com.mdkj.service.OrderService;
import com.mdkj.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【order(订单表)】的数据库操作Service实现
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired(required = false)
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public List<Order> selectAll() {
        LambdaQueryWrapper <Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Order> selectList(Order iOrder) {
        LambdaQueryWrapper <Order> lqw = this.lqw(iOrder);
        lqw.eq(Order::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Order iOrder) {
        save(iOrder);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Order> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Order entity  = new Order();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Order iOrder) {
        updateById(iOrder);
    }


    @Override
    public IPage<Order> pageList(Order iOrder, Integer page, Integer size) {
        Page<Order> pag = new Page<>(page,size);
        LambdaQueryWrapper<Order> lqw = lqw(iOrder);
        lqw.eq(Order::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Order> getExcelData() {
        return selectAll();
    }

    @Override
    public Map<String, Object> statistics() {
        LambdaQueryWrapper<Order> query = new LambdaQueryWrapper<>();
        query.eq(Order::getDeleted, 0);
        List<Order> orders = list(query);
        long totalOrder = orders.size();
        long unpaidOrder = orders.stream().filter(e -> ML.Order.UNPAID.equals(e.getStatus())).count();
        long paidOrder = orders.stream().filter(e -> ML.Order.PAID.equals(e.getStatus())).count();
        long cancelOrder = orders.stream().filter(e -> ML.Order.CANCEL.equals(e.getStatus())).count();
        BigDecimal totalAmount = orders.stream().map(Order::getTotalAmount).filter(e -> e != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal paidAmount = orders.stream().filter(e -> ML.Order.PAID.equals(e.getStatus()))
                .map(Order::getPayAmount).filter(e -> e != null).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> result = new HashMap<>(8);
        result.put("totalOrder", totalOrder);
        result.put("unpaidOrder", unpaidOrder);
        result.put("paidOrder", paidOrder);
        result.put("cancelOrder", cancelOrder);
        result.put("totalAmount", totalAmount);
        result.put("paidAmount", paidAmount);
        return result;
    }

    @Override
    @Transactional
    public Map<String, Object> createPrepayOrder(CreateOrderDTO dto) {
        if (dto == null || dto.getUserId() == null || CollectionUtils.isEmpty(dto.getCartIds())) {
            throw new ServiceException("创建预支付订单参数不完整");
        }
        LambdaQueryWrapper<Cart> cartQuery = new LambdaQueryWrapper<>();
        cartQuery.eq(Cart::getDeleted, 0)
                .eq(Cart::getFkUserId, dto.getUserId())
                .in(Cart::getId, dto.getCartIds());
        List<Cart> carts = cartMapper.selectList(cartQuery);
        if (CollectionUtils.isEmpty(carts)) {
            throw new ServiceException("购物车数据不存在");
        }
        BigDecimal totalAmount = carts.stream().map(Cart::getCoursePrice).filter(e -> e != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (BigDecimal.ZERO.compareTo(totalAmount) >= 0) {
            throw new ServiceException("订单金额异常");
        }

        Date now = new Date();
        Order order = new Order();
        order.setSn("ORD" + System.currentTimeMillis() + RandomUtil.randomNumbers(6));
        order.setTotalAmount(totalAmount);
        order.setPayAmount(totalAmount);
        order.setPayType(ML.Order.ALI_PAY);
        order.setStatus(ML.Order.UNPAID);
        order.setFkUserId(dto.getUserId());
        order.setUsername(dto.getUsername());
        order.setFkCouponsId(dto.getCouponsId());
        order.setInfo(dto.getInfo());
        order.setVersion(1L);
        order.setDeleted(0);
        order.setCreated(now);
        order.setUpdated(now);
        save(order);

        for (Cart cart : carts) {
            OrderDetail detail = new OrderDetail();
            detail.setFkOrderId(order.getId());
            detail.setFkCourseId(cart.getFkCourseId());
            detail.setCourseTitle(cart.getCourseTitle());
            detail.setCourseCover(cart.getCourseCover());
            detail.setCoursePrice(cart.getCoursePrice());
            detail.setVersion(1L);
            detail.setDeleted(0);
            detail.setCreated(now);
            detail.setUpdated(now);
            orderDetailMapper.insert(detail);

            Cart updateCart = new Cart();
            updateCart.setId(cart.getId());
            updateCart.setDeleted(1);
            updateCart.setUpdated(now);
            cartMapper.updateById(updateCart);
        }

        if (rocketMQTemplate != null) {
            try {
                rocketMQTemplate.syncSend("order-timeout-topic",
                        MessageBuilder.withPayload(String.valueOf(order.getId())).build(),
                        3000, 16);
            } catch (Exception ignore) {
                // MQ不可用时不影响下单主流程
            }
        }

        Map<String, Object> result = new HashMap<>(6);
        result.put("orderId", order.getId());
        result.put("sn", order.getSn());
        result.put("amount", order.getPayAmount());
        result.put("status", order.getStatus());
        return result;
    }

    @Override
    public Map<String, Object> getPayQrcode(Long orderId) {
        if (orderId == null) {
            throw new ServiceException("订单ID不能为空");
        }
        Order order = getById(orderId);
        if (order == null || order.getDeleted() != null && order.getDeleted() == 1) {
            throw new ServiceException("订单不存在");
        }
        AlipayTradePrecreateResponse response;
        try {
            response = Factory.Payment.FaceToFace()
                    .preCreate("MyLesson课程订单-" + order.getSn(), order.getSn(), order.getPayAmount().toPlainString());
        } catch (Exception e) {
            throw new ServiceException("调用支付宝预下单失败: " + e.getMessage());
        }
        if (!ResponseChecker.success(response)) {
            throw new ServiceException("支付宝预下单失败: " + response.msg + " " + response.subMsg);
        }
        String payUrl = response.qrCode;
        QrConfig QrConfig = new QrConfig();
        String qrCodeBase64 = QrCodeUtil.generateAsBase64(payUrl, QrConfig,  "png");
        Map<String, Object> result = new HashMap<>(6);
        result.put("orderId", order.getId());
        result.put("sn", order.getSn());
        result.put("status", order.getStatus());
        result.put("payUrl", payUrl);
        result.put("qrcodeBase64", qrCodeBase64);
        return result;
    }

    @Override
    public Map<String, Object> queryOrderStatus(Long orderId) {
        if (orderId == null) {
            throw new ServiceException("订单ID不能为空");
        }
        Order order = getById(orderId);
        if (order == null || order.getDeleted() != null && order.getDeleted() == 1) {
            throw new ServiceException("订单不存在");
        }
        Map<String, Object> result = new HashMap<>(6);
        result.put("orderId", order.getId());
        result.put("sn", order.getSn());
        result.put("statusCode", order.getStatus());
        result.put("statusName", ML.Order.statusFormat(order.getStatus()));
        result.put("payTypeCode", order.getPayType());
        result.put("payTypeName", ML.Order.payTypeFormat(order.getPayType()));
        return result;
    }

    @Override
    @Transactional
    public void handlePayCallback(Long orderId, String tradeNo, Integer payType, String callbackMsg) {
        if (orderId == null) {
            throw new ServiceException("订单ID不能为空");
        }
        Order order = getById(orderId);
        if (order == null || order.getDeleted() != null && order.getDeleted() == 1) {
            throw new ServiceException("订单不存在");
        }
        if (ML.Order.PAID.equals(order.getStatus())) {
            return;
        }
        Order updateOrder = new Order();
        updateOrder.setId(orderId);
        updateOrder.setStatus(ML.Order.PAID);
        updateOrder.setPayType(payType == null ? ML.Order.ALI_PAY : payType);
        updateOrder.setInfo(callbackMsg == null ? ("支付成功，tradeNo=" + tradeNo) : callbackMsg);
        updateOrder.setUpdated(new Date());
        updateById(updateOrder);

        if (rocketMQTemplate != null) {
            try {
                rocketMQTemplate.convertAndSend("order-payment-topic", String.valueOf(orderId));
            } catch (Exception ignore) {
                // MQ不可用时不影响回调主流程
            }
        }
    }

    @Override
    @Transactional
    public void handleAliPayCallback(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            throw new ServiceException("支付宝回调参数为空");
        }
        boolean signOk;
        try {
            signOk = Factory.Payment.Common().verifyNotify(params);
        } catch (Exception e) {
            throw new ServiceException("支付宝回调验签异常: " + e.getMessage());
        }
        if (!signOk) {
            throw new ServiceException("支付宝回调验签失败");
        }
        String outTradeNo = params.get("out_trade_no");
        String tradeStatus = params.get("trade_status");
        String tradeNo = params.get("trade_no");
        if (StrUtil.isBlank(outTradeNo)) {
            throw new ServiceException("回调缺少商户订单号");
        }
        if (!StrUtil.equalsAny(tradeStatus, "TRADE_SUCCESS", "TRADE_FINISHED")) {
            return;
        }
        LambdaQueryWrapper<Order> query = new LambdaQueryWrapper<>();
        query.eq(Order::getSn, outTradeNo).eq(Order::getDeleted, 0).last("limit 1");
        Order order = getOne(query);
        if (order == null) {
            throw new ServiceException("订单不存在: " + outTradeNo);
        }
        if (ML.Order.PAID.equals(order.getStatus())) {
            return;
        }
        Order updateOrder = new Order();
        updateOrder.setId(order.getId());
        updateOrder.setStatus(ML.Order.PAID);
        updateOrder.setPayType(ML.Order.ALI_PAY);
        updateOrder.setInfo("支付宝支付成功，tradeNo=" + tradeNo);
        updateOrder.setUpdated(new Date());
        updateById(updateOrder);
    }

    public LambdaQueryWrapper<Order> lqw(Order iOrder){
        LambdaQueryWrapper <Order> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iOrder.getId())){
            lqw.eq(Order::getId,iOrder.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getSn())){
            lqw.eq(Order::getSn,iOrder.getSn());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getTotalAmount())){
            lqw.eq(Order::getTotalAmount,iOrder.getTotalAmount());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getPayAmount())){
            lqw.eq(Order::getPayAmount,iOrder.getPayAmount());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getPayType())){
            lqw.eq(Order::getPayType,iOrder.getPayType());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getStatus())){
            lqw.eq(Order::getStatus,iOrder.getStatus());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getFkUserId())){
            lqw.eq(Order::getFkUserId,iOrder.getFkUserId());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getUsername())){
            lqw.eq(Order::getUsername,iOrder.getUsername());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getFkCouponsId())){
            lqw.eq(Order::getFkCouponsId,iOrder.getFkCouponsId());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getInfo())){
            lqw.eq(Order::getInfo,iOrder.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getVersion())){
            lqw.eq(Order::getVersion,iOrder.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getDeleted())){
            lqw.eq(Order::getDeleted,iOrder.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getCreated())){
            lqw.eq(Order::getCreated,iOrder.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iOrder.getUpdated())){
            lqw.eq(Order::getUpdated,iOrder.getUpdated());
        };
        return lqw;
    }
}




