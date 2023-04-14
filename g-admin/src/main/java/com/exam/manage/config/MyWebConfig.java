package com.exam.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: ZhangX
 * @createDate: 2023/4/14
 * @description:
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptorConfig jwtInterceptorConfig;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8001")
//                .allowedOriginPatterns("https://180.76.149.47")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtInterceptorConfig);
        registration.addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/user/info",
                        "/user/logout",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**");
    }
}
