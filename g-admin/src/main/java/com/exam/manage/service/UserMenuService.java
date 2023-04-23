package com.exam.manage.service;

import com.exam.manage.entity.Menu;
import com.exam.manage.entity.UserMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
public interface UserMenuService extends IService<UserMenu> {

    List<String> getMenuList(Integer userId);
    List<Menu> getMenuByUserId(Integer userId);
}
