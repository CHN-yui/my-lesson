package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.User;
import com.mdkj.service.UserService;
import com.mdkj.mapper.UserMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【user(用户表)】的数据库操作Service实现
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public List<User> selectAll() {
        LambdaQueryWrapper <User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<User> selectList(User iUser) {
        LambdaQueryWrapper <User> lqw = this.lqw(iUser);
        lqw.eq(User::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(User iUser) {
        save(iUser);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<User> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            User entity  = new User();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(User iUser) {
        updateById(iUser);
    }


    @Override
    public IPage<User> pageList(User iUser, Integer page, Integer size) {
        Page<User> pag = new Page<>(page,size);
        LambdaQueryWrapper<User> lqw = lqw(iUser);
        lqw.eq(User::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<User> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<User> lqw(User iUser){
        LambdaQueryWrapper <User> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iUser.getId())){
            lqw.eq(User::getId,iUser.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getUsername())){
            lqw.eq(User::getUsername,iUser.getUsername());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getPassword())){
            lqw.eq(User::getPassword,iUser.getPassword());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getNickname())){
            lqw.eq(User::getNickname,iUser.getNickname());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getEmail())){
            lqw.eq(User::getEmail,iUser.getEmail());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getProvince())){
            lqw.eq(User::getProvince,iUser.getProvince());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getRealname())){
            lqw.eq(User::getRealname,iUser.getRealname());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getAvatar())){
            lqw.eq(User::getAvatar,iUser.getAvatar());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getZodiac())){
            lqw.eq(User::getZodiac,iUser.getZodiac());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getPhone())){
            lqw.eq(User::getPhone,iUser.getPhone());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getIdcard())){
            lqw.eq(User::getIdcard,iUser.getIdcard());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getGender())){
            lqw.eq(User::getGender,iUser.getGender());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getAge())){
            lqw.eq(User::getAge,iUser.getAge());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getInfo())){
            lqw.eq(User::getInfo,iUser.getInfo());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getVersion())){
            lqw.eq(User::getVersion,iUser.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getDeleted())){
            lqw.eq(User::getDeleted,iUser.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getCreated())){
            lqw.eq(User::getCreated,iUser.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iUser.getUpdated())){
            lqw.eq(User::getUpdated,iUser.getUpdated());
        };
        return lqw;
    }
}




