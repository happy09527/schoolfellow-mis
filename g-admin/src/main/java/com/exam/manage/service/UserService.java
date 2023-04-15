package com.exam.manage.service;

import com.exam.manage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.manage.params.RoleParam;
import com.exam.manage.params.UserParam;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
public interface UserService extends IService<User> {
    Map<String ,String> login(UserParam userParam);

    Map<String ,Object> getUserInfo(String token);

    void logout(String token);

    Map<String,Object> getUserList(String username, Integer pageNumber, Integer pageSize);

    void addUser(UserParam userParam);

    void updateUser(UserParam userParam);

    User getUserById(Integer id);

    void deleteById(Integer id);
}
