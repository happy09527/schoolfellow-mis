package com.exam.manage.util;

import com.exam.manage.entity.User;
import com.exam.manage.params.UserParam;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZhangX
 * @createDate: 2023/4/27
 * @description:
 */

public class ThreadLocalUtil {
    private ThreadLocalUtil() {

    }

    private static final ThreadLocal<UserParam> LOCAL = new ThreadLocal<>();

    public static void put(UserParam user) {
        LOCAL.set(user);
    }

    public static UserParam get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
