package com.mdkj.controller;

import com.mdkj.domain.Follow;
import com.mdkj.result.R;
import com.mdkj.service.FollowService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Follow")
public class FollowController {
    @Autowired
    private FollowService iFollowService;

    /**
    * 查询全部收藏表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iFollowService.selectAll());
    }

    /**
     * 条件查询收藏表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Follow iFollow){
        return R.ok("条件查询成功",iFollowService.selectList(iFollow));
    }

    /**
     * 新增收藏表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Follow iFollow){
        iFollowService.insert(iFollow);
        return R.ok("新增成功");
    }

    /**
     * 删除收藏表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iFollowService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改收藏表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Follow iFollow){
        iFollowService.update(iFollow);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索收藏表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Follow iFollow, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iFollowService.pageList(iFollow,page,size));
    }

    /**
    * 导出收藏表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "收藏表", iFollowService.getExcelData());
    }
}




