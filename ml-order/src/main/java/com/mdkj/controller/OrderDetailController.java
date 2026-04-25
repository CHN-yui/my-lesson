package com.mdkj.controller;

import com.mdkj.domain.OrderDetail;
import com.mdkj.result.R;
import com.mdkj.service.OrderDetailService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单明细表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/OrderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService iOrderDetailService;

    /**
    * 查询全部订单明细表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iOrderDetailService.selectAll());
    }

    /**
     * 条件查询订单明细表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody OrderDetail iOrderDetail){
        return R.ok("条件查询成功",iOrderDetailService.selectList(iOrderDetail));
    }

    /**
     * 新增订单明细表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody OrderDetail iOrderDetail){
        iOrderDetailService.insert(iOrderDetail);
        return R.ok("新增成功");
    }

    /**
     * 删除订单明细表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iOrderDetailService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改订单明细表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody OrderDetail iOrderDetail){
        iOrderDetailService.update(iOrderDetail);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索订单明细表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody OrderDetail iOrderDetail, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iOrderDetailService.pageList(iOrderDetail,page,size));
    }

    /**
    * 导出订单明细表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "订单明细表", iOrderDetailService.getExcelData());
    }
}




