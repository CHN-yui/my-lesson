package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.SeckillDetail;
import com.mdkj.service.SeckillDetailService;
import com.mdkj.mapper.SeckillDetailMapper;
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
* 针对表【seckill_detail(秒杀明细表)】的数据库操作Service实现
*/
@Service
public class SeckillDetailServiceImpl extends ServiceImpl<SeckillDetailMapper, SeckillDetail> implements SeckillDetailService{

    @Override
    public List<SeckillDetail> selectAll() {
        LambdaQueryWrapper <SeckillDetail> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SeckillDetail::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<SeckillDetail> selectList(SeckillDetail iSeckillDetail) {
        LambdaQueryWrapper <SeckillDetail> lqw = this.lqw(iSeckillDetail);
        lqw.eq(SeckillDetail::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(SeckillDetail iSeckillDetail) {
        save(iSeckillDetail);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<SeckillDetail> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            SeckillDetail entity  = new SeckillDetail();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(SeckillDetail iSeckillDetail) {
        updateById(iSeckillDetail);
    }


    @Override
    public IPage<SeckillDetail> pageList(SeckillDetail iSeckillDetail, Integer page, Integer size) {
        Page<SeckillDetail> pag = new Page<>(page,size);
        LambdaQueryWrapper<SeckillDetail> lqw = lqw(iSeckillDetail);
        lqw.eq(SeckillDetail::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<SeckillDetail> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<SeckillDetail> lqw(SeckillDetail iSeckillDetail){
        LambdaQueryWrapper <SeckillDetail> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getId())){
            lqw.eq(SeckillDetail::getId,iSeckillDetail.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getFkSeckillId())){
            lqw.eq(SeckillDetail::getFkSeckillId,iSeckillDetail.getFkSeckillId());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getFkCourseId())){
            lqw.eq(SeckillDetail::getFkCourseId,iSeckillDetail.getFkCourseId());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getCourseTitle())){
            lqw.eq(SeckillDetail::getCourseTitle,iSeckillDetail.getCourseTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getCourseCover())){
            lqw.eq(SeckillDetail::getCourseCover,iSeckillDetail.getCourseCover());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getCoursePrice())){
            lqw.eq(SeckillDetail::getCoursePrice,iSeckillDetail.getCoursePrice());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getSkPrice())){
            lqw.eq(SeckillDetail::getSkPrice,iSeckillDetail.getSkPrice());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getSkCount())){
            lqw.eq(SeckillDetail::getSkCount,iSeckillDetail.getSkCount());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getInfo())){
            lqw.eq(SeckillDetail::getInfo,iSeckillDetail.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getVersion())){
            lqw.eq(SeckillDetail::getVersion,iSeckillDetail.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getDeleted())){
            lqw.eq(SeckillDetail::getDeleted,iSeckillDetail.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getCreated())){
            lqw.eq(SeckillDetail::getCreated,iSeckillDetail.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckillDetail.getUpdated())){
            lqw.eq(SeckillDetail::getUpdated,iSeckillDetail.getUpdated());
        };
        return lqw;
    }
}




