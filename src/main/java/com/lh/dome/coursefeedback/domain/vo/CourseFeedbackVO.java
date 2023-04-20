package com.lh.dome.coursefeedback.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 课程反馈签证官
 *
 * @author lihong
 * @date 2023/04/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseFeedbackVO {

    /**
     * 反馈信息id
     */
    private  Integer id;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空")
    private String courseName;

    /**
     * 老师名字
     */
    @NotBlank(message = "老师名字不能为空")
    private String teacherName;

    /**
     * 评分
     */
    @NotNull(message = "评分不能为空")
    private Integer score;

    /**
     * 反馈内容
     */
    @NotBlank(message = "反馈内容不能为空")
    private String content;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

}
