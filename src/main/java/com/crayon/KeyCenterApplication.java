package com.crayon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Author: znly
 * @Description:
 * @Date: 2023/12/28 9:30
 */
@SpringBootApplication
@Slf4j
public class KeyCenterApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KeyCenterApplication.class);

        ConfigurableApplicationContext context = application.run(args);

        log.info("-------------------Crayon项目成功启动-------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("当前时间:{}",dateFormat.format(System.currentTimeMillis()));
        log.info("配置中心地址:{}","http://8.138.18.180:8848/nacos");
    }

}
