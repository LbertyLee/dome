package com.lh.demo.common.utils.email.util;

import com.lh.demo.common.utils.StringUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 电子邮件工具类
 *
 * @author lihong
 * @date 2023/07/31
 */
@Component
public class EmailUtil {

    private static JavaMailSender javaMailSender;

    private static String formEmail;

    @Value("${spring.mail.form}")
    private String formEmail2;


    @Resource
    private JavaMailSender javaMailSender2;

    @PostConstruct
    public void beforeInit() {
        javaMailSender = javaMailSender2;
        formEmail = formEmail2;
    }

    /**
     * 发送邮件 - 不带附件
     *
     * @param title：邮件标题
     * @param content：   邮件内容
     * @param sendTo:    收件人
     */
    @SneakyThrows(Exception.class)
    public static void sendMail(String title, String content, String... sendTo) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        // 发送人邮件地址
        helper.setFrom(formEmail);
        // 接收人邮件地址
        helper.setTo(sendTo);
        // 主题
        helper.setSubject(title);

        // html内容 (设置true标识发送html邮件)
        helper.setText(content, true);
        javaMailSender.send(message);
    }

    /**
     * 发送邮件 - 带附件
     *
     * @param title：邮件标题
     * @param content： 邮件内容
     * @param attachmentFilename：附件文件名
     * @param file：附件
     * @param sendTo: 收件人
     */
    @SneakyThrows(Exception.class)
    public static void sendMail(String title, String content, String attachmentFilename, File file, String... sendTo) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        // 发送人邮件地址
        helper.setFrom(formEmail);
        // 接收人邮件地址
        helper.setTo(sendTo);
        // 主题
        helper.setSubject(title);

        // ① html内容 (设置true标识发送html邮件)
        helper.setText(content, true);

        // ② 附件
        if (StringUtils.isNotBlank(attachmentFilename)) {
            helper.addAttachment(attachmentFilename, file);
        }

        javaMailSender.send(message);
    }

}
