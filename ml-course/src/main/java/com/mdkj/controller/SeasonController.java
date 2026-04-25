package com.mdkj.controller;

import com.mdkj.domain.Season;
import com.mdkj.result.R;
import com.mdkj.service.SeasonService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 季次表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Season")
public class SeasonController {
    @Autowired
    private SeasonService iSeasonService;

    /**
    * 查询全部季次表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iSeasonService.selectAll());
    }

    /**
     * 条件查询季次表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Season iSeason){
        return R.ok("条件查询成功",iSeasonService.selectList(iSeason));
    }

    /**
     * 新增季次表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Season iSeason){
        iSeasonService.insert(iSeason);
        return R.ok("新增成功");
    }

    /**
     * 删除季次表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iSeasonService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改季次表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Season iSeason){
        iSeasonService.update(iSeason);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索季次表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Season iSeason, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iSeasonService.pageList(iSeason,page,size));
    }

    /**
    * 导出季次表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "季次表", iSeasonService.getExcelData());
    }
}




