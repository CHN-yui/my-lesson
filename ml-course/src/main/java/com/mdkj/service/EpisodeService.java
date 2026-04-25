package com.mdkj.service;

import com.mdkj.domain.Episode;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


/**
* 针对表【episode(集次表)】的数据库操作Service
*/
public interface EpisodeService extends IService<Episode>{
    /**
    * 查询全部
    */
    List<Episode> selectAll();

    /**
    * 条件查询
    */
    List<Episode> selectList(Episode iEpisode);

    /**
    * 新增
    */
    void insert(Episode iEpisode);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Episode iEpisode);

    /**
    * 分页查询
    */
    IPage<Episode> pageList(Episode iEpisode, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Episode> getExcelData();

    /**
     * 上传集次封面
     */
    Map<String, String> uploadCover(MultipartFile file, Long id);

    /**
     * 上传集次视频
     */
    Map<String, String> uploadVideo(MultipartFile file, Long id);

    /**
     * 查询集次弹幕（评论）列表
     */
    List<Map<String, Object>> listDanmu(Long episodeId);

    /**
     * 集次统计报表
     */
    Map<String, Object> statistics(Long episodeId);
}
