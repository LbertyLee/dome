package com.lh.dome.coursefeedback.controller;

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
    @PostMapping
    public RespResult addCourseFeedback(@RequestBody @Valid CourseFeedbackDTO courseFeedbackDTO){
        courseFeedbackService.addCourseFeedback(courseFeedbackDTO);
        return RespResult.success();
    }

    @GetMapping
    public RespResult getCourseFeedbackList(CourseFeedback courseFeedback){
       return RespResult.success(courseFeedbackService.getCourseFeedbackList(courseFeedback));
    }

}
