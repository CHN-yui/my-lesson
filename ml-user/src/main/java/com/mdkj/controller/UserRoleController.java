package com.mdkj.controller;

import com.mdkj.domain.UserRole;
import com.mdkj.result.R;
import com.mdkj.service.UserRoleService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色关系表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/UserRole")
public class UserRoleController {
    @Autowired
    private UserRoleService iUserRoleService;

    /**
    * 查询全部用户角色关系表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iUserRoleService.selectAll());
    }

    /**
     * 条件查询用户角色关系表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody UserRole iUserRole){
        return R.ok("条件查询成功",iUserRoleService.selectList(iUserRole));
    }

    /**
     * 新增用户角色关系表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody UserRole iUserRole){
        iUserRoleService.insert(iUserRole);
        return R.ok("新增成功");
    }

    /**
     * 删除用户角色关系表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iUserRoleService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改用户角色关系表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody UserRole iUserRole){
        iUserRoleService.update(iUserRole);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索用户角色关系表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody UserRole iUserRole, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iUserRoleService.pageList(iUserRole,page,size));
    }

    /**
    * 导出用户角色关系表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "用户角色关系表", iUserRoleService.getExcelData());
    }
}




