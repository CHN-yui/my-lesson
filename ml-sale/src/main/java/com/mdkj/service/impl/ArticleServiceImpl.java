package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.component.MyRedis;
import com.mdkj.constant.ML;
import com.mdkj.domain.Article;
import com.mdkj.service.ArticleService;
import com.mdkj.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【article(新闻表)】的数据库操作Service实现
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
    @Autowired
    private MyRedis myRedis;

    @Override
    public List<Article> selectAll() {
        LambdaQueryWrapper <Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Article> selectList(Article iArticle) {
        LambdaQueryWrapper <Article> lqw = this.lqw(iArticle);
        lqw.eq(Article::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Article iArticle) {
        save(iArticle);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Article> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Article entity  = new Article();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Article iArticle) {
        updateById(iArticle);
    }


    @Override
    public IPage<Article> pageList(Article iArticle, Integer page, Integer size) {
        Page<Article> pag = new Page<>(page,size);
        LambdaQueryWrapper<Article> lqw = lqw(iArticle);
        lqw.eq(Article::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Article> getExcelData() {
        return selectAll();
    }

    @Override
    public List<Article> topList(Integer topN) {
        int limit = (topN == null || topN < 1) ? 5 : topN;
        String cacheKey = ML.Redis.TOP_ARTICLE_KEY_PREFIX + limit;
        if (myRedis.exists(cacheKey)) {
            List<String> ids = myRedis.lRange(cacheKey, 0, -1);
            if (ids != null && !ids.isEmpty()) {
                List<Article> result = new ArrayList<>();
                for (String id : ids) {
                    Article article = getById(Long.valueOf(id));
                    if (article != null && article.getDeleted() != null && article.getDeleted() == 0) {
                        result.add(article);
                    }
                }
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }

        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getDeleted, 0)
                .orderByAsc(Article::getIdx)
                .orderByDesc(Article::getId)
                .last("limit " + limit);
        List<Article> topList = list(lqw);
        myRedis.del(cacheKey);
        for (int i = topList.size() - 1; i >= 0; i--) {
            myRedis.lPush(cacheKey, String.valueOf(topList.get(i).getId()));
        }
        return topList;
    }

    public LambdaQueryWrapper<Article> lqw(Article iArticle){
        LambdaQueryWrapper <Article> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iArticle.getId())){
            lqw.eq(Article::getId,iArticle.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getTitle())){
            lqw.eq(Article::getTitle,iArticle.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getContent())){
            lqw.eq(Article::getContent,iArticle.getContent());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getIdx())){
            lqw.eq(Article::getIdx,iArticle.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getVersion())){
            lqw.eq(Article::getVersion,iArticle.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getDeleted())){
            lqw.eq(Article::getDeleted,iArticle.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getCreated())){
            lqw.eq(Article::getCreated,iArticle.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iArticle.getUpdated())){
            lqw.eq(Article::getUpdated,iArticle.getUpdated());
        };
        return lqw;
    }
}




