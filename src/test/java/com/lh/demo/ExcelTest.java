package com.lh.demo;

import com.lh.demo.common.utils.excel.ExcelFormat;
import com.lh.demo.common.utils.excel.ExcelHeaderInfo;
import com.lh.demo.common.utils.excel.ExcelUtils;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.mapper.SysUserMapper;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExcelTest {
    @Resource
    private SysUserService  sysUserService;


}
