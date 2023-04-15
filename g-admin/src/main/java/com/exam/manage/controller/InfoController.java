package com.exam.manage.controller;


import com.exam.manage.params.Result;
import com.exam.manage.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/all")
    public Result getAllInfo(){
        return infoService.getAllInfo();
    }

}
