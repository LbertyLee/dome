package com.lh.demo.filestorage.domain.vo;

import cn.hutool.core.lang.Dict;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文件信息签证官
 *
 * @  lihong
 * @date 2023/04/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoVO {

    private static final long serialVersionUID = 1L;

    /*** 文件id*/
    private String id;

    /*** 文件访问地址*/
    private String url;

    /*** 文件名称*/
    private String filename;

    /*** 文件扩展名*/
    private String ext;

    /*** MIME 类型*/
    private String contentType;

    /*** 缩略图访问路径*/
    private String thUrl;

    /*** 缩略图名称*/
    private String thFilename;

    /*** 缩略图 MIME 类型*/
    private String thContentType;

    /*** 文件所属对象类型，例如用户头像，评价图片*/
    private String objectType;

    /*** 附加属性字典*/
    private Dict attr;

    /*** 创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
