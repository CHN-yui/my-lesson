package com.mdkj.service;

import com.mdkj.domain.Article;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【article(新闻表)】的数据库操作Service
*/
public interface ArticleService extends IService<Article>{
    /**
    * 查询全部
    */
    List<Article> selectAll();

    /**
    * 条件查询
    */
    List<Article> selectList(Article iArticle);

    /**
    * 新增
    */
    void insert(Article iArticle);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Article iArticle);

    /**
    * 分页查询
    */
    IPage<Article> pageList(Article iArticle, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Article> getExcelData();

    /**
     * 查看置顶新闻
     */
    List<Article> topList(Integer topN);
}
