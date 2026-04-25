package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.constant.ML;
import com.mdkj.domain.Course;
import com.mdkj.util.MinioUtil;
import com.mdkj.service.CourseService;
import com.mdkj.mapper.CourseMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【course(课程表)】的数据库操作Service实现
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService{

    @Override
    public List<Course> selectAll() {
        LambdaQueryWrapper <Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Course> selectList(Course iCourse) {
        LambdaQueryWrapper <Course> lqw = this.lqw(iCourse);
        lqw.eq(Course::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Course iCourse) {
        save(iCourse);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Course> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Course entity  = new Course();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Course iCourse) {
        updateById(iCourse);
    }


    @Override
    public IPage<Course> pageList(Course iCourse, Integer page, Integer size) {
        Page<Course> pag = new Page<>(page,size);
        LambdaQueryWrapper<Course> lqw = lqw(iCourse);
        lqw.eq(Course::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Course> getExcelData() {
        return selectAll();
    }

    @Override
    public Map<String, String> uploadCover(MultipartFile file, Long id) {
        Course course = getById(id);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        String oldFileName = course.getCover();
        String fileName = MinioUtil.randomFilename(file);
        MinioUtil.upload(file, fileName, ML.MinIO.COURSE_COVER_DIR, ML.MinIO.BUCKET_NAME);
        Course update = new Course();
        update.setId(id);
        update.setCover(fileName);
        updateById(update);
        if (oldFileName != null && !ML.Course.DEFAULT_COVER.equals(oldFileName)) {
            try {
                MinioUtil.delete(oldFileName, ML.MinIO.COURSE_COVER_DIR, ML.MinIO.BUCKET_NAME);
            } catch (Exception ignored) {
            }
        }
        Map<String, String> result = new HashMap<>(2);
        result.put("fileName", fileName);
        result.put("coverUrl", MinioUtil.publicUrl(fileName, ML.MinIO.COURSE_COVER_DIR, ML.MinIO.BUCKET_NAME));
        return result;
    }

    @Override
    public Map<String, String> uploadSummary(MultipartFile file, Long id) {
        Course course = getById(id);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        String oldFileName = course.getSummary();
        String fileName = MinioUtil.randomFilename(file);
        MinioUtil.upload(file, fileName, ML.MinIO.COURSE_SUMMARY_DIR, ML.MinIO.BUCKET_NAME);
        Course update = new Course();
        update.setId(id);
        update.setSummary(fileName);
        updateById(update);
        if (oldFileName != null && !ML.Course.DEFAULT_SUMMARY.equals(oldFileName)) {
            try {
                MinioUtil.delete(oldFileName, ML.MinIO.COURSE_SUMMARY_DIR, ML.MinIO.BUCKET_NAME);
            } catch (Exception ignored) {
            }
        }
        Map<String, String> result = new HashMap<>(2);
        result.put("fileName", fileName);
        result.put("summaryUrl", MinioUtil.publicUrl(fileName, ML.MinIO.COURSE_SUMMARY_DIR, ML.MinIO.BUCKET_NAME));
        return result;
    }

    @Override
    public List<Course> searchList(String keyword) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getDeleted, 0)
                .and(NotNullCheckUtil.checkNotNull(keyword), qw -> qw
                        .like(Course::getTitle, keyword)
                        .or()
                        .like(Course::getAuthor, keyword)
                        .or()
                        .like(Course::getInfo, keyword))
                .orderByAsc(Course::getIdx)
                .orderByDesc(Course::getId);
        return list(lqw);
    }

    public LambdaQueryWrapper<Course> lqw(Course iCourse){
        LambdaQueryWrapper <Course> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iCourse.getId())){
            lqw.eq(Course::getId,iCourse.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getTitle())){
            lqw.eq(Course::getTitle,iCourse.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getAuthor())){
            lqw.eq(Course::getAuthor,iCourse.getAuthor());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getFkCategoryId())){
            lqw.eq(Course::getFkCategoryId,iCourse.getFkCategoryId());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getSummary())){
            lqw.eq(Course::getSummary,iCourse.getSummary());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getCover())){
            lqw.eq(Course::getCover,iCourse.getCover());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getPrice())){
            lqw.eq(Course::getPrice,iCourse.getPrice());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getIdx())){
            lqw.eq(Course::getIdx,iCourse.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getInfo())){
            lqw.eq(Course::getInfo,iCourse.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getVersion())){
            lqw.eq(Course::getVersion,iCourse.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getDeleted())){
            lqw.eq(Course::getDeleted,iCourse.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getCreated())){
            lqw.eq(Course::getCreated,iCourse.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iCourse.getUpdated())){
            lqw.eq(Course::getUpdated,iCourse.getUpdated());
        };
        return lqw;
    }
}




