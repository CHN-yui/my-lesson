package com.mdkj.controller;

import com.mdkj.domain.Order;
import com.mdkj.dto.CreateOrderDTO;
import com.mdkj.result.R;
import com.mdkj.service.OrderService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Order")
public class OrderController {
    @Autowired
    private OrderService iOrderService;

    /**
    * 查询全部订单表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iOrderService.selectAll());
    }

    /**
     * 条件查询订单表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Order iOrder){
        return R.ok("条件查询成功",iOrderService.selectList(iOrder));
    }

    /**
     * 新增订单表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Order iOrder){
        iOrderService.insert(iOrder);
        return R.ok("新增成功");
    }

    /**
     * 创建预支付订单
     */
    @PostMapping("/createPrepay")
    public R<?> createPrepay(@RequestBody CreateOrderDTO dto){
        return R.ok("创建预支付订单成功", iOrderService.createPrepayOrder(dto));
    }

    /**
     * 删除订单表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iOrderService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改订单表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Order iOrder){
        iOrderService.update(iOrder);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索订单表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Order iOrder, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iOrderService.pageList(iOrder,page,size));
    }

    /**
    * 导出订单表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "订单表", iOrderService.getExcelData());
    }

    /**
     * 订单统计
     */
    @GetMapping("/statistics")
    public R<?> statistics() {
        return R.ok("查询成功", iOrderService.statistics());
    }
}




