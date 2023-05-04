package com.exam.manage.config;

import com.alibaba.fastjson2.JSON;
import com.exam.manage.entity.User;
import com.exam.manage.params.Result;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.UserService;
import com.exam.manage.util.JwtUtil;
import com.exam.manage.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ZhangX
 * @createDate: 2023/4/14
 * @description: 拦截器
 * 在执行controller方法(Handler)之前进行执行
 * 1. 需要判断 请求的接口路径 是否为 HandlerMethod (controller方法)
 * 2. 判断 token是否为空，如果为空 未登录
 * 3. 如果token 不为空，登录验证 loginService checkToken
 * 4. 如果认证成功 放行即可
 */

@Component
@Slf4j
public class JwtInterceptorConfig implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //handler 可能是 RequestResourceHandler springboot 程序 访问静态资源 默认去classpath下的static目录去查询
            return true;
        }

        String token = request.getHeader("X-Token");
        String url = request.getRequestURI();
        if(url.equals("/info/import")){
            return true;
        }
        if (!StringUtils.isEmpty(token)) {
            try {
                User user = jwtUtil.parseToken(token, User.class);
                UserParam userParam = new UserParam();
                userParam.setUsername(user.getUsername());
                userParam.setRoleList(userService.getRolesById(user.getUserId()));
                log.debug(request.getRequestURI());
                ThreadLocalUtil.put(userParam);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        log.debug(request.getRequestURI() + " 禁止访问...");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.fail(20003, "jwt令牌无效，请重新登录")));
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除 ThreadLocal中用完的信息 会有内存泄漏的风险
        ThreadLocalUtil.remove();
    }
}
