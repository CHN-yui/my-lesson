package com.mdkj.mapper;

import com.mdkj.domain.Notice;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【notice(通知表)】的数据库操作Mapper
* @utils com.mdkj.domain.Notice
*/
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}




