package com.lh.demo.test;


import com.lh.demo.common.domain.MailEntity;
import com.lh.demo.common.domain.RespResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件控制器
 *
 * @author lihong
 * @date 2023/08/01
 */
@RestController
@RequestMapping("/test/mail")
public class MailController {


    @PostMapping("/sendEmail")
    public RespResult sendEmail(@RequestBody MailEntity mailEntity){
//        MailUtil.send(mailEntity);
        return RespResult.success();

    }
}
