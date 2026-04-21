package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.UserRole;
import com.mdkj.service.UserRoleService;
import com.mdkj.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【user_role(用户角色关系表)】的数据库操作Service实现
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

    @Override
    public List<UserRole> selectAll() {
        LambdaQueryWrapper <UserRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserRole::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<UserRole> selectList(UserRole iUserRole) {
        LambdaQueryWrapper <UserRole> lqw = this.lqw(iUserRole);
        lqw.eq(UserRole::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(UserRole iUserRole) {
        save(iUserRole);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<UserRole> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            UserRole entity  = new UserRole();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(UserRole iUserRole) {
        updateById(iUserRole);
    }


    @Override
    public IPage<UserRole> pageList(UserRole iUserRole, Integer page, Integer size) {
        Page<UserRole> pag = new Page<>(page,size);
        LambdaQueryWrapper<UserRole> lqw = lqw(iUserRole);
        lqw.eq(UserRole::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<UserRole> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<UserRole> lqw(UserRole iUserRole){
        LambdaQueryWrapper <UserRole> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iUserRole.getId())){
            lqw.eq(UserRole::getId,iUserRole.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iUserRole.getFkUserId())){
            lqw.eq(UserRole::getFkUserId,iUserRole.getFkUserId());
        };
        if(NotNullCheckUtil.checkNotNull(iUserRole.getFkRoleId())){
            lqw.eq(UserRole::getFkRoleId,iUserRole.getFkRoleId());
        };
        if(NotNullCheckUtil.checkNotNull(iUserRole.getVersion())){
            lqw.eq(UserRole::getVersion,iUserRole.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iUserRole.getDeleted())){
            lqw.eq(UserRole::getDeleted,iUserRole.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iUserRole.getCreated())){
            lqw.eq(UserRole::getCreated,iUserRole.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iUserRole.getUpdated())){
            lqw.eq(UserRole::getUpdated,iUserRole.getUpdated());
        };
        return lqw;
    }
}




