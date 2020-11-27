package com.csc.demo1.exception;

/**
 * @Description: 自定义状态码异常枚举类
 * @Author csc
 * @Version: 1.0
 */
public enum ApplicationStatus {
    /**
     * 执行成功
     */
    OK(0, "SUCCESS"),
    SYSTEM_EXCEPTION(2000, "系统异常"),
    INDEX_OUTOF_BOUNDS_EXCEPTION(2050, "数组越界异常");

    private final int status;
    private final String message;

    ApplicationStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
