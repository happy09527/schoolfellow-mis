package com.exam.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.manage.entity.Info;
import com.exam.manage.mapper.InfoMapper;
import com.exam.manage.params.InfoParam;
import com.exam.manage.params.Result;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.manage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
    public Map<String, Object> getInfoList(String name, String id, String company, String city, String tutor, String major, String degree, Integer pageNumber, Integer pageSize) {
        UserParam userParam = ThreadLocalUtil.get();
        System.out.println(userParam);
        LambdaQueryWrapper<Info> wrapper = new LambdaQueryWrapper<>();
//        wrapper.inSql(Info::get);
        wrapper.like(StringUtils.hasLength(id), Info::getIdSchool, id);
        wrapper.like(StringUtils.hasLength(name), Info::getName, name);
        wrapper.eq(StringUtils.hasLength(company), Info::getCCompany, company);
        wrapper.eq(StringUtils.hasLength(city), Info::getCCity, city);
        wrapper.eq(StringUtils.hasLength(tutor), Info::getSTutor, tutor);
        wrapper.eq(StringUtils.hasLength(major), Info::getSMajor, major);
        wrapper.eq(StringUtils.hasLength(degree), Info::getDegree, degree);
        wrapper.orderByAsc(Info::getId);
        if (userParam.getRoleList().size() == 1) {
            wrapper.inSql(Info::getDegree, "'" + userParam.getRoleList().get(0) + "'");
        } else if (userParam.getRoleList().size() == 2) {
            wrapper.inSql(Info::getDegree, "'" + userParam.getRoleList().get(0) + "' ,'" + userParam.getRoleList().get(1) + "'");
        } else if (userParam.getRoleList().size() == 3) {
            wrapper.inSql(Info::getDegree, "'" + userParam.getRoleList().get(0) + "' ,'" + userParam.getRoleList().get(1) + "'," + "'" + userParam.getRoleList().get(2) + "'");
        } else {
            wrapper.inSql(Info::getDegree, "'" + userParam.getRoleList().get(0) + "' ,'" + userParam.getRoleList().get(1) + "'," + "'" + userParam.getRoleList().get(2) + "' ,'" + userParam.getRoleList().get(3) + "'");
        }
        Page<Info> page = new Page<>(pageNumber, pageSize);
        this.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return data;
    }

    @Override
    public Info getInfoById(Integer id) {
        Info info = infoMapper.selectById(id);
        return info;
    }

    @Override
    public void addInfo(InfoParam infoParam) {
        Info info = new Info(infoParam.getId(), infoParam.getName(), infoParam.getSex(), infoParam.getIdSchool(),
                infoParam.getIdCard(), infoParam.getNation(), infoParam.getPolitical(), infoParam.getNativer(), infoParam.getDegree(),
                infoParam.getSTime(), infoParam.getSMajor(), infoParam.getSClass(), infoParam.getSTutor(), infoParam.getGTime(), infoParam.getGDestination(),
                infoParam.getCCompany(), infoParam.getCJob(), infoParam.getCCity(), infoParam.getTelephone(), infoParam.getEmail(),
                infoParam.getPostalAddress(), infoParam.getRemark());
        infoMapper.insert(info);
        System.out.println(info);
    }

    @Override
    public void updateInfo(InfoParam infoParam) {
        Info info = new Info(infoParam.getId(), infoParam.getName(), infoParam.getSex(), infoParam.getIdSchool(),
                infoParam.getIdCard(), infoParam.getNation(), infoParam.getPolitical(), infoParam.getNativer(), infoParam.getDegree(),
                infoParam.getSTime(), infoParam.getSMajor(), infoParam.getSClass(), infoParam.getSTutor(), infoParam.getGTime(), infoParam.getGDestination(),
                infoParam.getCCompany(), infoParam.getCJob(), infoParam.getCCity(), infoParam.getTelephone(), infoParam.getEmail(),
                infoParam.getPostalAddress(), infoParam.getRemark());
        infoMapper.updateById(info);
    }

    @Override
    public void saveImportTask(List<Info> listData) {
        for (Info info : listData) {
            infoMapper.insert(info);
        }
    }
}
