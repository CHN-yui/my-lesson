package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.component.MyRedis;
import com.mdkj.constant.ML;
import com.mdkj.domain.Banner;
import com.mdkj.service.BannerService;
import com.mdkj.mapper.BannerMapper;
import com.mdkj.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mdkj.util.NotNullCheckUtil;
import org.springframework.web.multipart.MultipartFile;

/**
* 针对表【banner(横幅表)】的数据库操作Service实现
*/
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService{
    @Autowired
    private MyRedis myRedis;

    @Override
    public List<Banner> selectAll() {
        LambdaQueryWrapper <Banner> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Banner::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Banner> selectList(Banner iBanner) {
        LambdaQueryWrapper <Banner> lqw = this.lqw(iBanner);
        lqw.eq(Banner::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Banner iBanner) {
        save(iBanner);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Banner> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Banner entity  = new Banner();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Banner iBanner) {
        updateById(iBanner);
    }


    @Override
    public IPage<Banner> pageList(Banner iBanner, Integer page, Integer size) {
        Page<Banner> pag = new Page<>(page,size);
        LambdaQueryWrapper<Banner> lqw = lqw(iBanner);
        lqw.eq(Banner::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Banner> getExcelData() {
        return selectAll();
    }

    @Override
    public List<Banner> topList(Integer topN) {
        int limit = (topN == null || topN < 1) ? 5 : topN;
        String cacheKey = ML.Redis.TOP_BANNER_KEY_PREFIX + limit;
        if (myRedis.exists(cacheKey)) {
            List<String> ids = myRedis.lRange(cacheKey, 0, -1);
            if (ids != null && !ids.isEmpty()) {
                List<Banner> result = new ArrayList<>();
                for (String id : ids) {
                    Banner banner = getById(Long.valueOf(id));
                    if (banner != null && banner.getDeleted() != null && banner.getDeleted() == 0) {
                        result.add(banner);
                    }
                }
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }

        LambdaQueryWrapper<Banner> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Banner::getDeleted, 0)
                .orderByAsc(Banner::getIdx)
                .orderByDesc(Banner::getId)
                .last("limit " + limit);
        List<Banner> topList = list(lqw);
        myRedis.del(cacheKey);
        for (int i = topList.size() - 1; i >= 0; i--) {
            myRedis.lPush(cacheKey, String.valueOf(topList.get(i).getId()));
        }
        return topList;
    }

    @Override
    public Map<String, String> uploadBanner(MultipartFile file, Long id) {
        Banner banner = getById(id);
        if (banner == null) {
            throw new RuntimeException("横幅记录不存在");
        }
        String oldFileName = banner.getUrl();
        String fileName = MinioUtil.randomFilename(file);
        MinioUtil.upload(file, fileName, ML.MinIO.BANNER_DIR, ML.MinIO.BUCKET_NAME);
        Banner update = new Banner();
        update.setId(id);
        update.setUrl(fileName);
        updateById(update);
        if (oldFileName != null && !ML.Banner.DEFAULT_BANNER.equals(oldFileName)) {
            try {
                MinioUtil.delete(oldFileName, ML.MinIO.BANNER_DIR, ML.MinIO.BUCKET_NAME);
            } catch (Exception ignored) {
            }
        }
        Map<String, String> result = new HashMap<>(2);
        result.put("fileName", fileName);
        result.put("bannerUrl", MinioUtil.publicUrl(fileName, ML.MinIO.BANNER_DIR, ML.MinIO.BUCKET_NAME));
        return result;
    }

    public LambdaQueryWrapper<Banner> lqw(Banner iBanner){
        LambdaQueryWrapper <Banner> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iBanner.getId())){
            lqw.eq(Banner::getId,iBanner.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getUrl())){
            lqw.eq(Banner::getUrl,iBanner.getUrl());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getInfo())){
            lqw.eq(Banner::getInfo,iBanner.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getIdx())){
            lqw.eq(Banner::getIdx,iBanner.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getVersion())){
            lqw.eq(Banner::getVersion,iBanner.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getDeleted())){
            lqw.eq(Banner::getDeleted,iBanner.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getCreated())){
            lqw.eq(Banner::getCreated,iBanner.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iBanner.getUpdated())){
            lqw.eq(Banner::getUpdated,iBanner.getUpdated());
        };
        return lqw;
    }
}




