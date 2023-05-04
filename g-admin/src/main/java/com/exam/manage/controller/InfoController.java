package com.exam.manage.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.exam.manage.config.ExcelListener;
import com.exam.manage.entity.Info;
import com.exam.manage.mapper.InfoMapper;
import com.exam.manage.params.InfoDto;
import com.exam.manage.params.Result;
import com.exam.manage.params.InfoParam;
import com.exam.manage.service.InfoService;
import com.exam.manage.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;
    @Autowired
    private InfoMapper infoMapper;

    //    @GetMapping("/all")
//    public Result getAllInfo(){
//        return infoService.getAllInfo();
//    }
    @GetMapping("/list")
    public Result getInfoList(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "company", required = false) String company,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "tutor", required = false) String tutor,
                              @RequestParam(value = "degree", required = false) String degree,
                              @RequestParam(value = "major", required = false) String major,
                              @RequestParam(value = "pageNumber") Integer pageNumber,
                              @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = infoService.getInfoList(name, id, company, city, tutor, major, degree, pageNumber, pageSize);
        System.out.println(name + id + company + city);
        return Result.success(map);
    }

    @GetMapping("/get/{id}")
    public Result getInfoById(@PathVariable("id") Integer id) {
        Info info = infoService.getInfoById(id);
        return Result.success(info);
    }


    @PostMapping("/add")
    public Result addUser(@RequestBody InfoParam infoParam) {
        infoService.addInfo(infoParam);
        Map<String, String> data = new HashMap<>();
        data.put("message", "新增成功");
        return Result.success(data);
    }

    @PostMapping("/update")
    public Result updateInfo(@RequestBody InfoParam infoParam) {
        infoService.updateInfo(infoParam);
        Map<String, String> data = new HashMap<>();
        data.put("message", "修改成功");
        return Result.success(data);
    }

    @GetMapping("/delete/{id}")
    public Result deleteInfoById(@PathVariable("id") Integer id) {
        infoService.removeById(id);
        return Result.success("删除成功");
    }

    @GetMapping("/1111")
    public Result get1(HttpServletResponse response, HttpServletRequest request) {
        return Result.success("删除成功");
    }

    /**
     * 表格导入
     */
    @PostMapping("/import")
    public Result tableImport(@RequestParam("file") MultipartFile file) throws IOException {
        boolean result = infoService.tableImport(file);
        if (result) {
            return Result.success("成功");
        } else {
            return Result.fail(20003, "插入失败");
        }
    }

    @GetMapping("/export")
    public Result exportTable(HttpServletResponse response, HttpServletRequest request,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "company", required = false) String company,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "tutor", required = false) String tutor,
                              @RequestParam(value = "degree", required = false) String degree,
                              @RequestParam(value = "major", required = false) String major) throws IOException {
        //请求头
        String fileName = URLEncoder.encode("数据信息表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");

        //逻辑代码获取数据（自定义修改）
        List<Info> list = infoService.export(name, id, company, city, tutor, degree, major);
        List<InfoDto> productDOS = BeanUtil.listCopyTo(list, InfoDto.class);
        System.out.println(productDOS);
        //获取输出流
        OutputStream outputStream = response.getOutputStream();
        try {
            //导出
            EasyExcel.write(outputStream, InfoDto.class).sheet().doWrite(productDOS);
            outputStream.flush();
        } catch (IOException e) {

        } finally {
            outputStream.close();
        }
        return null;
    }
}