package com.exam.manage.service.impl;

import com.exam.manage.entity.Info;
import com.exam.manage.mapper.InfoMapper;
import com.exam.manage.params.Result;
import com.exam.manage.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2023-04-04
 */
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {
    @Resource
    private InfoMapper infoMapper;

    @Override
    public Result getAllInfo() {
        List<Info> info = infoMapper.selectList(null);
        info.forEach(System.out::println);
        return Result.success(info);
    }
}
