package com.exam.manage.mapper;

import com.exam.manage.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.manage.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2023-04-04
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {


}
