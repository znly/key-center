package com.crayon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author hengye
 * @version 1.0
 * @Description
 * @date 2024/9/26 17:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 默认成功空结果返回
     *
     * @return
     */
    public static Result success() {
        return new Result(200, "success", null);
    }

    /**
     * 携带数据的成功结果返回
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    /**
     * 指定状态码和信息的成功结果返回
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result success(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    /**
     * 指定状态码和信息的成功结果返回
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result success(Integer code, String msg) {
        return new Result(200, msg, null);
    }

    /**
     * 携带数据和信息的成功结果返回
     *
     * @param msg
     * @param data
     * @return
     */
    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }

    /**
     * 携带成功信息的空结果返回
     *
     * @param msg
     * @return
     */
    public static Result success(String msg) {
        return new Result(200, msg, null);
    }

    public static Result error() {
        return new Result(500, "error", null);
    }

    /**
     * 指定异常状态码的错误结果
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * 指定异常信息的错误结果
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        return new Result(500, msg, null);
    }

    public static Result fail(String msg){
        return new Result(400, msg, null);
    }

    public static Result fail(String msg,Object data){
        return new Result(400, msg, data);
    }

    public boolean isSuccess() {
        return Objects.equals(code, 200);
    }

    public boolean isError() {
        return Objects.equals(code, 500);
    }

    public boolean isUnknown() {
        return Objects.equals(code, 999);
    }
}
