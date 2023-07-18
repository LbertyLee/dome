package com.lh.dome;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@EnableFileStorage
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.lh.dome.**.mapper")
public class DomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomeApplication.class, args);

        System.out.println("""

                                                                                 \s
                  ,--,     ,---,                                   ____          \s
                ,--.'|   ,--.' |          ,---,                  ,'  , `.        \s
                |  | :   |  |  :        ,---.'|   ,---.       ,-+-,.' _ |        \s
                :  : '   :  :  :        |   | :  '   ,'\\   ,-+-. ;   , ||        \s
                |  ' |   :  |  |,--.    |   | | /   /   | ,--.'|'   |  || ,---.  \s
                '  | |   |  :  '   |  ,--.__| |.   ; ,. :|   |  ,', |  |,/     \\ \s
                |  | :   |  |   /' : /   ,'   |'   | |: :|   | /  | |--'/    /  |\s
                '  : |__ '  :  | | |.   '  /  |'   | .; :|   : |  | ,  .    ' / |\s
                |  | '.'||  |  ' | :'   ; |:  ||   :    ||   : |  |/   '   ;   /|\s
                ;  :    ;|  :  :_:,'|   | '/  ' \\   \\  / |   | |`-'    '   |  / |\s
                |  ,   / |  | ,'    |   :    :|  `----'  |   ;/        |   :    |\s
                 ---`-'  `--''       \\   \\  /            '---'          \\   \\  / \s
                                      `----'                             `----'  \s
                                                                                 \s""");
    }




}
