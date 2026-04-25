package com.mdkj.controller;

import com.mdkj.domain.Banner;
import com.mdkj.result.R;
import com.mdkj.service.BannerService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 横幅表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Banner")
public class BannerController {
    @Autowired
    private BannerService iBannerService;

    /**
    * 查询全部横幅表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iBannerService.selectAll());
    }

    /**
     * 条件查询横幅表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Banner iBanner){
        return R.ok("条件查询成功",iBannerService.selectList(iBanner));
    }

    /**
     * 新增横幅表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Banner iBanner){
        iBannerService.insert(iBanner);
        return R.ok("新增成功");
    }

    /**
     * 删除横幅表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iBannerService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改横幅表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Banner iBanner){
        iBannerService.update(iBanner);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索横幅表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Banner iBanner, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iBannerService.pageList(iBanner,page,size));
    }

    /**
    * 导出横幅表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "横幅表", iBannerService.getExcelData());
    }

    /**
     * 查看置顶记录
     */
    @GetMapping("/top")
    public R<?> top(@RequestParam(name = "topN", defaultValue = "5") Integer topN) {
        return R.ok("查询成功", iBannerService.topList(topN));
    }

    /**
     * 上传轮播图片
     */
    @PostMapping("/upload/{id}")
    public R<?> upload(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return R.ok("上传成功", iBannerService.uploadBanner(file, id));
    }
}




