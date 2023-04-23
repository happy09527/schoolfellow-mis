package com.exam.manage.service;

import com.exam.manage.entity.Menu;
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
public interface MenuService extends IService<Menu> {

    List<Menu> getMenuList(List<Integer> idList);
}
