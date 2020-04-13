package com.marbun.todo.dto.responses;

public class GeneralResponse<T> {
    private int code;
    private T data;
    private String message;

    public GeneralResponse() {}

    public GeneralResponse(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public GeneralResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public  static <T> GeneralResponse <T> response ( T data) {
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setCode(200);
        response.setData(data);
        return response;
    }


    public  static <T> GeneralResponse <T> dialog (int code, String message) {
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
