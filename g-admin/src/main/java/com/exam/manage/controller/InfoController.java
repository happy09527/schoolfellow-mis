package com.exam.manage.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.exam.manage.entity.Info;
import com.exam.manage.entity.User;
import com.exam.manage.params.Result;
import com.exam.manage.params.InfoParam;
import com.exam.manage.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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


    /**
     * 任务导入    使用hutool工具类导入Excel文件
     *
     * @return
     */
    @PostMapping("/import")
    public Result fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            //使用hutool工具类导入Excel文件
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            //读取excel中的数据，与User实体类一一对应
            List<Info> listData = reader.readAll(Info.class);
            //批量存入数据库中
            infoService.saveImportTask(listData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success("成功上传");
    }


    /**
     * 统计导出 文件下载
     * @return
     */
//    @GetMapping(value = "/export")
//    public void statisticsExport(
//            @RequestParam("province") String province,
//            @RequestParam("city") String city,
//            @RequestParam("counter") String counter,
//            @RequestParam("startTime") Long startTime,
//            @RequestParam("endTime") Long endTime,
//            @RequestParam("orderByType") String orderByType,
//            HttpServletResponse response) {
//        try {
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf-8");
//            String fileName = URLEncoder.encode("统计", "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//            FastDateFormat fastDateFormat = FastDateFormat.getInstance(DatePattern.NORM_DATETIME_PATTERN, TimeZone.getTimeZone("Asia/Shanghai"));
//            String startTimeStr = fastDateFormat.format(DateUtil.beginOfDay(new DateTime(startTime, TimeZone.getTimeZone("Asia/Shanghai"))));
//            String endTimeStr = fastDateFormat.format(DateUtil.endOfDay(new DateTime(endTime, TimeZone.getTimeZone("Asia/Shanghai"))));
//            //  DemoDTO 查询条件入参
//            DemoDto dto= new DemoDto();
//            dto.setProvince(province);
//            dto.setCity(city);
//            dto.setCounter(counter);
//            dto.setStartTime(startTimeStr);
//            dto.setEndTime(endTimeStr);
//            dto.setOrderByType(orderByType);
//            //根据查询条件查询数据库---把需要导出的数据放到list中
//            List<DemoVO> list = task.findStatisByParams(DemoDto);
//            // 这里需要设置不关闭流
//            String dateTitle = "时间段：" + fastDateFormat.format(new DateTime(startTime,TimeZone.getTimeZone("Asia/Shanghai"))) + "至" + DateUtil.formatDate(new DateTime(endTime, TimeZone.getTimeZone("Asia/Shanghai")));
//            String rangeTitle = "范围:" +
//                    (StrUtil.isBlank(province) ? "全部" : province) + "/" +
//                    (StrUtil.isBlank(city) ? "全部" : city) + "/" +
//                    (StrUtil.isBlank(counter) ? "全部" : counter);
//            EasyExcel.write(response.getOutputStream(), ClientDetailStatisVO.class)
//                    .head(ClientDetailStatisVO.head(dateTitle, rangeTitle))
//                    .autoCloseStream(Boolean.FALSE).sheet("统计")
//                    //上面从数据库查出来的数据
//                    .doWrite(list);
//        } catch (Exception e) {
//            // 重置response
//            response.reset();
//            throw new CustomException(Result.Status.INVALID_PARAM);
//
//        }
//    }

}
