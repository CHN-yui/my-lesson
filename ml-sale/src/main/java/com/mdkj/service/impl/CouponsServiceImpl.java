package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Coupons;
import com.mdkj.service.CouponsService;
import com.mdkj.mapper.CouponsMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【coupons(优惠卷表)】的数据库操作Service实现
*/
@Service
public class CouponsServiceImpl extends ServiceImpl<CouponsMapper, Coupons> implements CouponsService{

    @Override
    public List<Coupons> selectAll() {
        LambdaQueryWrapper <Coupons> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Coupons::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Coupons> selectList(Coupons iCoupons) {
        LambdaQueryWrapper <Coupons> lqw = this.lqw(iCoupons);
        lqw.eq(Coupons::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Coupons iCoupons) {
        save(iCoupons);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Coupons> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Coupons entity  = new Coupons();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Coupons iCoupons) {
        updateById(iCoupons);
    }


    @Override
    public IPage<Coupons> pageList(Coupons iCoupons, Integer page, Integer size) {
        Page<Coupons> pag = new Page<>(page,size);
        LambdaQueryWrapper<Coupons> lqw = lqw(iCoupons);
        lqw.eq(Coupons::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Coupons> getExcelData() {
        return selectAll();
    }

    @Override
    public Coupons queryByCode(String code) {
        LambdaQueryWrapper<Coupons> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Coupons::getCode, code)
                .eq(Coupons::getDeleted, 0)
                .last("limit 1");
        return getOne(lqw);
    }

    public LambdaQueryWrapper<Coupons> lqw(Coupons iCoupons){
        LambdaQueryWrapper <Coupons> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iCoupons.getId())){
            lqw.eq(Coupons::getId,iCoupons.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getCode())){
            lqw.eq(Coupons::getCode,iCoupons.getCode());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getTitle())){
            lqw.eq(Coupons::getTitle,iCoupons.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getCpPrice())){
            lqw.eq(Coupons::getCpPrice,iCoupons.getCpPrice());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getInfo())){
            lqw.eq(Coupons::getInfo,iCoupons.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getStartTime())){
            lqw.eq(Coupons::getStartTime,iCoupons.getStartTime());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getEndTime())){
            lqw.eq(Coupons::getEndTime,iCoupons.getEndTime());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getVersion())){
            lqw.eq(Coupons::getVersion,iCoupons.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getDeleted())){
            lqw.eq(Coupons::getDeleted,iCoupons.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getCreated())){
            lqw.eq(Coupons::getCreated,iCoupons.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iCoupons.getUpdated())){
            lqw.eq(Coupons::getUpdated,iCoupons.getUpdated());
        };
        return lqw;
    }
}




