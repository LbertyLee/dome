package com.lh.dome.coursefeedback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.dome.common.domain.PaginateData;
import com.lh.dome.common.utils.PaginateUtils;
import com.lh.dome.coursefeedback.domain.CourseFeedback;
import com.lh.dome.coursefeedback.domain.dto.CourseFeedbackDTO;
import com.lh.dome.coursefeedback.domain.vo.CourseFeedbackVO;
import com.lh.dome.coursefeedback.mapper.CourseFeedbackMapper;
import com.lh.dome.coursefeedback.service.CourseFeedbackService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程反馈实现类
 *
 * @author lihong
 * @date 2023/04/20
 */
@Service
public class CourseFeedbackServiceImpl implements CourseFeedbackService {

    @Resource
    CourseFeedbackMapper courseFeedbackMapper;

    /**
     * 添加课程反馈
     *
     * @param courseFeedbackDTO 课程反馈dto
     * @return int
     */
    @Override
    public int addCourseFeedback(CourseFeedbackDTO courseFeedbackDTO) {
        CourseFeedback courseFeedback = new CourseFeedback();
        BeanUtils.copyProperties(courseFeedbackDTO ,courseFeedback);
        return courseFeedbackMapper.insert(courseFeedback);
    }

    /**
     * 得到课程反馈列表
     *
     * @param courseFeedback 课程反馈
     * @return {@link List}<{@link CourseFeedback}>
     */
    @Override
    public PaginateData<CourseFeedbackVO> getCourseFeedbackList(CourseFeedback courseFeedback) {
        Page<CourseFeedback> page = PaginateUtils.startPage();
        LambdaQueryWrapper<CourseFeedback> courseFeedbackQueryWrapper = new LambdaQueryWrapper<>();
        courseFeedbackQueryWrapper.like(CourseFeedback::getCourseName,courseFeedback.getCourseName());
        Page<CourseFeedback> courseFeedbackPage = courseFeedbackMapper.selectPage(page, courseFeedbackQueryWrapper);
        List<CourseFeedbackVO> courseFeedbackVOS = courseFeedbackPage.getRecords().stream().map(a -> {
            CourseFeedbackVO courseFeedbackVO = new CourseFeedbackVO();
            BeanUtils.copyProperties(a, courseFeedbackVO);
            return courseFeedbackVO;
        }).toList();
        return PaginateUtils.build(page,courseFeedbackVOS);
    }
}
