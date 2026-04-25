package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.RoleMenu;
import com.mdkj.exception.ServiceException;
import com.mdkj.service.RoleMenuService;
import com.mdkj.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【role_menu(角色菜单关系表)】的数据库操作Service实现
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService{

    @Override
    public List<RoleMenu> selectAll() {
        LambdaQueryWrapper <RoleMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RoleMenu::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<RoleMenu> selectList(RoleMenu iRoleMenu) {
        LambdaQueryWrapper <RoleMenu> lqw = this.lqw(iRoleMenu);
        lqw.eq(RoleMenu::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(RoleMenu iRoleMenu) {
        save(iRoleMenu);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<RoleMenu> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            RoleMenu entity  = new RoleMenu();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(RoleMenu iRoleMenu) {
        updateById(iRoleMenu);
    }


    @Override
    public IPage<RoleMenu> pageList(RoleMenu iRoleMenu, Integer page, Integer size) {
        Page<RoleMenu> pag = new Page<>(page,size);
        LambdaQueryWrapper<RoleMenu> lqw = lqw(iRoleMenu);
        lqw.eq(RoleMenu::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<RoleMenu> getExcelData() {
        return selectAll();
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RoleMenu::getFkRoleId, roleId).eq(RoleMenu::getDeleted, 0);
        List<RoleMenu> list = list(lqw);
        return list.stream().map(RoleMenu::getFkMenuId).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenus(Long roleId, List<Long> menuIds) {
        if (Objects.isNull(roleId)) {
            throw new ServiceException("角色ID不能为空");
        }
        LambdaQueryWrapper<RoleMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RoleMenu::getFkRoleId, roleId).eq(RoleMenu::getDeleted, 0);
        remove(lqw);
        if (menuIds == null || menuIds.isEmpty()) {
            return;
        }
        List<RoleMenu> saveList = new ArrayList<>(menuIds.size());
        for (Long menuId : menuIds) {
            RoleMenu rm = new RoleMenu();
            rm.setFkRoleId(roleId);
            rm.setFkMenuId(menuId);
            rm.setDeleted(0);
            saveList.add(rm);
        }
        saveBatch(saveList);
    }

    public LambdaQueryWrapper<RoleMenu> lqw(RoleMenu iRoleMenu){
        LambdaQueryWrapper <RoleMenu> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getId())){
            lqw.eq(RoleMenu::getId,iRoleMenu.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getFkRoleId())){
            lqw.eq(RoleMenu::getFkRoleId,iRoleMenu.getFkRoleId());
        };
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getFkMenuId())){
            lqw.eq(RoleMenu::getFkMenuId,iRoleMenu.getFkMenuId());
        };
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getVersion())){
            lqw.eq(RoleMenu::getVersion,iRoleMenu.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getDeleted())){
            lqw.eq(RoleMenu::getDeleted,iRoleMenu.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getCreated())){
            lqw.eq(RoleMenu::getCreated,iRoleMenu.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iRoleMenu.getUpdated())){
            lqw.eq(RoleMenu::getUpdated,iRoleMenu.getUpdated());
        };
        return lqw;
    }
}




