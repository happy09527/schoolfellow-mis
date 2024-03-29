package com.exam.manage.service;

import com.exam.manage.entity.Info;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.manage.params.InfoParam;
import com.exam.manage.params.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2023-04-04
 */
public interface InfoService extends IService<Info> {
    Result getAllInfo();

    Map<String, Object> getInfoList(String name, String id, String company, String city, String tutor, String major, String degree, Integer pageNumber, Integer pageSize);

    Info getInfoById(Integer id);


    void addInfo(InfoParam infoParam);

    void updateInfo(InfoParam infoParam) throws Exception;

    boolean tableImport(MultipartFile file);

    List<Info> export(String name, String id, String company, String city, String tutor, String degree, String major);
}
