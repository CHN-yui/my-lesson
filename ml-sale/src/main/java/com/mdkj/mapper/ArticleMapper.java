package com.mdkj.mapper;

import com.mdkj.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【article(新闻表)】的数据库操作Mapper
* @utils com.mdkj.domain.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




