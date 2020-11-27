package com.csc.demo1.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: csc
 * @Create: 2020-11-09 15:34
 * @Version: 1.0
 */
@RestControllerAdvice
public class ExceptionAdviceHandler {
    private Logger logger = LoggerFactory.getLogger(ExceptionAdviceHandler.class);

    /**
     * 未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData unKnowExceptionHandler(Exception e) {
        ResponseData result = ResponseData.buildResponse(ApplicationStatus.SYSTEM_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
//            if (LoggerUtil.isDebug()) {
            String message = "类|方法" + element.getClassName() + "." + element.getMethodName() + "类的第" + element.getLineNumber() + "行发生" + e.toString() + "异常";
            result.setMessage(message);
//            }
        }
        return result;
    }

    @ExceptionHandler
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public ResponseData indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        ResponseData result = ResponseData.buildResponse(ApplicationStatus.INDEX_OUTOF_BOUNDS_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
//            if (LoggerUtil.isDebug()) {
//                String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第",
//                        element.getLineNumber(), "行发生", e.toString(), "异常");
            String message = "类|方法" + element.getClassName() + "." + element.getMethodName() + "类的第" + element.getLineNumber() + "行发生" + e.toString() + "异常";
            result.setMessage(message);
            logger.error("404");
        }
        return result;
    }

    /**
     * @Description 打印错误日志信息
     * @Author csc
     * @Date 2019/8/21 14:34
     * @Version 1.0
     */
    private void printErrorMessage(Throwable e) {
        String message = e.toString();
        for (StackTraceElement element : e.getStackTrace()) {
            message = message + "\n" + element.toString();
        }
        logger.error(message);
    }
}
