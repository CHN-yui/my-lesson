package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Category;
import com.mdkj.service.CategoryService;
import com.mdkj.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【category(课程类别表)】的数据库操作Service实现
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Override
    public List<Category> selectAll() {
        LambdaQueryWrapper <Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Category::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Category> selectList(Category iCategory) {
        LambdaQueryWrapper <Category> lqw = this.lqw(iCategory);
        lqw.eq(Category::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Category iCategory) {
        save(iCategory);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Category> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Category entity  = new Category();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Category iCategory) {
        updateById(iCategory);
    }


    @Override
    public IPage<Category> pageList(Category iCategory, Integer page, Integer size) {
        Page<Category> pag = new Page<>(page,size);
        LambdaQueryWrapper<Category> lqw = lqw(iCategory);
        lqw.eq(Category::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Category> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<Category> lqw(Category iCategory){
        LambdaQueryWrapper <Category> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iCategory.getId())){
            lqw.eq(Category::getId,iCategory.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getTitle())){
            lqw.eq(Category::getTitle,iCategory.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getIdx())){
            lqw.eq(Category::getIdx,iCategory.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getInfo())){
            lqw.eq(Category::getInfo,iCategory.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getVersion())){
            lqw.eq(Category::getVersion,iCategory.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getDeleted())){
            lqw.eq(Category::getDeleted,iCategory.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getCreated())){
            lqw.eq(Category::getCreated,iCategory.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iCategory.getUpdated())){
            lqw.eq(Category::getUpdated,iCategory.getUpdated());
        };
        return lqw;
    }
}




