package com.lxl.cakeadmin.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.config
 * @ClassName: DataSourceConfig
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 16:32
 */
@Slf4j
@Component
public class DataSourceConfig implements BeanPostProcessor, EnvironmentAware {

    private ConfigurableEnvironment environment;

    @Value("${datasource.config-path}")
    private String configPath;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MybatisPlusAutoConfiguration) {
            try {
                File file = new File(configPath + "/db.json");
                log.info("找到数据库配置json文件 ===> " + file.getAbsolutePath());
                String config = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                JSONObject configJson = JSON.parseObject(config);
                environment.getSystemProperties().put("spring.datasource.type", "com.alibaba.druid.pool.DruidDataSource");
                environment.getSystemProperties().put("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
                environment.getSystemProperties().put("spring.datasource.url", configJson.getString("url"));
                environment.getSystemProperties().put("spring.datasource.username", configJson.getString("username"));
                environment.getSystemProperties().put("spring.datasource.password", configJson.getString("password"));
            } catch (IOException e) {
                log.error("配置MybatisPlusAutoConfiguration失败,原因为： =====> {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
