package com.mdkj.controller;

import com.mdkj.domain.RoleMenu;
import com.mdkj.result.R;
import com.mdkj.service.RoleMenuService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单关系表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/RoleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService iRoleMenuService;

    /**
    * 查询全部角色菜单关系表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iRoleMenuService.selectAll());
    }

    /**
     * 条件查询角色菜单关系表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody RoleMenu iRoleMenu){
        return R.ok("条件查询成功",iRoleMenuService.selectList(iRoleMenu));
    }

    /**
     * 新增角色菜单关系表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody RoleMenu iRoleMenu){
        iRoleMenuService.insert(iRoleMenu);
        return R.ok("新增成功");
    }

    /**
     * 删除角色菜单关系表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iRoleMenuService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改角色菜单关系表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody RoleMenu iRoleMenu){
        iRoleMenuService.update(iRoleMenu);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索角色菜单关系表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody RoleMenu iRoleMenu, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iRoleMenuService.pageList(iRoleMenu,page,size));
    }

    /**
    * 导出角色菜单关系表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "角色菜单关系表", iRoleMenuService.getExcelData());
    }

    /**
     * 查询角色菜单
     */
    @GetMapping("/getMenuIdsByRoleId/{roleId}")
    public R<?> getMenuIdsByRoleId(@PathVariable("roleId") Long roleId) {
        return R.ok("查询成功", iRoleMenuService.getMenuIdsByRoleId(roleId));
    }

    /**
     * 修改角色菜单
     */
    @PostMapping("/updateRoleMenus")
    public R<?> updateRoleMenus(@RequestBody Map<String, Object> map) {
        Long roleId = Long.valueOf(String.valueOf(map.get("roleId")));
        @SuppressWarnings("unchecked")
        List<Number> rawMenuIds = (List<Number>) map.get("menuIds");
        List<Long> menuIds = rawMenuIds == null ? List.of() : rawMenuIds.stream().map(Number::longValue).toList();
        iRoleMenuService.updateRoleMenus(roleId, menuIds);
        return R.ok("修改角色菜单成功");
    }
}




