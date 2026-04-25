package com.mdkj.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.component.MyRedis;
import com.mdkj.constant.ML;
import com.mdkj.domain.User;
import com.mdkj.exception.ServiceException;
import com.mdkj.service.UserService;
import com.mdkj.mapper.UserMapper;
import com.mdkj.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.mdkj.util.NotNullCheckUtil;
import org.springframework.web.multipart.MultipartFile;

/**
* 针对表【user(用户表)】的数据库操作Service实现
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MyRedis myRedis;
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
        iUser.setPassword(BCrypt.hashpw(iUser.getPassword(), BCrypt.gensalt()));
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

    @Override
    @Transactional
    public void resetPassword(String ids) {
        String hashpw = BCrypt.hashpw(ML.User.DEFAULT_PASSWORD, BCrypt.gensalt());
        for (String id : ids.split(",")) {
            User user = new User();
            user.setId(Long.valueOf(id));
            user.setPassword(hashpw);
            user.setUpdated(new Date());
            updateById(user);
        }
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        if (!BCrypt.checkpw(oldPassword,user.getPassword())) {
            throw new ServiceException("旧密码错误");
        }
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        user.setUpdated(new Date());
        update(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, String> uploadAvatar(MultipartFile newFile, Long id) {
        // 按主键查询记录
        User user = userMapper.selectById(id);
        if (ObjectUtil.isNull(user)) {
            throw new ServiceException(id + "号用户数据不存在");
        }

        // 备份旧文件名
        String oldFileName = user.getAvatar();

        // 生成新文件名
        String newFileName = null;
        try {
            // 检查文件对象
            if (newFile == null) {
                throw new ServiceException("文件对象为空");
            }
            if (newFile.isEmpty()) {
                throw new ServiceException("文件内容为空");
            }
            if (newFile.getOriginalFilename() == null) {
                throw new ServiceException("原始文件名为空");
            }
            
            newFileName = MinioUtil.randomFilename(newFile);
            if (ObjectUtil.isNull(newFileName)) {
                throw new ServiceException("生成的文件名为空");
            }
        } catch (Exception e) {
            throw new ServiceException("生成文件名失败：" + e.getMessage());
        }

        try {
            // 先上传新文件，成功后再更新数据库，避免数据库脏数据
            MinioUtil.upload(newFile, newFileName, ML.MinIO.AVATAR_DIR, ML.MinIO.BUCKET_NAME);
        } catch (Exception e) {
            throw new ServiceException("MinIO操作失败：" + e.getMessage());
        }

        // DB更新文件名
        user.setAvatar(newFileName);
        user.setUpdated(new Date());
        if (userMapper.updateById(user) <= 0) {
            throw new ServiceException("数据库更新头像文件名失败");
        }

        try {
            // MinIO删除旧文件（默认文件不删除）
            if (ObjectUtil.isNotNull(oldFileName) && !ML.User.DEFAULT_AVATARS.contains(oldFileName)) {
                MinioUtil.delete(oldFileName, ML.MinIO.AVATAR_DIR, ML.MinIO.BUCKET_NAME);
            }
        } catch (Exception e) {
            // 旧文件删除失败不影响主流程
            log.warn("删除旧头像失败 oldFileName={} error={}", oldFileName, e.getMessage());
        }

        String avatarUrl = MinioUtil.publicUrl(newFileName, ML.MinIO.AVATAR_DIR, ML.MinIO.BUCKET_NAME);
        Map<String, String> result = new HashMap<>(2);
        result.put("fileName", newFileName);
        result.put("avatarUrl", avatarUrl);
        return result;
    }

    @Override
    public Map<String, Object> accountLogin(String username, String password) {
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            throw new ServiceException("账号或密码为空");
        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username).eq(User::getDeleted, 0).last("limit 1");
        User user = getOne(lqw);
        if (ObjectUtil.isNull(user) || !BCrypt.checkpw(password, user.getPassword())) {
            throw new ServiceException("账号或密码错误");
        }
        return buildLoginData(user, "账号密码登录成功");
    }

    @Override
    public Map<String, Object> phoneLogin(String phone, String vcode) {
        if (StrUtil.isBlank(phone) || StrUtil.isBlank(vcode)) {
            throw new ServiceException("手机号或验证码为空");
        }
        String cacheVcode = myRedis.get(ML.Redis.LOGIN_VCODE_PREFIX + phone);
        if (!StrUtil.equals(vcode, cacheVcode)) {
            throw new ServiceException("登录验证码错误");
        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getPhone, phone).eq(User::getDeleted, 0).last("limit 1");
        User user = getOne(lqw);
        if (ObjectUtil.isNull(user)) {
            throw new ServiceException("手机号未注册");
        }
        return buildLoginData(user, "手机号登录成功");
    }

    @Override
    public void rebindPhone(Long id, String oldPhone, String newPhone, String oldVcode, String newVcode) {
        if (ObjectUtil.isNull(id) || StrUtil.hasBlank(oldPhone, newPhone, oldVcode, newVcode)) {
            throw new ServiceException("换绑参数不完整");
        }
        User user = getById(id);
        if (ObjectUtil.isNull(user) || Objects.equals(user.getDeleted(), 1)) {
            throw new ServiceException("用户不存在");
        }
        if (!StrUtil.equals(user.getPhone(), oldPhone)) {
            throw new ServiceException("原手机号不匹配");
        }
        String oldCacheVcode = myRedis.get(ML.Redis.UNBOUND_VCODE_PREFIX + oldPhone);
        if (!StrUtil.equals(oldVcode, oldCacheVcode)) {
            throw new ServiceException("旧手机号验证码错误");
        }
        String newCacheVcode = myRedis.get(ML.Redis.BOUND_VCODE_PREFIX + newPhone);
        if (!StrUtil.equals(newVcode, newCacheVcode)) {
            throw new ServiceException("新手机号验证码错误");
        }
        LambdaQueryWrapper<User> repeatQuery = new LambdaQueryWrapper<>();
        repeatQuery.eq(User::getPhone, newPhone).eq(User::getDeleted, 0).ne(User::getId, id).last("limit 1");
        if (ObjectUtil.isNotNull(getOne(repeatQuery))) {
            throw new ServiceException("新手机号已被占用");
        }
        User updateEntity = new User();
        updateEntity.setId(id);
        updateEntity.setPhone(newPhone);
        updateEntity.setUpdated(new Date());
        updateById(updateEntity);
    }

    @Override
    public Map<String, Object> statistics() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getDeleted, 0);
        long total = count(lqw);
        LambdaQueryWrapper<User> maleQuery = new LambdaQueryWrapper<>();
        maleQuery.eq(User::getDeleted, 0).eq(User::getGender, ML.User.MALE);
        long male = count(maleQuery);
        LambdaQueryWrapper<User> femaleQuery = new LambdaQueryWrapper<>();
        femaleQuery.eq(User::getDeleted, 0).eq(User::getGender, ML.User.FEMALE);
        long female = count(femaleQuery);
        LambdaQueryWrapper<User> secretQuery = new LambdaQueryWrapper<>();
        secretQuery.eq(User::getDeleted, 0).eq(User::getGender, ML.User.SECRET);
        long secret = count(secretQuery);
        Map<String, Object> result = new HashMap<>(4);
        result.put("total", total);
        result.put("male", male);
        result.put("female", female);
        result.put("secret", secret);
        return result;
    }

    private Map<String, Object> buildLoginData(User user, String loginType) {
        String token = IdUtil.fastSimpleUUID();
        myRedis.setEx(token, String.valueOf(user.getId()), 30, TimeUnit.MINUTES);
        Map<String, Object> result = new HashMap<>(4);
        result.put("token", token);
        result.put("id", user.getId());
        result.put("username", user.getUsername());
        result.put("loginType", loginType);
        return result;
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




