package com.lh.dome.coursefeedback.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import com.lh.dome.common.domain.RespResult;
import com.lh.dome.coursefeedback.domain.CourseFeedback;
import com.lh.dome.coursefeedback.domain.dto.CourseFeedbackDTO;
import com.lh.dome.coursefeedback.service.CourseFeedbackService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 课程反馈控制器
 *
 * @author lihong
 * @date 2023/04/20
 */
@RestController
@RequestMapping("/feedback")
public class CourseFeedbackController {


    @Resource
    private CourseFeedbackService courseFeedbackService;


    /**
     * 添加课程反馈
     *
     * @param courseFeedbackDTO 课程反馈dto
     * @return {@link RespResult}
     */
    @SaIgnore
    @PostMapping
    public RespResult addCourseFeedback(@RequestBody @Valid CourseFeedbackDTO courseFeedbackDTO){
        courseFeedbackService.addCourseFeedback(courseFeedbackDTO);
        return RespResult.success();
    }


//    @SaCheckRole("admin")
    @GetMapping
    public RespResult getCourseFeedbackList(String name){
        CourseFeedback courseFeedback = new CourseFeedback().setCourseName(name);
        return RespResult.success(courseFeedbackService.getCourseFeedbackList(courseFeedback));
    }

}
