package com.lh.dome.filestorage.controller;

import com.lh.dome.common.domain.RespResult;
import com.lh.dome.filestorage.service.FileDetailService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/file/detail")
public class FileDetailController {

    @Resource
    private FileDetailService fileDetailService;

    @GetMapping
    public RespResult test(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1658442864041926656");
        return RespResult.success(fileDetailService.getFileInfo(strings));
    }
}
