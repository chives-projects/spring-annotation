package com.csc.demo1.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Description 返回值工具类
 * @Author csc
 * @Date 2019/8/20 14:53
 * @Version 1.0
 */
public class  ResponseData<T> implements Serializable {
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ResponseData<T> buildResponse(int status, String message) {
        ResponseData baseResponse = new ResponseData();
        baseResponse.setStatus(status);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> ResponseData<T> buildResponse(ApplicationStatus applicationStatus) {
        ResponseData baseResponse = new ResponseData();
        baseResponse.setStatus(applicationStatus.getStatus());
        baseResponse.setMessage(applicationStatus.getMessage());
        return baseResponse;
    }

    public static <T> ResponseData<T> buildResponse() {
        ResponseData baseResponse = new ResponseData();
        baseResponse.setStatus(ApplicationStatus.OK.getStatus());
        baseResponse.setMessage(ApplicationStatus.OK.getMessage());
        return baseResponse;
    }

    public static <T> ResponseData<T> buildResponse(T data) {
        ResponseData baseResponse = new ResponseData();
        baseResponse.setStatus(ApplicationStatus.OK.getStatus());
        baseResponse.setMessage(ApplicationStatus.OK.getMessage());
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> ResponseData<T> buildResponse(int status, String message, T data) {
        ResponseData baseResponse = new ResponseData();
        baseResponse.setStatus(status);
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> ResponseData<T> buildResponse(ApplicationStatus applicationStatus, T data) {
        ResponseData baseResponse = new ResponseData();
        baseResponse.setStatus(applicationStatus.getStatus());
        baseResponse.setMessage(applicationStatus.getMessage());
        baseResponse.setData(data);
        return baseResponse;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
