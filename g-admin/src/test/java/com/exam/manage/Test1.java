package com.exam.manage;

import com.exam.manage.entity.Info;
import com.exam.manage.mapper.InfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ZhangX
 * @createDate: 2023/4/5
 * @description:
 */
@SpringBootTest
public class Test1 {
    @Resource
    private InfoMapper infoMapper;

    @Test
    public void test1() {
        List<Info> info = infoMapper.selectList(null);
        info.forEach(System.out::println);
        HashMap<String,Object> map  = new HashMap<>();
        map.put("ad","asd");
        map.get("ad");
    }

    public static void main(String[] args) {
        List<Integer> accountIdListOne = new ArrayList<>();
        accountIdListOne.add(1);
        accountIdListOne.add(2);
        accountIdListOne.add(3);

        List<Integer> accountIdListTwo = new ArrayList<>();
        accountIdListTwo.add(3);
        accountIdListTwo.add(4);
        accountIdListTwo.add(5);
        accountIdListTwo.add(6);


        List<Integer> accountIdList = accountIdListOne.stream().filter(accountIdListTwo::contains).collect(Collectors.toList());

        System.out.println(accountIdList.toString());
    }
}
