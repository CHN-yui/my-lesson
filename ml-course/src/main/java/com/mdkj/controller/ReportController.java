package com.mdkj.controller;

import com.mdkj.domain.Report;
import com.mdkj.result.R;
import com.mdkj.service.ReportService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 举报表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Report")
public class ReportController {
    @Autowired
    private ReportService iReportService;

    /**
    * 查询全部举报表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iReportService.selectAll());
    }

    /**
     * 条件查询举报表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Report iReport){
        return R.ok("条件查询成功",iReportService.selectList(iReport));
    }

    /**
     * 新增举报表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Report iReport){
        iReportService.insert(iReport);
        return R.ok("新增成功");
    }

    /**
     * 删除举报表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iReportService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改举报表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Report iReport){
        iReportService.update(iReport);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索举报表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Report iReport, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iReportService.pageList(iReport,page,size));
    }

    /**
    * 导出举报表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "举报表", iReportService.getExcelData());
    }

    /**
     * 根据用户删除举报
     */
    @PostMapping("/deleteByUserId")
    public R<?> deleteByUserId(@RequestParam("userId") Long userId) {
        iReportService.deleteByUserId(userId);
        return R.ok("删除成功");
    }

    /**
     * 根据用户批量删除举报
     */
    @PostMapping("/deleteByUserIds")
    public R<?> deleteByUserIds(@RequestParam("userIds") String userIds) {
        iReportService.deleteByUserIds(userIds);
        return R.ok("批量删除成功");
    }
}




