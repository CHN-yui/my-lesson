package com.mdkj.controller;

import com.mdkj.domain.Category;
import com.mdkj.result.R;
import com.mdkj.service.CategoryService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 课程类别表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Category")
public class CategoryController {
    @Autowired
    private CategoryService iCategoryService;

    /**
    * 查询全部课程类别表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iCategoryService.selectAll());
    }

    /**
     * 条件查询课程类别表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Category iCategory){
        return R.ok("条件查询成功",iCategoryService.selectList(iCategory));
    }

    /**
     * 新增课程类别表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Category iCategory){
        iCategoryService.insert(iCategory);
        return R.ok("新增成功");
    }

    /**
     * 删除课程类别表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iCategoryService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改课程类别表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Category iCategory){
        iCategoryService.update(iCategory);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索课程类别表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Category iCategory, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iCategoryService.pageList(iCategory,page,size));
    }

    /**
    * 导出课程类别表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "课程类别表", iCategoryService.getExcelData());
    }
}




