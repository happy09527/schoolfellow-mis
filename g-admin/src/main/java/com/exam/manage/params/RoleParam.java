package com.exam.manage.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhangX
 * @createDate: 2023/4/5
 * @description:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleParam {
    private String name;
    private String password;
}
