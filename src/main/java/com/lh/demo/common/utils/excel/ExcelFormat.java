package com.lh.demo.common.utils.excel;

public enum ExcelFormat {

    /**
     * 整数格式
     */
    FORMAT_INTEGER("INTEGER"),
    /**
     * 格式双
     */
    FORMAT_DOUBLE("DOUBLE"),
    /**
     * 格式百分比
     */
    FORMAT_PERCENT("PERCENT"),
    /**
     * 日期格式
     */
    FORMAT_DATE("DATE");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
