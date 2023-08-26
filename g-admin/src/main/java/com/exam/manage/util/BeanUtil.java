package com.exam.manage.util;


import com.exam.manage.entity.Info;
import com.exam.manage.params.InfoDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author: ZhangX
 * @createDate: 2023/5/1
 * @description:
 */

public class BeanUtil extends BeanUtils {
    public static List<InfoDto> listCopyTo(List<Info> source) {
        if (source.size() == 0) {
            return Collections.emptyList();
        } else {
            List<InfoDto> res = new ArrayList(source.size());
            Iterator var3 = source.iterator();

            while(var3.hasNext()) {
                Info o = (Info) var3.next();
                InfoDto e = new InfoDto();
                BeanUtils.copyProperties(o, e);
                e.setSchooltime(String.valueOf(o.getSchoolTime()));
                e.setGradtime(String.valueOf(o.getGradTime()));
                e.setSchoolmajor(o.getSchoolMajor());
                e.setSchoolclass(o.getSchoolClass());
                e.setSchooltutor(o.getSchoolTutor());
                e.setGraddestination(o.getGradDestination());
                e.setCurcompany(o.getCurCompany());
                e.setCurcity(o.getCurCity());
                e.setCurjob(o.getCurJob());
                res.add(e);
            }
            return res;
        }
    }
}

