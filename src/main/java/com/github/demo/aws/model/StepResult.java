package com.github.demo.aws.model;

public class StepResult {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StepResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
