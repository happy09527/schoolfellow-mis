package com.exam.manage.mapper;

import com.exam.manage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
public interface UserMapper extends BaseMapper<User> {

    List<String> getRoleByUserId(Integer userId);

    List<User> getUserListByRoleName(String role);

    User selectUserById(Integer id);

    void updateByUserId(Integer userId, String username, String password, String userDesc);

    void deleteUserById(Integer id);
}


