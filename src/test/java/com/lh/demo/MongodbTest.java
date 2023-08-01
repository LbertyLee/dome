package com.lh.demo;

import com.lh.demo.common.utils.mongodb.MongodbUtils;
import com.lh.demo.common.utils.uuid.IdUtils;
import com.lh.demo.system.domain.SysUser;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongodbTest {
    @Resource
    private MongodbUtils mongodbUtils;

    @Test
    public  void mongodbUtilsAddTest() {
        mongodbUtils.add(new SysUser().setId(IdUtils.randomSnowflake()).setUserName("lihong").setNickname("李洪"));
    }
}
