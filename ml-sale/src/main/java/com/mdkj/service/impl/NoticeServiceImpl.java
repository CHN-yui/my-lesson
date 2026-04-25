package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.component.MyRedis;
import com.mdkj.constant.ML;
import com.mdkj.domain.Notice;
import com.mdkj.service.NoticeService;
import com.mdkj.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【notice(通知表)】的数据库操作Service实现
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{
    @Autowired
    private MyRedis myRedis;

    @Override
    public List<Notice> selectAll() {
        LambdaQueryWrapper <Notice> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Notice::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Notice> selectList(Notice iNotice) {
        LambdaQueryWrapper <Notice> lqw = this.lqw(iNotice);
        lqw.eq(Notice::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Notice iNotice) {
        save(iNotice);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Notice> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Notice entity  = new Notice();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Notice iNotice) {
        updateById(iNotice);
    }


    @Override
    public IPage<Notice> pageList(Notice iNotice, Integer page, Integer size) {
        Page<Notice> pag = new Page<>(page,size);
        LambdaQueryWrapper<Notice> lqw = lqw(iNotice);
        lqw.eq(Notice::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Notice> getExcelData() {
        return selectAll();
    }

    @Override
    public List<Notice> topList(Integer topN) {
        int limit = (topN == null || topN < 1) ? 5 : topN;
        String cacheKey = ML.Redis.TOP_NOTICE_KEY_PREFIX + limit;
        if (myRedis.exists(cacheKey)) {
            List<String> ids = myRedis.lRange(cacheKey, 0, -1);
            if (ids != null && !ids.isEmpty()) {
                List<Notice> result = new ArrayList<>();
                for (String id : ids) {
                    Notice notice = getById(Long.valueOf(id));
                    if (notice != null && notice.getDeleted() != null && notice.getDeleted() == 0) {
                        result.add(notice);
                    }
                }
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }

        LambdaQueryWrapper<Notice> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Notice::getDeleted, 0)
                .orderByAsc(Notice::getIdx)
                .orderByDesc(Notice::getId)
                .last("limit " + limit);
        List<Notice> topList = list(lqw);
        myRedis.del(cacheKey);
        for (int i = topList.size() - 1; i >= 0; i--) {
            myRedis.lPush(cacheKey, String.valueOf(topList.get(i).getId()));
        }
        return topList;
    }

    public LambdaQueryWrapper<Notice> lqw(Notice iNotice){
        LambdaQueryWrapper <Notice> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iNotice.getId())){
            lqw.eq(Notice::getId,iNotice.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iNotice.getContent())){
            lqw.eq(Notice::getContent,iNotice.getContent());
        };
        if(NotNullCheckUtil.checkNotNull(iNotice.getIdx())){
            lqw.eq(Notice::getIdx,iNotice.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iNotice.getVersion())){
            lqw.eq(Notice::getVersion,iNotice.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iNotice.getDeleted())){
            lqw.eq(Notice::getDeleted,iNotice.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iNotice.getCreated())){
            lqw.eq(Notice::getCreated,iNotice.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iNotice.getUpdated())){
            lqw.eq(Notice::getUpdated,iNotice.getUpdated());
        };
        return lqw;
    }
}




