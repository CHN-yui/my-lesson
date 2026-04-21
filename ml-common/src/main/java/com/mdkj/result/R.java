package com.mdkj.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 响应类 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    /** 响应代码 */
    private Integer code;
    /** 响应描述（访问人员）*/
    private String message;
    /** 响应描述（开发人员）*/
    private String coderMessage;
    /** 响应数据 */
    private T data;

    /**
     * <p>创建Result类：
     * 
     * <p>响应码：默认返回请求成功对应状态码，如 200, 1000 等
     * <p>响应描述（访问人员）：“请求成功”
     * <p>响应描述（开发人员）：“请求成功”
     * <p>响应数据：手动传递
     *
     * @param data 响应数据
     */
    public R(T data) {
        this(ResultCode.SUCCESS.getCODE(), ResultCode.SUCCESS.getMESSAGE(), ResultCode.SUCCESS.getMESSAGE(), data);
    }

    /**
     * <p>创建Result类：
     * 
     * <p>响应码：根据响应状态枚举实例获取
     * <p>响应描述（访问人员）：根据响应状态枚举实例获取
     * <p>响应描述（开发人员）：手动传递
     * <p>响应数据：null
     *
     * @param resultCode   响应状态枚举实例
     * @param coderMessage 响应描述（开发人员）
     */
    public R(ResultCode resultCode, String coderMessage) {
        this(resultCode.getCODE(), resultCode.getMESSAGE(), coderMessage, null);
    }

    /**
     * <p>创建Result类：
     * 
     * <p>响应码：根据响应状态枚举实例获取
     * <p>响应描述（访问人员）：根据响应状态枚举实例获取
     * <p>响应描述（开发人员）：手动传递
     * <p>响应数据：手动传递
     *
     * @param resultCode   响应状态枚举实例
     * @param coderMessage 响应描述（开发人员）
     * @param data         响应数据
     */
    public R(ResultCode resultCode, String coderMessage, T data) {
        this(resultCode.getCODE(), resultCode.getMESSAGE(), coderMessage, data);
    }

    public static <T> R<T> ok(String message) {
        return new R<>(ResultCode.SUCCESS, message);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data);
    }
    public static <T> R<T> ok(String message, T data) {
        return new R<>(ResultCode.SUCCESS, message, data);
    }

    public static <T> R<T> error(ResultCode resultCode, String message) {
        return new R<>(resultCode, message);
    }
    public static <T> R<T> error(ResultCode resultCode, String message, T data) {
        return new R<>(resultCode, message, data);
    }
    public static <T> R<T> error(String message) {
        return new R<>(ResultCode.SERVER_ERROR, message);
    }
}