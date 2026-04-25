package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Season;
import com.mdkj.service.SeasonService;
import com.mdkj.mapper.SeasonMapper;
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
* 针对表【season(季次表)】的数据库操作Service实现
*/
@Service
public class SeasonServiceImpl extends ServiceImpl<SeasonMapper, Season> implements SeasonService{

    @Override
    public List<Season> selectAll() {
        LambdaQueryWrapper <Season> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Season::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Season> selectList(Season iSeason) {
        LambdaQueryWrapper <Season> lqw = this.lqw(iSeason);
        lqw.eq(Season::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Season iSeason) {
        save(iSeason);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Season> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Season entity  = new Season();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Season iSeason) {
        updateById(iSeason);
    }


    @Override
    public IPage<Season> pageList(Season iSeason, Integer page, Integer size) {
        Page<Season> pag = new Page<>(page,size);
        LambdaQueryWrapper<Season> lqw = lqw(iSeason);
        lqw.eq(Season::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Season> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<Season> lqw(Season iSeason){
        LambdaQueryWrapper <Season> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iSeason.getId())){
            lqw.eq(Season::getId,iSeason.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getTitle())){
            lqw.eq(Season::getTitle,iSeason.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getInfo())){
            lqw.eq(Season::getInfo,iSeason.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getFkCourseId())){
            lqw.eq(Season::getFkCourseId,iSeason.getFkCourseId());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getIdx())){
            lqw.eq(Season::getIdx,iSeason.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getVersion())){
            lqw.eq(Season::getVersion,iSeason.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getDeleted())){
            lqw.eq(Season::getDeleted,iSeason.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getCreated())){
            lqw.eq(Season::getCreated,iSeason.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iSeason.getUpdated())){
            lqw.eq(Season::getUpdated,iSeason.getUpdated());
        };
        return lqw;
    }
}




