package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Comment;
import com.mdkj.service.CommentService;
import com.mdkj.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【comment(评论表)】的数据库操作Service实现
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Override
    public List<Comment> selectAll() {
        LambdaQueryWrapper <Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Comment> selectList(Comment iComment) {
        LambdaQueryWrapper <Comment> lqw = this.lqw(iComment);
        lqw.eq(Comment::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Comment iComment) {
        save(iComment);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Comment> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Comment entity  = new Comment();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Comment iComment) {
        updateById(iComment);
    }


    @Override
    public IPage<Comment> pageList(Comment iComment, Integer page, Integer size) {
        Page<Comment> pag = new Page<>(page,size);
        LambdaQueryWrapper<Comment> lqw = lqw(iComment);
        lqw.eq(Comment::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Comment> getExcelData() {
        return selectAll();
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getFkUserId, userId).eq(Comment::getDeleted, 0);
        List<Comment> comments = list(lqw);
        ArrayList<Comment> updateList = new ArrayList<>();
        for (Comment comment : comments) {
            Comment entity = new Comment();
            entity.setId(comment.getId());
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

    public LambdaQueryWrapper<Comment> lqw(Comment iComment){
        LambdaQueryWrapper <Comment> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iComment.getId())){
            lqw.eq(Comment::getId,iComment.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getFkEpisodeId())){
            lqw.eq(Comment::getFkEpisodeId,iComment.getFkEpisodeId());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getFkUserId())){
            lqw.eq(Comment::getFkUserId,iComment.getFkUserId());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getNickname())){
            lqw.eq(Comment::getNickname,iComment.getNickname());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getAvatar())){
            lqw.eq(Comment::getAvatar,iComment.getAvatar());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getProvince())){
            lqw.eq(Comment::getProvince,iComment.getProvince());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getPid())){
            lqw.eq(Comment::getPid,iComment.getPid());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getContent())){
            lqw.eq(Comment::getContent,iComment.getContent());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getVersion())){
            lqw.eq(Comment::getVersion,iComment.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getDeleted())){
            lqw.eq(Comment::getDeleted,iComment.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getCreated())){
            lqw.eq(Comment::getCreated,iComment.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iComment.getUpdated())){
            lqw.eq(Comment::getUpdated,iComment.getUpdated());
        };
        return lqw;
    }
}




