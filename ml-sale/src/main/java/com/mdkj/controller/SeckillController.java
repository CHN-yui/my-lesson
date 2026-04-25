package com.mdkj.controller;

import com.mdkj.domain.Seckill;
import com.mdkj.result.R;
import com.mdkj.service.SeckillService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Seckill")
public class SeckillController {
    @Autowired
    private SeckillService iSeckillService;

    /**
    * 查询全部秒杀表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iSeckillService.selectAll());
    }

    /**
     * 条件查询秒杀表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Seckill iSeckill){
        return R.ok("条件查询成功",iSeckillService.selectList(iSeckill));
    }

    /**
     * 新增秒杀表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Seckill iSeckill){
        iSeckillService.insert(iSeckill);
        return R.ok("新增成功");
    }

    /**
     * 删除秒杀表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iSeckillService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改秒杀表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Seckill iSeckill){
        iSeckillService.update(iSeckill);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索秒杀表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Seckill iSeckill, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iSeckillService.pageList(iSeckill,page,size));
    }

    /**
    * 导出秒杀表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "秒杀表", iSeckillService.getExcelData());
    }

    /**
     * 查看今日秒杀
     */
    @GetMapping("/today")
    public R<?> today() {
        return R.ok("查询成功", iSeckillService.todayList());
    }

    /**
     * 秒杀活动预热
     */
    @PostMapping("/preheat")
    public R<?> preheat(@RequestParam("seckillId") Long seckillId) {
        return R.ok("预热成功", iSeckillService.preheat(seckillId));
    }

    /**
     * 开始秒杀课程
     */
    @PostMapping("/start")
    public R<?> start(@RequestParam("seckillId") Long seckillId) {
        iSeckillService.startSeckill(seckillId);
        return R.ok("开始秒杀成功");
    }
}




