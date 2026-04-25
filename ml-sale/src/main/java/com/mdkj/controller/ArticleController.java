package com.mdkj.controller;

import com.mdkj.domain.Article;
import com.mdkj.result.R;
import com.mdkj.service.ArticleService;
import com.mdkj.util.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻表管理
 */
@RestController
@CrossOrigin
@RequestMapping("/api/Article")
public class ArticleController {
    @Autowired
    private ArticleService iArticleService;

    /**
    * 查询全部新闻表
    */
    @GetMapping("/selectAll")
    public R<?> selectAll(){
        return R.ok("查询成功",iArticleService.selectAll());
    }

    /**
     * 条件查询新闻表
     */
    @PostMapping("/selectList")
    public R<?> select(@RequestBody Article iArticle){
        return R.ok("条件查询成功",iArticleService.selectList(iArticle));
    }

    /**
     * 新增新闻表
     */
    @PostMapping("/insert")
    public R<?> insert(@RequestBody Article iArticle){
        iArticleService.insert(iArticle);
        return R.ok("新增成功");
    }

    /**
     * 删除新闻表
     */
    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids){
        iArticleService.delete(ids);
        return R.ok("删除成功");
    }

    /**
     * 修改新闻表
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody Article iArticle){
        iArticleService.update(iArticle);
        return R.ok("修改成功");
    }

    /**
    * 分页搜索新闻表
    */
    @PostMapping("/page")
    public R<?> page(@RequestBody Article iArticle, @RequestParam(name="page",defaultValue = "1")  Integer page, @RequestParam(name="size",defaultValue = "10") Integer size){
        return R.ok("查询成功",iArticleService.pageList(iArticle,page,size));
    }

    /**
    * 导出新闻表Excel数据
    */
    @SneakyThrows
    @GetMapping("/excel")
    public void excel(HttpServletResponse resp) {
        EasyExcelUtil.download(resp, "新闻表", iArticleService.getExcelData());
    }

    /**
     * 查看置顶新闻
     */
    @GetMapping("/top")
    public R<?> top(@RequestParam(name = "topN", defaultValue = "5") Integer topN) {
        return R.ok("查询成功", iArticleService.topList(topN));
    }
}




