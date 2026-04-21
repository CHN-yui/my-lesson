package com.mdkj.controller;

import com.mdkj.domain.Role;
import com.mdkj.result.R;
import com.mdkj.service.RoleService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Role")
public class RoleController {
    @Autowired
    private RoleService iRoleService;

    /**
    * 查询全部角色表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iRoleService.selectAll());
    }

    /**
     * 条件查询角色表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Role iRole){
        return R.ok("条件查询成功",iRoleService.selectList(iRole));
    }

    /**
     * 新增角色表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Role iRole){
        iRoleService.insert(iRole);
        return R.ok("新增成功");
    }

    /**
     * 删除角色表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iRoleService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改角色表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Role iRole){
        iRoleService.update(iRole);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索角色表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Role iRole, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iRoleService.pageList(iRole,page,size));
    }

    /**
    * 导出角色表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "角色表", iRoleService.getExcelData());
    }
}




