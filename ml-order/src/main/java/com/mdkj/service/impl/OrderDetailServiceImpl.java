package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.OrderDetail;
import com.mdkj.service.OrderDetailService;
import com.mdkj.mapper.OrderDetailMapper;
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
* 针对表【order_detail(订单明细表)】的数据库操作Service实现
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService{

    @Override
    public List<OrderDetail> selectAll() {
        LambdaQueryWrapper <OrderDetail> lqw = new LambdaQueryWrapper<>();
        lqw.eq(OrderDetail::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<OrderDetail> selectList(OrderDetail iOrderDetail) {
        LambdaQueryWrapper <OrderDetail> lqw = this.lqw(iOrderDetail);
        lqw.eq(OrderDetail::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(OrderDetail iOrderDetail) {
        save(iOrderDetail);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            OrderDetail entity  = new OrderDetail();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(OrderDetail iOrderDetail) {
        updateById(iOrderDetail);
    }


    @Override
    public IPage<OrderDetail> pageList(OrderDetail iOrderDetail, Integer page, Integer size) {
        Page<OrderDetail> pag = new Page<>(page,size);
        LambdaQueryWrapper<OrderDetail> lqw = lqw(iOrderDetail);
        lqw.eq(OrderDetail::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<OrderDetail> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<OrderDetail> lqw(OrderDetail iOrderDetail){
        LambdaQueryWrapper <OrderDetail> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getId())){
            lqw.eq(OrderDetail::getId,iOrderDetail.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getFkOrderId())){
            lqw.eq(OrderDetail::getFkOrderId,iOrderDetail.getFkOrderId());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getFkCourseId())){
            lqw.eq(OrderDetail::getFkCourseId,iOrderDetail.getFkCourseId());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getCourseTitle())){
            lqw.eq(OrderDetail::getCourseTitle,iOrderDetail.getCourseTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getCourseCover())){
            lqw.eq(OrderDetail::getCourseCover,iOrderDetail.getCourseCover());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getCoursePrice())){
            lqw.eq(OrderDetail::getCoursePrice,iOrderDetail.getCoursePrice());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getVersion())){
            lqw.eq(OrderDetail::getVersion,iOrderDetail.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getDeleted())){
            lqw.eq(OrderDetail::getDeleted,iOrderDetail.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getCreated())){
            lqw.eq(OrderDetail::getCreated,iOrderDetail.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iOrderDetail.getUpdated())){
            lqw.eq(OrderDetail::getUpdated,iOrderDetail.getUpdated());
        };
        return lqw;
    }
}




