package com.lh.demo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * 邮件实体
 *
 * @author lihong
 * @date 2023/07/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailEntity {

    /*** 标题*/
    String title;

    /*** 内容*/
    String content;

    /*** 发送到*/
    String sendTo;

    /*** 文件*/
    File file;
}
