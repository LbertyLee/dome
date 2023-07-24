package com.lh.demo.auth.controller.router;

import com.lh.demo.common.domain.RespResult;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.bouncycastle.asn1.esf.SPuri;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 路由器控制器
 *
 * @author lihong
 * @date 2023/07/24
 */
@RestController
@RequestMapping("/router")
public class RouterController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping
    public RespResult getRouters(){
        return RespResult.success(sysMenuService.getRouters(SecurityUtils.getUserId()));

    }
}
