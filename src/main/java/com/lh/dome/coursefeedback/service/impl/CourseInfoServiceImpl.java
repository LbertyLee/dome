package com.lh.dome.coursefeedback.service.impl;

import com.lh.dome.coursefeedback.domain.CourseInfo;
import com.lh.dome.coursefeedback.mapper.CourseInfoMapper;
import com.lh.dome.coursefeedback.service.CourseInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 课程信息服务实现类
 *
 * @author lihong
 * @date 2023/04/20
 */
@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    @Resource
    CourseInfoMapper courseInfoMapper;

    /**
     * 获取课程信息列表
     *
     * @param courseInfo 课程信息
     * @return {@link List}<{@link CourseInfo}>
     */
    @Override
    public List<CourseInfo> getCourseInfoList(CourseInfo courseInfo) {
        return courseInfoMapper.getCourseInfoList(courseInfo.getName());
    }
}
