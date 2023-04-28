package com.lh.dome;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * dome应用程序
 *
 * @author lihong
 * @date 2023/04/20
 */
@EnableWebMvc
@EnableFileStorage
@SpringBootApplication
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
