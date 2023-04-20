package com.lh.dome.common.domain;

import lombok.Data;

import java.util.List;

/**
 *
 *
 * @author lihong
 * @date 2023/04/20
 */
@Data
public class PaginateData<T> {
    private Integer total;
    private List<T> records;
    private Integer currentPage;
    private Integer pageSize;
    private Integer pageCount;
}
