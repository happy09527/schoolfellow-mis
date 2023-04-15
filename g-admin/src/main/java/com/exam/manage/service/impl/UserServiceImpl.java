package com.exam.manage.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.manage.entity.User;
import com.exam.manage.mapper.UserMapper;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.manage.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @author: ZhangX
     * @date: 2023/4/13 15:03
     * @param: [userParam]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @description: 登录，JWT验证
     **/
    @Override
    public Map<String, String> login(UserParam userParam) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getUsername, userParam.getUsername());
        User user = this.getOne(wrapper);
        if (user != null && passwordEncoder.matches(userParam.getPassword(), user.getPassword())) {
            Map<String, String> map = new HashMap<>();
            String token = jwtUtil.createToken(user);
            map.put("token", token);
            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        Map<String, Object> map = new HashMap<>();
        User user = null;
        try {
            user = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.isEmpty(user)) {
            return null;
        }
        map.put("name", user.getUsername());
//        List<String> roleList = this.baseMapper.getRoleByUserId(user.getUserId());
//        map.put("roles", roleList);
        return map;
    }

    @Override
    public void logout(String token) {
        return;
    }

    @Override
    public Map<String, Object> getUserList(String username, Integer pageNumber, Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.eq(User::getUsername, username);
            Page<User> page = new Page<>(pageNumber, pageSize);
            this.page(page, wrapper);
            Map<String, Object> data = new HashMap<>();
            data.put("total", page.getTotal());
            data.put("rows", page.getRecords());
            return data;
        } else {
            wrapper.eq(!StringUtils.isEmpty(username), User::getUsername, username);
            Page<User> page = new Page<>(pageNumber, pageSize);
            this.page(page, wrapper);
            Map<String, Object> data = new HashMap<>();
            data.put("total", page.getTotal());
            data.put("rows", page.getRecords());
            return data;
        }
    }

    @Override
    public void addUser(UserParam userParam) {
        User user = new User();
        user.setUsername(userParam.getUsername());
        user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        user.setUserDesc(userParam.getDesc());
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserParam userParam) {
        userMapper.updateByUserId(userParam.getUserId(), userParam.getUsername(), passwordEncoder.encode(userParam.getPassword()), userParam.getDesc());
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteUserById(id);
    }
}