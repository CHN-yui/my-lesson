package com.mdkj.mapper;

import com.mdkj.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【comment(评论表)】的数据库操作Mapper
* @utils com.mdkj.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




