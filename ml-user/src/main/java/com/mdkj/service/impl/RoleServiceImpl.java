package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Role;
import com.mdkj.service.RoleService;
import com.mdkj.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【role(角色表)】的数据库操作Service实现
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Override
    public List<Role> selectAll() {
        LambdaQueryWrapper <Role> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Role::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Role> selectList(Role iRole) {
        LambdaQueryWrapper <Role> lqw = this.lqw(iRole);
        lqw.eq(Role::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Role iRole) {
        save(iRole);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Role> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Role entity  = new Role();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Role iRole) {
        updateById(iRole);
    }


    @Override
    public IPage<Role> pageList(Role iRole, Integer page, Integer size) {
        Page<Role> pag = new Page<>(page,size);
        LambdaQueryWrapper<Role> lqw = lqw(iRole);
        lqw.eq(Role::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Role> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<Role> lqw(Role iRole){
        LambdaQueryWrapper <Role> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iRole.getId())){
            lqw.eq(Role::getId,iRole.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getTitle())){
            lqw.eq(Role::getTitle,iRole.getTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getIdx())){
            lqw.eq(Role::getIdx,iRole.getIdx());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getInfo())){
            lqw.eq(Role::getInfo,iRole.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getVersion())){
            lqw.eq(Role::getVersion,iRole.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getDeleted())){
            lqw.eq(Role::getDeleted,iRole.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getCreated())){
            lqw.eq(Role::getCreated,iRole.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iRole.getUpdated())){
            lqw.eq(Role::getUpdated,iRole.getUpdated());
        };
        return lqw;
    }
}




