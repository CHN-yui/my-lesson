package com.mdkj.controller;

import com.mdkj.domain.Comment;
import com.mdkj.result.R;
import com.mdkj.service.CommentService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Comment")
public class CommentController {
    @Autowired
    private CommentService iCommentService;

    /**
    * 查询全部评论表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iCommentService.selectAll());
    }

    /**
     * 条件查询评论表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Comment iComment){
        return R.ok("条件查询成功",iCommentService.selectList(iComment));
    }

    /**
     * 新增评论表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Comment iComment){
        iCommentService.insert(iComment);
        return R.ok("新增成功");
    }

    /**
     * 删除评论表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iCommentService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改评论表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Comment iComment){
        iCommentService.update(iComment);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索评论表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Comment iComment, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iCommentService.pageList(iComment,page,size));
    }

    /**
    * 导出评论表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "评论表", iCommentService.getExcelData());
    }

    /**
     * 根据用户删除评论
     */
    @PostMapping("/deleteByUserId")
    public R<?> deleteByUserId(@RequestParam("userId") Long userId) {
        iCommentService.deleteByUserId(userId);
        return R.ok("删除成功");
    }

    /**
     * 根据用户批量删除评论
     */
    @PostMapping("/deleteByUserIds")
    public R<?> deleteByUserIds(@RequestParam("userIds") String userIds) {
        iCommentService.deleteByUserIds(userIds);
        return R.ok("批量删除成功");
    }
}




