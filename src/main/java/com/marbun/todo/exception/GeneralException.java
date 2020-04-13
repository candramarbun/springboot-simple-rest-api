package com.marbun.todo.exception;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GeneralException extends RuntimeException {
    private int code;
    private String message;
    private Object data;

    public GeneralException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        List<Object> d = new ArrayList<>();
        d.add(data);
        this.data = d;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
