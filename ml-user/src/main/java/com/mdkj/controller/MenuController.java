package com.mdkj.controller;

import com.mdkj.domain.Menu;
import com.mdkj.result.R;
import com.mdkj.service.MenuService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Menu")
public class MenuController {
    @Autowired
    private MenuService iMenuService;

    /**
    * 查询全部菜单表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iMenuService.selectAll());
    }

    /**
     * 条件查询菜单表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Menu iMenu){
        return R.ok("条件查询成功",iMenuService.selectList(iMenu));
    }

    /**
     * 新增菜单表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Menu iMenu){
        iMenuService.insert(iMenu);
        return R.ok("新增成功");
    }

    /**
     * 删除菜单表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iMenuService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改菜单表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Menu iMenu){
        iMenuService.update(iMenu);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索菜单表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Menu iMenu, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iMenuService.pageList(iMenu,page,size));
    }

    /**
    * 导出菜单表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "菜单表", iMenuService.getExcelData());
    }
}




