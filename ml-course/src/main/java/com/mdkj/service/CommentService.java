package com.mdkj.service;

import com.mdkj.domain.Comment;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【comment(评论表)】的数据库操作Service
*/
public interface CommentService extends IService<Comment>{
    /**
    * 查询全部
    */
    List<Comment> selectAll();

    /**
    * 条件查询
    */
    List<Comment> selectList(Comment iComment);

    /**
    * 新增
    */
    void insert(Comment iComment);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Comment iComment);

    /**
    * 分页查询
    */
    IPage<Comment> pageList(Comment iComment, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Comment> getExcelData();

    /**
     * 根据用户删除评论
     */
    void deleteByUserId(Long userId);

    /**
     * 根据用户批量删除评论
     */
    void deleteByUserIds(String userIds);
}
