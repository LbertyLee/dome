package com.lh.demo.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.demo.common.domain.PaginateData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * 分页工具
 *
 * @  lihong
 * @Date 2023/4/14
 */
public class PaginateUtils {


    /**
     * 默认分页
     */
    public static <T> Page<T> startPage() {
        // 获取上下文
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        int pageNum = 1;
        int pageSize = 10;
        String pageNumStr = request.getParameter("pageNum");
        String pageSizeStr = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        return new Page<>(pageNum, pageSize);
    }

    /**
     * 构建分页数据
     *
     * @param page 页面
     * @param data 数据
     * @return {@link PaginateData}<{@link T}>
     */
    public static <T, V> PaginateData<T> build(Page<V> page, List<T> data) {
        PaginateData<T> paginateData = new PaginateData<>();
        paginateData.setTotal((int) page.getTotal());
        paginateData.setRecords(data);
        paginateData.setCurrentPage((int) page.getCurrent());
        paginateData.setPageSize((int) page.getSize());
        paginateData.setPageCount((int) page.getPages());
        return paginateData;
    }

    public static <T> PaginateData<T> builds(Page<T> courseFeedbackPage) {
        PaginateData<T> paginateData = new PaginateData<>();
        paginateData.setTotal((int) courseFeedbackPage.getTotal());
        paginateData.setRecords(courseFeedbackPage.getRecords());
        paginateData.setCurrentPage((int) courseFeedbackPage.getCurrent());
        paginateData.setPageSize((int) courseFeedbackPage.getSize());
        paginateData.setPageCount((int) courseFeedbackPage.getPages());
        return paginateData;
    }
}
