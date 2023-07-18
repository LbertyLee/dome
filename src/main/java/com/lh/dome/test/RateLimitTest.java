package com.lh.dome.test;

import com.lh.dome.framework.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RateLimitTest {
    @GetMapping("/limitedEndpoint")
    @RateLimit(value = 2)
    public String limitedEndpoint(){
        return "Hello, limitedEndpoint!";
    }
}
