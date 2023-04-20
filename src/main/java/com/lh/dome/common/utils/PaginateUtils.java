package com.lh.dome.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.dome.common.domain.PaginateData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * @Author Jianfreespace
 * @Date 2023/4/14
 */
public class PaginateUtils {


    /**
     * 默认分页
     * @return
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

    public static <T, V> PaginateData<T> build(Page<V> page, List<T> data) {
        PaginateData<T> paginateData = new PaginateData<>();
        paginateData.setTotal((int) page.getTotal());
        paginateData.setRecords(data);
        paginateData.setCurrentPage((int) page.getCurrent());
        paginateData.setPageSize((int) page.getSize());
        paginateData.setPageCount((int) page.getPages());
        return paginateData;
    }
}
