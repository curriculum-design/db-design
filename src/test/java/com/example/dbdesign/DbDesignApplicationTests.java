package com.example.dbdesign;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbDesignApplicationTests {

    @Test
    void contextLoads() {
        DateTime parse = DateUtil.date(System.currentTimeMillis());
        System.out.println(parse);
    }

}
