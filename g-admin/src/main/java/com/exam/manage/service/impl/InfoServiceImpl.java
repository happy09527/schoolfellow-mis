package com.exam.manage.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.manage.config.ExcelListener;
import com.exam.manage.entity.Info;
import com.exam.manage.mapper.InfoMapper;
import com.exam.manage.params.InfoDto;
import com.exam.manage.params.InfoParam;
import com.exam.manage.params.Result;
import com.exam.manage.params.UserParam;
import com.exam.manage.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.manage.util.EasyExcelUtil;
import com.exam.manage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public boolean tableImport(MultipartFile file) {
        int outcome = 1;
        try {
            //获取文件名
            String filename = file.getOriginalFilename();
            //获取文件流
            InputStream inputStream = file.getInputStream();
            //实例化实现了AnalysisEventListener接口的类
            ExcelListener listener = new ExcelListener();

            EasyExcelFactory.read(inputStream, InfoDto.class, listener).headRowNumber(1).build().readAll();
            //获取数据
            List<Object> list = listener.getDatas();

            if (list.size() >= 1) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                    InfoDto info = (InfoDto) list.get(i);
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate end = LocalDate.parse(info.getGtime().substring(0,4) + "-06-01", fmt);
                    LocalDate start = LocalDate.parse(info.getStime().substring(0,4) + "-09-01", fmt);
                    Info info1 = new Info(null, info.getName(), info.getSex(), info.getIdSchool(), info.getIdCard(), info.getNation()
                            , info.getPolitical(), info.getNativer(), info.getDegree(), start, info.getSmajor(), info.getSclass(), info.getStutor(), end, info.getGdestination()
                            , info.getCcompany(), info.getCjob(), info.getCcity(), info.getTelephone(), info.getEmail(), info.getPostalAddress(), info.getRemark());
                    outcome &= infoMapper.insert(info1);
                }
            } else {
                outcome &= 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            outcome &= 0;
        }
        return outcome == 1;
    }

    @Override
    public List<Info> export(String name, String id, String company, String city, String tutor, String degree, String major) {
        UserParam userParam = ThreadLocalUtil.get();
        System.out.println(userParam);
        LambdaQueryWrapper<Info> wrapper = new LambdaQueryWrapper<>();
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
        List<Info> infoList = infoMapper.selectList(wrapper);
        return infoList;
    }
}
