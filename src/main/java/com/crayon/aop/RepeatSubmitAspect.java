package com.crayon.aop;

import com.crayon.annotation.RepeatSubmit;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @Author: znly
 * @Description:
 * @Date: 2024/3/20 21:28
 */
@Slf4j(topic = "RepeatSubmitAspect")
@Component
@Aspect
@Order(12)   //定义
public class RepeatSubmitAspect {

    @Autowired
//    private RedisManager redisManager;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.crayon.annotation.RepeatSubmit)")
    public void repeatSubmit() {
    }

    @Around("repeatSubmit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取请求属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取访问ip
        String remoteAddr = request.getRemoteAddr();

        //获取方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取防重复提交注解
        RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
        String type = annotation.type();

        return joinPoint.proceed();
    }


}
