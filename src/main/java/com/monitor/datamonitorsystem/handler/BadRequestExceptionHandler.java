package com.monitor.datamonitorsystem.handler;

import com.alibaba.fastjson.JSONObject;
import com.monitor.datamonitorsystem.pojo.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 统一错误校验处理
 * @author zxl
 * @创建时间 2019-12-18 15:15
 */
@RestControllerAdvice
public class BadRequestExceptionHandler {


    /**
     * 方法参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String exceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errLog = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errLog.append(fieldError.getDefaultMessage()).
                    append("[").append(fieldError.getField()).append("]").append(";");
        }
        return JSONObject.toJSONString(BaseResponse.error(errLog.toString()));
    }

    /**
     * 数据校验绑定异常校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public String validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder logSb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            logSb.append(fieldError.getDefaultMessage()).append("[").append(fieldError.getField()).append("]").append(";");
        }
        return JSONObject.toJSONString(BaseResponse.error(logSb.toString()));
    }

    /**
     * 类型转换校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public String constraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return JSONObject.toJSONString(BaseResponse.error(String.join(",",msgList)));
    }

    /**
     * 全局异常
     * @author zxl
     * @time 2020/1/2 9:43
     */
    @ExceptionHandler
    public String errorHandler(Exception e){
        e.printStackTrace();
        return JSONObject.toJSONString(BaseResponse.error("未知异常"));
    }

}

