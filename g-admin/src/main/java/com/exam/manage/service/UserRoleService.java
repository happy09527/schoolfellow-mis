package com.exam.manage.service;

import com.exam.manage.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.manage.params.UserParam;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
public interface UserRoleService extends IService<UserRole> {

    List<String> getRoleList(Integer userId);

    void updateUserRM(HashMap<String,Object> userParam);
}