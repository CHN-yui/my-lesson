package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Follow;
import com.mdkj.service.FollowService;
import com.mdkj.mapper.FollowMapper;
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
* 针对表【follow(收藏表)】的数据库操作Service实现
*/
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService{

    @Override
    public List<Follow> selectAll() {
        LambdaQueryWrapper <Follow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Follow::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Follow> selectList(Follow iFollow) {
        LambdaQueryWrapper <Follow> lqw = this.lqw(iFollow);
        lqw.eq(Follow::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Follow iFollow) {
        save(iFollow);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Follow> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Follow entity  = new Follow();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Follow iFollow) {
        updateById(iFollow);
    }


    @Override
    public IPage<Follow> pageList(Follow iFollow, Integer page, Integer size) {
        Page<Follow> pag = new Page<>(page,size);
        LambdaQueryWrapper<Follow> lqw = lqw(iFollow);
        lqw.eq(Follow::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Follow> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<Follow> lqw(Follow iFollow){
        LambdaQueryWrapper <Follow> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iFollow.getId())){
            lqw.eq(Follow::getId,iFollow.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getFkEpisodeId())){
            lqw.eq(Follow::getFkEpisodeId,iFollow.getFkEpisodeId());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getFkUserId())){
            lqw.eq(Follow::getFkUserId,iFollow.getFkUserId());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getNickname())){
            lqw.eq(Follow::getNickname,iFollow.getNickname());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getVersion())){
            lqw.eq(Follow::getVersion,iFollow.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getDeleted())){
            lqw.eq(Follow::getDeleted,iFollow.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getCreated())){
            lqw.eq(Follow::getCreated,iFollow.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iFollow.getUpdated())){
            lqw.eq(Follow::getUpdated,iFollow.getUpdated());
        };
        return lqw;
    }
}




