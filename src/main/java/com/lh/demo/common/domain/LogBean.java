package com.lh.demo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日志豆
 *
 * @author lihong
 * @date 2023/08/01
 */
@Document(collection = " log_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogBean {
    /**
     * id
     */
    private String id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    /**
     * 知识产权
     */
    private String ip;
    /**
     * 类名
     */
    private String className;//类名
    /**
     * 方法
     */
    private String method;//方法名
    /**
     * 要求参数
     */
    private String reqParam;//请求

}
