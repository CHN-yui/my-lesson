package com.mdkj.controller;

import com.mdkj.domain.Coupons;
import com.mdkj.result.R;
import com.mdkj.service.CouponsService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠卷表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Coupons")
public class CouponsController {
    @Autowired
    private CouponsService iCouponsService;

    /**
    * 查询全部优惠卷表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iCouponsService.selectAll());
    }

    /**
     * 条件查询优惠卷表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Coupons iCoupons){
        return R.ok("条件查询成功",iCouponsService.selectList(iCoupons));
    }

    /**
     * 新增优惠卷表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Coupons iCoupons){
        iCouponsService.insert(iCoupons);
        return R.ok("新增成功");
    }

    /**
     * 删除优惠卷表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iCouponsService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改优惠卷表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Coupons iCoupons){
        iCouponsService.update(iCoupons);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索优惠卷表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Coupons iCoupons, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iCouponsService.pageList(iCoupons,page,size));
    }

    /**
    * 导出优惠卷表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "优惠卷表", iCouponsService.getExcelData());
    }

    /**
     * 根据口令查询
     */
    @GetMapping("/queryByCode")
    public R<?> queryByCode(@RequestParam("code") String code) {
        return R.ok("查询成功", iCouponsService.queryByCode(code));
    }
}




