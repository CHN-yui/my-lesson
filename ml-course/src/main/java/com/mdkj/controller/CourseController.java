package com.mdkj.controller;

import com.mdkj.domain.Course;
import com.mdkj.result.R;
import com.mdkj.service.CourseService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Course")
public class CourseController {
    @Autowired
    private CourseService iCourseService;

    /**
    * 查询全部课程表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iCourseService.selectAll());
    }

    /**
     * 条件查询课程表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Course iCourse){
        return R.ok("条件查询成功",iCourseService.selectList(iCourse));
    }

    /**
     * 新增课程表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Course iCourse){
        iCourseService.insert(iCourse);
        return R.ok("新增成功");
    }

    /**
     * 删除课程表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iCourseService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改课程表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Course iCourse){
        iCourseService.update(iCourse);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索课程表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Course iCourse, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iCourseService.pageList(iCourse,page,size));
    }

    /**
    * 导出课程表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "课程表", iCourseService.getExcelData());
    }

    /**
     * 上传课程封面
     */
    @PostMapping("/uploadCover/{id}")
    public R<?> uploadCover(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return R.ok("上传课程封面成功", iCourseService.uploadCover(file, id));
    }

    /**
     * 上传课程摘要图
     */
    @PostMapping("/uploadSummary/{id}")
    public R<?> uploadSummary(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return R.ok("上传课程摘要图成功", iCourseService.uploadSummary(file, id));
    }

    /**
     * 搜索课程列表
     */
    @GetMapping("/search")
    public R<?> search(@RequestParam(name = "keyword", required = false) String keyword) {
        return R.ok("搜索成功", iCourseService.searchList(keyword));
    }
}




