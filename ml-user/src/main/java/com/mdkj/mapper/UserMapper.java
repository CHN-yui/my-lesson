package com.mdkj.mapper;

import com.mdkj.domain.User;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【user(用户表)】的数据库操作Mapper
* @utils generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




