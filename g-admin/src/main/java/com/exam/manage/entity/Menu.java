package com.exam.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zx
 * @since 2023-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("g_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer menuId;

    private String component;

    private String path;

    private String redirect;

    private String name;

    private String title;

    private String icon;

    private Boolean hidden;


}
