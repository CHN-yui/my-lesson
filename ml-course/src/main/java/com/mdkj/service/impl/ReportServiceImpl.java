package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Report;
import com.mdkj.service.ReportService;
import com.mdkj.mapper.ReportMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【report(举报表)】的数据库操作Service实现
*/
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService{

    @Override
    public List<Report> selectAll() {
        LambdaQueryWrapper <Report> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Report::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Report> selectList(Report iReport) {
        LambdaQueryWrapper <Report> lqw = this.lqw(iReport);
        lqw.eq(Report::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Report iReport) {
        save(iReport);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Report> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Report entity  = new Report();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Report iReport) {
        updateById(iReport);
    }


    @Override
    public IPage<Report> pageList(Report iReport, Integer page, Integer size) {
        Page<Report> pag = new Page<>(page,size);
        LambdaQueryWrapper<Report> lqw = lqw(iReport);
        lqw.eq(Report::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Report> getExcelData() {
        return selectAll();
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        LambdaQueryWrapper<Report> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Report::getFkUserId, userId).eq(Report::getDeleted, 0);
        List<Report> reports = list(lqw);
        ArrayList<Report> updateList = new ArrayList<>();
        for (Report report : reports) {
            Report entity = new Report();
            entity.setId(report.getId());
            entity.setDeleted(1);
            updateList.add(entity);
        }
        if (!updateList.isEmpty()) {
            updateBatchById(updateList);
        }
    }

    @Override
    @Transactional
    public void deleteByUserIds(String userIds) {
        for (String userId : userIds.split(",")) {
            deleteByUserId(Long.valueOf(userId));
        }
    }

    public LambdaQueryWrapper<Report> lqw(Report iReport){
        LambdaQueryWrapper <Report> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iReport.getId())){
            lqw.eq(Report::getId,iReport.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getFkEpisodeId())){
            lqw.eq(Report::getFkEpisodeId,iReport.getFkEpisodeId());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getFkUserId())){
            lqw.eq(Report::getFkUserId,iReport.getFkUserId());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getNickname())){
            lqw.eq(Report::getNickname,iReport.getNickname());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getContent())){
            lqw.eq(Report::getContent,iReport.getContent());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getVersion())){
            lqw.eq(Report::getVersion,iReport.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getDeleted())){
            lqw.eq(Report::getDeleted,iReport.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getCreated())){
            lqw.eq(Report::getCreated,iReport.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iReport.getUpdated())){
            lqw.eq(Report::getUpdated,iReport.getUpdated());
        };
        return lqw;
    }
}




