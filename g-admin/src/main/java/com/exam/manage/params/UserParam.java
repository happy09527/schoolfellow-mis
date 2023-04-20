package com.exam.manage.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ZhangX
 * @createDate: 2023/4/6
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {
    private Integer userId;
    private String username;
    private String password;
    private List<String> roleList;
    private List<String> menuList;
    private String desc;
}
