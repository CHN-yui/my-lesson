package com.mdkj.controller;

import com.mdkj.domain.SeckillDetail;
import com.mdkj.result.R;
import com.mdkj.service.SeckillDetailService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀明细表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SeckillDetail")
public class SeckillDetailController {
    @Autowired
    private SeckillDetailService iSeckillDetailService;

    /**
    * 查询全部秒杀明细表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iSeckillDetailService.selectAll());
    }

    /**
     * 条件查询秒杀明细表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody SeckillDetail iSeckillDetail){
        return R.ok("条件查询成功",iSeckillDetailService.selectList(iSeckillDetail));
    }

    /**
     * 新增秒杀明细表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody SeckillDetail iSeckillDetail){
        iSeckillDetailService.insert(iSeckillDetail);
        return R.ok("新增成功");
    }

    /**
     * 删除秒杀明细表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iSeckillDetailService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改秒杀明细表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody SeckillDetail iSeckillDetail){
        iSeckillDetailService.update(iSeckillDetail);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索秒杀明细表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody SeckillDetail iSeckillDetail, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iSeckillDetailService.pageList(iSeckillDetail,page,size));
    }

    /**
    * 导出秒杀明细表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "秒杀明细表", iSeckillDetailService.getExcelData());
    }
}




