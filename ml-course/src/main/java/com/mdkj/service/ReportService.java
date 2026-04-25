package com.mdkj.service;

import com.mdkj.domain.Report;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【report(举报表)】的数据库操作Service
*/
public interface ReportService extends IService<Report>{
    /**
    * 查询全部
    */
    List<Report> selectAll();

    /**
    * 条件查询
    */
    List<Report> selectList(Report iReport);

    /**
    * 新增
    */
    void insert(Report iReport);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Report iReport);

    /**
    * 分页查询
    */
    IPage<Report> pageList(Report iReport, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Report> getExcelData();

    /**
     * 根据用户删除举报
     */
    void deleteByUserId(Long userId);

    /**
     * 根据用户批量删除举报
     */
    void deleteByUserIds(String userIds);
}
