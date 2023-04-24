package com.lh.dome.coursefeedback.controller;

import com.lh.dome.common.domain.RespResult;
import com.lh.dome.coursefeedback.domain.CourseInfo;
import com.lh.dome.coursefeedback.service.CourseInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程信息控制器
 *
 * @author lihong
 * @date 2023/04/20
 */
@RestController
@RequestMapping("/feedback/courseInfo")
public class CourseInfoController {

    @Resource
    private CourseInfoService courseInfoService;

    @GetMapping
    public RespResult getCourseInfoList(String name){
        CourseInfo courseInfo = new CourseInfo().setName(name);
        List<CourseInfo> courseInfoList=courseInfoService.getCourseInfoList(courseInfo);
        return RespResult.success(courseInfoList);
    }
}
