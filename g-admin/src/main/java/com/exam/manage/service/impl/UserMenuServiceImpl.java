package com.exam.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.manage.entity.Menu;
import com.exam.manage.entity.UserMenu;
import com.exam.manage.mapper.UserMenuMapper;
import com.exam.manage.service.MenuService;
import com.exam.manage.service.UserMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements UserMenuService {
    @Resource
    private UserMenuMapper userMenuMapper;

    @Autowired
    private MenuService menuService;

    @Override
    public List<String> getMenuList(Integer userId) {
        LambdaQueryWrapper<UserMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserMenu::getUserId, userId);
        List<UserMenu> userMenus = userMenuMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        for (UserMenu userMenu : userMenus) {
            if (userMenu.getMenuId() == 1) {
                list.add("用户管理");
            } else if (userMenu.getMenuId() == 2) {
                list.add("权限管理");
            }else{
                list.add("批量管理");
            }
        }
        return list;
    }

    @Override
    public List<Menu> getMenuByUserId(Integer userId) {
        LambdaQueryWrapper<UserMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserMenu::getUserId,userId);
        List<UserMenu> list = userMenuMapper.selectList(wrapper);
        List<Integer> idList = new ArrayList<>();
        for(UserMenu userMenu : list){
            idList.add(userMenu.getMenuId());
        }
        List<Menu> menuList = menuService.getMenuList(idList);
        return menuList;
    }
}
