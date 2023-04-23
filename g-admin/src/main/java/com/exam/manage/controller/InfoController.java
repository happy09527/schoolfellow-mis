package com.exam.manage.controller;


import com.exam.manage.params.Result;
import com.exam.manage.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
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
    @GetMapping("/all")
    public Result getInfoList(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "pageNumber") Integer pageNumber,
                              @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = infoService.getInfoList(name, pageNumber, pageSize);
        return Result.success(map);
    }

}
