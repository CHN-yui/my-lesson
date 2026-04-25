package com.mdkj.controller;

import com.mdkj.domain.Notice;
import com.mdkj.result.R;
import com.mdkj.service.NoticeService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 通知表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Notice")
public class NoticeController {
    @Autowired
    private NoticeService iNoticeService;

    /**
    * 查询全部通知表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iNoticeService.selectAll());
    }

    /**
     * 条件查询通知表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Notice iNotice){
        return R.ok("条件查询成功",iNoticeService.selectList(iNotice));
    }

    /**
     * 新增通知表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Notice iNotice){
        iNoticeService.insert(iNotice);
        return R.ok("新增成功");
    }

    /**
     * 删除通知表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iNoticeService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改通知表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Notice iNotice){
        iNoticeService.update(iNotice);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索通知表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Notice iNotice, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iNoticeService.pageList(iNotice,page,size));
    }

    /**
    * 导出通知表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "通知表", iNoticeService.getExcelData());
    }

    /**
     * 查看置顶通知
     */
    @GetMapping("/top")
    public R<?> top(@RequestParam(name = "topN", defaultValue = "5") Integer topN) {
        return R.ok("查询成功", iNoticeService.topList(topN));
    }
}




