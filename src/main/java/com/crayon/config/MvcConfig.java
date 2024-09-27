package com.crayon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: znly
 * @Description:    MVC配置类
 * @Date: 2024/4/9 22:58
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置处理
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/keyCenter/**") // 允许跨域的路径
                .allowedOrigins("*") // 允许所有域名访问 上线的时候可以替换成服务器域名或ip地址
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的方法
                .allowedHeaders("*"); // 允许的头信息
//                .allowCredentials(true); // 是否允许证书（cookies）
    }

    /**
     * 增加全局统一请求前缀
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/keyCenter",location->true);
    }
}
