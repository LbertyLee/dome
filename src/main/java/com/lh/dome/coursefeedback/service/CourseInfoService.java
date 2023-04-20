package com.lh.dome.coursefeedback.service;

import com.lh.dome.coursefeedback.domain.CourseInfo;

import java.util.List;

/**
 * 课程信息服务
 *
 * @author lihong
 * @date 2023/04/20
 */
public interface CourseInfoService {
    /**
     * 获取课程信息列表
     *
     * @param courseInfo 课程信息
     * @return {@link List}<{@link CourseInfo}>
     */
    List<CourseInfo> getCourseInfoList(CourseInfo courseInfo);
}
