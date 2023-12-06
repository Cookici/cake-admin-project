package com.lxl.cakeadmin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.config
 * @ClassName: CorsConfig
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/6 13:14
 */
@Configuration
@Slf4j
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 配置跨域
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许哪个请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许哪个方法进行跨域
        corsConfiguration.addAllowedMethod("*");
        // 允许哪个请求来源进行跨域
        corsConfiguration.addAllowedOriginPattern("*");
        //跨域访问允许访问的响应头的内容 不设置无法返回token
        corsConfiguration.addExposedHeader("*");
        // 是否允许携带cookie进行跨域
        corsConfiguration.setAllowCredentials(false);

        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
