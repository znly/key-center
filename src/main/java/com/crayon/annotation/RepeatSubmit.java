package com.crayon.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: znly
 * @Description: 防止接口被重复访问
 * @Date: 2024/3/20 21:20
 */
@Inherited
@Target(ElementType.METHOD) //可以放在方法上TYPE是可以放在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
//防止接口重复提交
public @interface RepeatSubmit {

    /**
     * 防重复操作限时标记数值（存储redis限时标记数值）
     */
    String value() default "value";

    /**
     * 防重复操作过期时间,默认1s
     */
    long expireTime() default 1;

    //是否需要token 默认需要
    boolean istoken() default true;

    //类型
    String type() default "";

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
