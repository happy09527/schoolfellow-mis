package com.exam.manage;

import com.exam.manage.entity.User;
import com.exam.manage.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: ZhangX
 * @createDate: 2023/4/13
 * @description:
 */
@SpringBootTest
public class JWTUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setUserDesc("12399988877");
        String token = jwtUtil.createToken(user);
        System.out.println(token);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YzYzNWY2Ni1iNDEyLTQ3YTUtODQxMi0wNGM1OWEwYmE3ZTIiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY3Nzg5ODIxMSwiZXhwIjoxNjc3OTAwMDExfQ.CxkspQgN-f7KDtxOpok4c9kdSdIjfXb8z9MdjMgRjlk";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void testParseJwt2(){
        String token = "1yJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YzYzNWY2Ni1iNDEyLTQ3YTUtODQxMi0wNGM1OWEwYmE3ZTIiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY3Nzg5ODIxMSwiZXhwIjoxNjc3OTAwMDExfQ.CxkspQgN-f7KDtxOpok4c9kdSdIjfXb8z9MdjMgRjlk";
        User user = jwtUtil.parseToken(token,User.class);
        System.out.println(user);
    }
}
