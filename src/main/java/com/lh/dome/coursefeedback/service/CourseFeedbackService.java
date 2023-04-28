package com.lh.dome.coursefeedback.service;

import com.lh.dome.common.domain.PaginateData;
import com.lh.dome.coursefeedback.domain.CourseFeedback;
import com.lh.dome.coursefeedback.domain.dto.CourseFeedbackDTO;
import com.lh.dome.coursefeedback.domain.vo.CourseFeedbackVO;

import java.util.List;

/**
 * 课程反馈服务
 *
 * @author lihong
 * @date 2023/04/20
 */
public interface CourseFeedbackService {

    /**
     * 添加课程反馈
     *
     * @param courseFeedbackDTO 课程反馈dto
     * @return int
     */
    int addCourseFeedback(CourseFeedbackDTO courseFeedbackDTO);

    /**
     * 得到课程反馈列表
     *
     * @param courseFeedback 课程反馈
     * @return {@link List}<{@link CourseFeedback}>
     */
    PaginateData<CourseFeedbackVO> getCourseFeedbackList(CourseFeedback courseFeedback);
}
