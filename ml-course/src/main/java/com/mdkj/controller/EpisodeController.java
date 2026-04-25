package com.mdkj.controller;

import com.mdkj.domain.Episode;
import com.mdkj.result.R;
import com.mdkj.service.EpisodeService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 集次表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Episode")
public class EpisodeController {
    @Autowired
    private EpisodeService iEpisodeService;

    /**
    * 查询全部集次表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iEpisodeService.selectAll());
    }

    /**
     * 条件查询集次表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Episode iEpisode){
        return R.ok("条件查询成功",iEpisodeService.selectList(iEpisode));
    }

    /**
     * 新增集次表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Episode iEpisode){
        iEpisodeService.insert(iEpisode);
        return R.ok("新增成功");
    }

    /**
     * 删除集次表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iEpisodeService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改集次表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Episode iEpisode){
        iEpisodeService.update(iEpisode);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索集次表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Episode iEpisode, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iEpisodeService.pageList(iEpisode,page,size));
    }

    /**
    * 导出集次表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "集次表", iEpisodeService.getExcelData());
    }

    /**
     * 上传集次封面
     */
    @PostMapping("/uploadCover/{id}")
    public R<?> uploadCover(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return R.ok("上传集次封面成功", iEpisodeService.uploadCover(file, id));
    }

    /**
     * 上传集次视频
     */
    @PostMapping("/uploadVideo/{id}")
    public R<?> uploadVideo(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return R.ok("上传集次视频成功", iEpisodeService.uploadVideo(file, id));
    }

    /**
     * 查询弹幕列表
     */
    @GetMapping("/danmu")
    public R<?> danmu(@RequestParam("episodeId") Long episodeId) {
        return R.ok("查询成功", iEpisodeService.listDanmu(episodeId));
    }

    /**
     * 下载数据报表（统计）
     */
    @GetMapping("/statistics")
    public R<?> statistics(@RequestParam("episodeId") Long episodeId) {
        return R.ok("查询成功", iEpisodeService.statistics(episodeId));
    }
}




