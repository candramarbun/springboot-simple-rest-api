package com.marbun.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;


@ControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object processValidationError(GeneralException ex) {
        HashMap<String,Object> resp= new HashMap<>();
        String result = ex.getMessage();
        resp.put("code",ex.getCode());
        resp.put("data",ex.getData());
        resp.put("message",result);
        return resp;
    }
}
