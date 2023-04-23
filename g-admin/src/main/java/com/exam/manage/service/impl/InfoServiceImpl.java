package com.exam.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.manage.entity.Info;
import com.exam.manage.entity.User;
import com.exam.manage.mapper.InfoMapper;
import com.exam.manage.params.Result;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getInfoList(String name, Integer pageNumber, Integer pageSize) {
        LambdaQueryWrapper<Info> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(name),Info::getName,name);
        wrapper.orderByDesc(Info::getId);

        Page<Info> page = new Page<>(pageNumber,pageSize);
        this.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return data;
    }
}
