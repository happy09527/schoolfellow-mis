package com.exam.manage.controller;


import com.exam.manage.params.Result;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/user")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/getRole")
    public Result getRoleList(@RequestParam(value = "userId") Integer userId) {
        List<String> roles = userRoleService.getRoleList(userId);
        return Result.success(roles);
    }
    @PostMapping("/updateRM")
    public Result updateUserRM(@RequestBody HashMap<String,Object> userParam){
        userRoleService.updateUserRM(userParam);
        return Result.success("修改成功");
    }
}
