package com.mdkj.service;

import com.mdkj.domain.Banner;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


/**
* 针对表【banner(横幅表)】的数据库操作Service
*/
public interface BannerService extends IService<Banner>{
    /**
    * 查询全部
    */
    List<Banner> selectAll();

    /**
    * 条件查询
    */
    List<Banner> selectList(Banner iBanner);

    /**
    * 新增
    */
    void insert(Banner iBanner);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Banner iBanner);

    /**
    * 分页查询
    */
    IPage<Banner> pageList(Banner iBanner, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Banner> getExcelData();

    /**
     * 查看置顶记录
     */
    List<Banner> topList(Integer topN);

    /**
     * 上传轮播图片
     */
    Map<String, String> uploadBanner(MultipartFile file, Long id);
}
