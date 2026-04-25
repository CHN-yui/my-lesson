package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.hutool.json.JSONUtil;
import com.mdkj.component.MyRedis;
import com.mdkj.constant.ML;
import com.mdkj.domain.Seckill;
import com.mdkj.domain.SeckillDetail;
import com.mdkj.mapper.SeckillDetailMapper;
import com.mdkj.service.SeckillService;
import com.mdkj.mapper.SeckillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【seckill(秒杀表)】的数据库操作Service实现
*/
@Service
public class SeckillServiceImpl extends ServiceImpl<SeckillMapper, Seckill> implements SeckillService{
    @Autowired
    private SeckillDetailMapper seckillDetailMapper;
    @Autowired
    private MyRedis myRedis;

    @Override
    public List<Seckill> selectAll() {
        LambdaQueryWrapper <Seckill> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Seckill::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Seckill> selectList(Seckill iSeckill) {
        LambdaQueryWrapper <Seckill> lqw = this.lqw(iSeckill);
        lqw.eq(Seckill::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Seckill iSeckill) {
        save(iSeckill);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Seckill> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Seckill entity  = new Seckill();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Seckill iSeckill) {
        updateById(iSeckill);
    }


    @Override
    public IPage<Seckill> pageList(Seckill iSeckill, Integer page, Integer size) {
        Page<Seckill> pag = new Page<>(page,size);
        LambdaQueryWrapper<Seckill> lqw = lqw(iSeckill);
        lqw.eq(Seckill::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Seckill> getExcelData() {
        return selectAll();
    }

    @Override
    public List<Seckill> todayList() {
        Date now = new Date();
        LambdaQueryWrapper<Seckill> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Seckill::getDeleted, 0)
                .le(Seckill::getStartTime, now)
                .ge(Seckill::getEndTime, now)
                .orderByAsc(Seckill::getStartTime);
        return list(lqw);
    }

    @Override
    public Map<String, Object> preheat(Long seckillId) {
        Seckill seckill = getById(seckillId);
        if (seckill == null || seckill.getDeleted() == 1) {
            throw new RuntimeException("秒杀活动不存在");
        }
        LambdaQueryWrapper<SeckillDetail> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SeckillDetail::getFkSeckillId, seckillId)
                .eq(SeckillDetail::getDeleted, 0);
        List<SeckillDetail> details = seckillDetailMapper.selectList(lqw);
        int count = 0;
        for (SeckillDetail detail : details) {
            String infoKey = ML.Redis.SECKILL_COURSE_INFO_PREFIX + detail.getFkCourseId();
            String stockKey = ML.Redis.SECKILL_COURSE_COUNT_PREFIX + detail.getFkCourseId();
            myRedis.set(infoKey, JSONUtil.toJsonStr(detail), 3600L);
            myRedis.set(stockKey, String.valueOf(detail.getSkCount()), 3600L);
            count++;
        }
        Map<String, Object> result = new HashMap<>(3);
        result.put("seckillId", seckillId);
        result.put("preheatCount", count);
        result.put("message", "预热完成");
        return result;
    }

    @Override
    public void startSeckill(Long seckillId) {
        Seckill seckill = new Seckill();
        seckill.setId(seckillId);
        seckill.setStatus(ML.Seckill.STARTED);
        updateById(seckill);
    }

    public LambdaQueryWrapper<Seckill> lqw(Seckill iSeckill){
        LambdaQueryWrapper <Seckill> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iSeckill.getId())){
            lqw.eq(Seckill::getId,iSeckill.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getTitle())){
            lqw.eq(Seckill::getTitle,iSeckill.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getInfo())){
            lqw.eq(Seckill::getInfo,iSeckill.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getStartTime())){
            lqw.eq(Seckill::getStartTime,iSeckill.getStartTime());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getEndTime())){
            lqw.eq(Seckill::getEndTime,iSeckill.getEndTime());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getStatus())){
            lqw.eq(Seckill::getStatus,iSeckill.getStatus());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getVersion())){
            lqw.eq(Seckill::getVersion,iSeckill.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getDeleted())){
            lqw.eq(Seckill::getDeleted,iSeckill.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getCreated())){
            lqw.eq(Seckill::getCreated,iSeckill.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iSeckill.getUpdated())){
            lqw.eq(Seckill::getUpdated,iSeckill.getUpdated());
        };
        return lqw;
    }
}




