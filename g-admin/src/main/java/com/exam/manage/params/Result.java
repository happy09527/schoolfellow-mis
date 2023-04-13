package com.exam.manage.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhangX
 * @createDate: 2023/4/5
 * @description: 封装结果
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(20000, "success", data);
    }

    public static Result fail(int code, String msg) {
        return new Result(code, msg, null);
    }
}
