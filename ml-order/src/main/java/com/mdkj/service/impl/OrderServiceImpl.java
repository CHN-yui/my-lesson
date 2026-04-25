package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Order;
import com.mdkj.service.OrderService;
import com.mdkj.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【order(订单表)】的数据库操作Service实现
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

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




