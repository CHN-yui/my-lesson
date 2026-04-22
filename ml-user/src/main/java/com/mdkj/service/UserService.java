package com.mdkj.service;

import com.mdkj.domain.User;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


/**
* 针对表【user(用户表)】的数据库操作Service
*/
public interface UserService extends IService<User>{
    /**
    * 查询全部
    */
    List<User> selectAll();

    /**
    * 条件查询
    */
    List<User> selectList(User iUser);

    /**
    * 新增
    */
    void insert(User iUser);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(User iUser);

    /**
    * 分页查询
    */
    IPage<User> pageList(User iUser, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<User> getExcelData();

    /**
     * 重置密码
     */
    void resetPassword(String ids);

    /**
     * 修改密码
     */
    void updatePassword(String oldPassword, String newPassword,String id);

    /**
     * 上传用户头像
     *
     * @param newFile 头像文件
     * @param id      用户主键
     * @return 上传结果（文件名+访问URL）
     */
    Map<String, String> uploadAvatar(MultipartFile newFile, Long id);

    /**
     * 账号密码登录
     */
    Map<String, Object> accountLogin(String username, String password);

    /**
     * 手机号验证码登录
     */
    Map<String, Object> phoneLogin(String phone, String vcode);

    /**
     * 换绑手机号
     */
    void rebindPhone(Long id, String oldPhone, String newPhone, String oldVcode, String newVcode);

    /**
     * 用户统计
     */
    Map<String, Object> statistics();

}
