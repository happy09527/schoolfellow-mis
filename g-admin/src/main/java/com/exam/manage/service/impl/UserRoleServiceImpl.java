package com.exam.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.manage.entity.UserMenu;
import com.exam.manage.entity.UserRole;
import com.exam.manage.mapper.UserMenuMapper;
import com.exam.manage.mapper.UserRoleMapper;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private UserMenuMapper userMenuMapper;

    @Override
    public List<String> getRoleList(Integer userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            if (userRole.getLevelId() == 1) {
                list.add("本科");
            } else if (userRole.getLevelId() == 2) {
                list.add("学术型硕士");
            } else if (userRole.getLevelId() == 3) {
                list.add("专业型硕士");
            } else {
                list.add("博士");
            }
        }
        return list;
    }

    @Override
    public void updateUserRM(HashMap<String, Object> userInfo) {
        System.out.println(userInfo);
        UserParam userParam = new UserParam();
        userParam.setUserId((Integer) userInfo.get("user"));
        userParam.setRoleList((List<String>) userInfo.get("roleIdList"));
        userParam.setMenuList((List<String>) userInfo.get("menuIdList"));

        LambdaQueryWrapper<UserRole> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(UserRole::getUserId, userParam.getUserId());
        userRoleMapper.delete(roleWrapper);
        LambdaQueryWrapper<UserMenu> menuWrapper = new LambdaQueryWrapper<>();
        menuWrapper.eq(UserMenu::getUserId, userParam.getUserId());
        userMenuMapper.delete(menuWrapper);


        for (String role : userParam.getRoleList()) {
            if (role.equals("本科")) {
                userRoleMapper.insert(new UserRole(null, userParam.getUserId(), 1));
            } else if (role.equals("学术型硕士")) {
                userRoleMapper.insert(new UserRole(null, userParam.getUserId(), 2));
            } else if (role.equals("专业型硕士")) {
                userRoleMapper.insert(new UserRole(null, userParam.getUserId(), 3));
            } else {
                userRoleMapper.insert(new UserRole(null, userParam.getUserId(), 4));
            }
        }

        for (String menu : userParam.getMenuList()) {
            if (menu.equals("用户管理")) {
                userMenuMapper.insert(new UserMenu(null, userParam.getUserId(), 1));
            } else if (menu.equals("权限管理")) {
                userMenuMapper.insert(new UserMenu(null, userParam.getUserId(), 2));
            } else {
                userMenuMapper.insert(new UserMenu(null, userParam.getUserId(), 3));
            }
        }
    }
}
