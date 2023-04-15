package com.exam.manage.controller;


import com.exam.manage.entity.User;
import com.exam.manage.params.Result;
import com.exam.manage.params.RoleParam;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.UserService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@Api
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserParam userParam) {
        Map<String, String> map = userService.login(userParam);
        if (map != null) {
            return Result.success(map);
        } else {
            return Result.fail(2222, "用户密码错误");
        }
    }

    @GetMapping("/info")
    public Result getUserInfo(@RequestParam("token") String token) {
        Map<String, Object> map = userService.getUserInfo(token);
        if (map == null) {
            return Result.fail(20002, "用户信息获取失败");
        }
        return Result.success(map);
    }

    @GetMapping("/logout")
    public Result logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return Result.success(null);
    }

    @GetMapping("/list")
    public Result getUserList(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "pageNumber") Integer pageNumber,
                              @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = userService.getUserList(username, pageNumber, pageSize);
        return Result.success(map);
    }

    @PostMapping("/add")
    public Result addUser(@RequestBody UserParam userParam) {
        userService.addUser(userParam);
        Map<String, String> data = new HashMap<>();
        data.put("message", "新增成功");
        return Result.success(data);
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody UserParam userParam) {
        userService.updateUser(userParam);
        Map<String, String> data = new HashMap<>();
        data.put("message", "修改成功");
        return Result.success(data);
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id")Integer id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }
    @GetMapping("/delete/{id}")
    public Result deleteUserById(@PathVariable("id")Integer id){
        userService.removeById(id);
        return Result.success("删除成功");
    }
}