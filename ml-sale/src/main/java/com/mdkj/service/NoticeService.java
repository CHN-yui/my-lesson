package com.mdkj.service;

import com.mdkj.domain.Notice;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【notice(通知表)】的数据库操作Service
*/
public interface NoticeService extends IService<Notice>{
    /**
    * 查询全部
    */
    List<Notice> selectAll();

    /**
    * 条件查询
    */
    List<Notice> selectList(Notice iNotice);

    /**
    * 新增
    */
    void insert(Notice iNotice);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Notice iNotice);

    /**
    * 分页查询
    */
    IPage<Notice> pageList(Notice iNotice, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Notice> getExcelData();

    /**
     * 查看置顶通知
     */
    List<Notice> topList(Integer topN);
}
