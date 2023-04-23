package com.exam.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.manage.entity.Menu;
import com.exam.manage.mapper.MenuMapper;
import com.exam.manage.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuList(List<Integer> idList) {
        List<Menu> menuList = new ArrayList<>();
        for(Integer id : idList){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Menu::getMenuId,id);
            Menu menu = menuMapper.selectOne(wrapper);
            menuList.add(menu);
        }
        return menuList;
    }
}
