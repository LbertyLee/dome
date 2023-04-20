package com.lh.dome.coursefeedback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author lihong
 * @date 2023/04/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseInfo {
    /**
     * 课程id
     */
    private Integer id;

    /**
     * 课程名字
     */
    private String name;

    /**
     * 老师名字
     */
    private String teacherName;

}
