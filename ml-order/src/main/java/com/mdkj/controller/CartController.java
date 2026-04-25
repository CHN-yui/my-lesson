package com.mdkj.controller;

import com.mdkj.domain.Cart;
import com.mdkj.result.R;
import com.mdkj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Cart")
public class CartController {
    @Autowired
    private CartService iCartService;

    /**
    * 查询全部购物车表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iCartService.selectAll());
    }

    /**
     * 条件查询购物车表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Cart iCart){
        return R.ok("条件查询成功",iCartService.selectList(iCart));
    }

    /**
     * 新增购物车表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Cart iCart){
        iCartService.insert(iCart);
        return R.ok("新增成功");
    }

    /**
     * 删除购物车表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iCartService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改购物车表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Cart iCart){
        iCartService.update(iCart);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索购物车表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Cart iCart, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iCartService.pageList(iCart,page,size));
    }

    /**
    * 导出购物车表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "购物车表", iCartService.getExcelData());
    }
}




