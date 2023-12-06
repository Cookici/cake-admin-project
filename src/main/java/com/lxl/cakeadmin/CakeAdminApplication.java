package com.lxl.cakeadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CakeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeAdminApplication.class, args);
    }

}
