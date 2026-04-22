package com.mdkj.controller;

import com.mdkj.domain.User;
import com.mdkj.result.R;
import com.mdkj.service.UserService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 用户表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/User")
public class UserController {
    @Autowired
    private UserService iUserService;

    /**
     * 查询全部用户表
     */
    @GetMapping("/selectAll")
    public R<?> selectAll() {
        return R.ok("查询成功", iUserService.selectAll());
    }

    /**
     * 条件查询用户表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody User iUser) {
        return R.ok("条件查询成功", iUserService.selectList(iUser));
    }

    /**
     * 新增用户表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody User iUser) {
        iUserService.insert(iUser);
        return R.ok("新增成功");
    }

    /**
     * 删除用户表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids) {
        iUserService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改用户表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody User iUser) {
        iUserService.update(iUser);
        return R.ok("修改成功");
    }

    /**
     * 分页搜索用户表
     */
    @PostMapping("/page")
    public R<?> page(@RequestBody User iUser, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return R.ok("查询成功", iUserService.pageList(iUser, page, size));
    }

    /**
     * 导出用户表Excel数据
     */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "用户表", iUserService.getExcelData());
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPassword")
    public R resetPassword(@RequestParam("ids") String ids) {
        iUserService.resetPassword(ids);
        return R.ok("重置密码成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public R updatePassword(@RequestBody Map<String, String> map) {
        iUserService.updatePassword(map.get("oldPassword"), map.get("newPassword"), map.get("id"));
        return R.ok("修改密码成功");
    }


    /**
     * 上传用户头像
     */
    @PostMapping("/uploadAvatar/{id}")
    public R uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return R.ok("上传头像成功", iUserService.uploadAvatar(file, id));
    }

    /**
     * 账号密码登录
     */
    @PostMapping("/accountLogin")
    public R<?> accountLogin(@RequestBody Map<String, String> map) {
        return R.ok("登录成功", iUserService.accountLogin(map.get("username"), map.get("password")));
    }

    /**
     * 手机号验证码登录
     */
    @PostMapping("/phoneLogin")
    public R<?> phoneLogin(@RequestBody Map<String, String> map) {
        return R.ok("登录成功", iUserService.phoneLogin(map.get("phone"), map.get("vcode")));
    }

    /**
     * 换绑手机号
     */
    @PostMapping("/rebindPhone")
    public R<?> rebindPhone(@RequestBody Map<String, String> map) {
        iUserService.rebindPhone(
                Long.valueOf(map.get("id")),
                map.get("oldPhone"),
                map.get("newPhone"),
                map.get("oldVcode"),
                map.get("newVcode"));
        return R.ok("换绑手机号成功");
    }

    /**
     * 用户数据统计
     */
    @GetMapping("/statistics")
    public R<?> statistics() {
        return R.ok("查询成功", iUserService.statistics());
    }
}






