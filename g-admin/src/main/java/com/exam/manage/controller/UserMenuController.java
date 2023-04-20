package com.exam.manage.controller;


import com.exam.manage.params.Result;
import com.exam.manage.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/user")
public class UserMenuController {
    @Autowired
    private UserMenuService userMenuService;

    @GetMapping("/getMenu")
    public Result getMenuList(@RequestParam(value = "userId") Integer userId){
        List<String> roles = userMenuService.getMenuList(userId);
        return Result.success(roles);
    }


}
