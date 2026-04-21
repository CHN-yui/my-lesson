package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Menu;
import com.mdkj.service.MenuService;
import com.mdkj.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【menu(菜单表)】的数据库操作Service实现
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{

    @Override
    public List<Menu> selectAll() {
        LambdaQueryWrapper <Menu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Menu::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Menu> selectList(Menu iMenu) {
        LambdaQueryWrapper <Menu> lqw = this.lqw(iMenu);
        lqw.eq(Menu::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Menu iMenu) {
        save(iMenu);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Menu> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Menu entity  = new Menu();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Menu iMenu) {
        updateById(iMenu);
    }


    @Override
    public IPage<Menu> pageList(Menu iMenu, Integer page, Integer size) {
        Page<Menu> pag = new Page<>(page,size);
        LambdaQueryWrapper<Menu> lqw = lqw(iMenu);
        lqw.eq(Menu::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Menu> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<Menu> lqw(Menu iMenu){
        LambdaQueryWrapper <Menu> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iMenu.getId())){
            lqw.eq(Menu::getId,iMenu.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getTitle())){
            lqw.eq(Menu::getTitle,iMenu.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getUrl())){
            lqw.eq(Menu::getUrl,iMenu.getUrl());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getIcon())){
            lqw.eq(Menu::getIcon,iMenu.getIcon());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getPid())){
            lqw.eq(Menu::getPid,iMenu.getPid());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getIdx())){
            lqw.eq(Menu::getIdx,iMenu.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getInfo())){
            lqw.eq(Menu::getInfo,iMenu.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getVersion())){
            lqw.eq(Menu::getVersion,iMenu.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getDeleted())){
            lqw.eq(Menu::getDeleted,iMenu.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getCreated())){
            lqw.eq(Menu::getCreated,iMenu.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iMenu.getUpdated())){
            lqw.eq(Menu::getUpdated,iMenu.getUpdated());
        };
        return lqw;
    }
}




