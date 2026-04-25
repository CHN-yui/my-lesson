package com.mdkj.mapper;

import com.mdkj.domain.Report;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【report(举报表)】的数据库操作Mapper
* @utils com.mdkj.domain.Report
*/
@Mapper
public interface ReportMapper extends BaseMapper<Report> {

}




