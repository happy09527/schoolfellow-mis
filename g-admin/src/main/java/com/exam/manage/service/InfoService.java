package com.exam.manage.service;

import com.exam.manage.entity.Info;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.manage.params.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zx
 * @since 2023-04-04
 */
public interface InfoService extends IService<Info> {
    Result getAllInfo();

}
