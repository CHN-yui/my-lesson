package com.mdkj.service;

import com.mdkj.domain.Course;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


/**
* 针对表【course(课程表)】的数据库操作Service
*/
public interface CourseService extends IService<Course>{
    /**
    * 查询全部
    */
    List<Course> selectAll();

    /**
    * 条件查询
    */
    List<Course> selectList(Course iCourse);

    /**
    * 新增
    */
    void insert(Course iCourse);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Course iCourse);

    /**
    * 分页查询
    */
    IPage<Course> pageList(Course iCourse, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Course> getExcelData();

    /**
     * 上传课程封面
     */
    Map<String, String> uploadCover(MultipartFile file, Long id);

    /**
     * 上传课程摘要图
     */
    Map<String, String> uploadSummary(MultipartFile file, Long id);

    /**
     * 搜索课程列表（标题/作者/描述模糊匹配）
     */
    List<Course> searchList(String keyword);
}
