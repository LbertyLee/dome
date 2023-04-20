package com.lh.dome.framework.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author lihong
 * @date 2023/04/20
 */
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有的跨域请求来源
        config.addAllowedOrigin("*");
        // 允许所有的请求头
        config.addAllowedHeader("*");
        // 允许所有的请求方法
        config.addAllowedMethod("*");
        // 允许携带cookie
        config.setAllowCredentials(true);
        // 设置跨域请求缓存时间
        config.setMaxAge(3600L);
        // 对所有的请求路径启用该配置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        // 设置过滤器的优先级，让它先于其他过滤器执行
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }
}
