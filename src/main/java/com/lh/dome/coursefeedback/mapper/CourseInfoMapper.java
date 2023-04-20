package com.lh.dome.coursefeedback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.dome.coursefeedback.domain.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 课程信息映射器
 *
 * @author lihong
 * @date 2023/04/20
 */
@Mapper
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

    /**
     * 获取课程信息列表
     *
     * @param name 课程信息
     * @return {@link List}<{@link CourseInfo}>
     */
    List<CourseInfo> getCourseInfoList(String name);
}
