package com.lh.dome.filestorage.domain;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author lihong
 * @date 2023/05/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileDetail {

    private static final long serialVersionUID = 1L;

    /*** 文件id*/
    @TableId
    private String id;

    /*** 文件访问地址*/
    private String url;

    /**
     * 文件名称
     */
    private String filename;

    /*** 状态*/
    private String status;

    /*** 创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
