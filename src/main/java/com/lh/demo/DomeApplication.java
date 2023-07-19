package com.lh.demo;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@EnableFileStorage
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.lh.demo.**.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

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
