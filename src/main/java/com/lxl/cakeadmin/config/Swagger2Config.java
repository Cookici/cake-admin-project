package com.lxl.cakeadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ProjectName: payment-demo
 * @Package: com.lrh.paymentdemo.config
 * @ClassName: Swagger2Config
 * @Author: 63283
 * @Description:
 * @Date: 2023/11/26 15:25
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Cake后台接口文档")
                        .build());
    }


}
