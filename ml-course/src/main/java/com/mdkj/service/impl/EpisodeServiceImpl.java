package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.constant.ML;
import com.mdkj.domain.Comment;
import com.mdkj.domain.Episode;
import com.mdkj.mapper.CommentMapper;
import com.mdkj.service.EpisodeService;
import com.mdkj.mapper.EpisodeMapper;
import com.mdkj.util.MinioUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【episode(集次表)】的数据库操作Service实现
*/
@Service
public class EpisodeServiceImpl extends ServiceImpl<EpisodeMapper, Episode> implements EpisodeService{
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Episode> selectAll() {
        LambdaQueryWrapper <Episode> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Episode::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Episode> selectList(Episode iEpisode) {
        LambdaQueryWrapper <Episode> lqw = this.lqw(iEpisode);
        lqw.eq(Episode::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Episode iEpisode) {
        save(iEpisode);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Episode> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Episode entity  = new Episode();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Episode iEpisode) {
        updateById(iEpisode);
    }


    @Override
    public IPage<Episode> pageList(Episode iEpisode, Integer page, Integer size) {
        Page<Episode> pag = new Page<>(page,size);
        LambdaQueryWrapper<Episode> lqw = lqw(iEpisode);
        lqw.eq(Episode::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Episode> getExcelData() {
        return selectAll();
    }

    @Override
    public Map<String, String> uploadCover(MultipartFile file, Long id) {
        Episode episode = getById(id);
        if (episode == null) {
            throw new RuntimeException("集次不存在");
        }
        String oldFileName = episode.getCover();
        String fileName = MinioUtil.randomFilename(file);
        MinioUtil.upload(file, fileName, ML.MinIO.EPISODE_VIDEO_COVER_DIR, ML.MinIO.BUCKET_NAME);
        Episode update = new Episode();
        update.setId(id);
        update.setCover(fileName);
        updateById(update);
        if (oldFileName != null && !ML.Episode.DEFAULT_VIDEO_COVER.equals(oldFileName)) {
            try {
                MinioUtil.delete(oldFileName, ML.MinIO.EPISODE_VIDEO_COVER_DIR, ML.MinIO.BUCKET_NAME);
            } catch (Exception ignored) {
            }
        }
        Map<String, String> result = new HashMap<>(2);
        result.put("fileName", fileName);
        result.put("coverUrl", MinioUtil.publicUrl(fileName, ML.MinIO.EPISODE_VIDEO_COVER_DIR, ML.MinIO.BUCKET_NAME));
        return result;
    }

    @Override
    public Map<String, String> uploadVideo(MultipartFile file, Long id) {
        Episode episode = getById(id);
        if (episode == null) {
            throw new RuntimeException("集次不存在");
        }
        String oldFileName = episode.getVideo();
        String fileName = MinioUtil.randomFilename(file);
        MinioUtil.upload(file, fileName, ML.MinIO.EPISODE_VIDEO_DIR, ML.MinIO.BUCKET_NAME);
        Episode update = new Episode();
        update.setId(id);
        update.setVideo(fileName);
        updateById(update);
        if (oldFileName != null && !ML.Episode.DEFAULT_VIDEO.equals(oldFileName)) {
            try {
                MinioUtil.delete(oldFileName, ML.MinIO.EPISODE_VIDEO_DIR, ML.MinIO.BUCKET_NAME);
            } catch (Exception ignored) {
            }
        }
        Map<String, String> result = new HashMap<>(2);
        result.put("fileName", fileName);
        result.put("videoUrl", MinioUtil.publicUrl(fileName, ML.MinIO.EPISODE_VIDEO_DIR, ML.MinIO.BUCKET_NAME));
        return result;
    }

    @Override
    public List<Map<String, Object>> listDanmu(Long episodeId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getFkEpisodeId, episodeId)
                .eq(Comment::getDeleted, 0)
                .orderByAsc(Comment::getCreated);
        List<Comment> comments = commentMapper.selectList(lqw);
        List<Map<String, Object>> result = new ArrayList<>(comments.size());
        for (Comment comment : comments) {
            Map<String, Object> item = new HashMap<>(4);
            item.put("id", comment.getId());
            item.put("content", comment.getContent());
            item.put("nickname", comment.getNickname());
            item.put("created", comment.getCreated());
            result.add(item);
        }
        return result;
    }

    @Override
    public Map<String, Object> statistics(Long episodeId) {
        LambdaQueryWrapper<Comment> commentQuery = new LambdaQueryWrapper<>();
        commentQuery.eq(Comment::getFkEpisodeId, episodeId).eq(Comment::getDeleted, 0);
        long commentCount = commentMapper.selectCount(commentQuery);
        Map<String, Object> data = new HashMap<>(2);
        data.put("episodeId", episodeId);
        data.put("commentCount", commentCount);
        return data;
    }

    public LambdaQueryWrapper<Episode> lqw(Episode iEpisode){
        LambdaQueryWrapper <Episode> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iEpisode.getId())){
            lqw.eq(Episode::getId,iEpisode.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getTitle())){
            lqw.eq(Episode::getTitle,iEpisode.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getInfo())){
            lqw.eq(Episode::getInfo,iEpisode.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getVideo())){
            lqw.eq(Episode::getVideo,iEpisode.getVideo());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getCover())){
            lqw.eq(Episode::getCover,iEpisode.getCover());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getFkSeasonId())){
            lqw.eq(Episode::getFkSeasonId,iEpisode.getFkSeasonId());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getIdx())){
            lqw.eq(Episode::getIdx,iEpisode.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getVersion())){
            lqw.eq(Episode::getVersion,iEpisode.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getDeleted())){
            lqw.eq(Episode::getDeleted,iEpisode.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getCreated())){
            lqw.eq(Episode::getCreated,iEpisode.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iEpisode.getUpdated())){
            lqw.eq(Episode::getUpdated,iEpisode.getUpdated());
        };
        return lqw;
    }
}




