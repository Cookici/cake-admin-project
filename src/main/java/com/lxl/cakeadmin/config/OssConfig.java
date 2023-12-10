package com.lxl.cakeadmin.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.config
 * @ClassName: OssConfig
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/9 21:06
 */
@Data
@Configuration
public class OssConfig {


    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;

    @Bean
    public OSS oss(){
        // 创建ossClient实例。
        return new OSSClientBuilder().build(endpoint, accessId, secretKey);
    }



}
