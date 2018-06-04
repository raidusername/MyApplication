package com.example.networktest2;

public class LoginBean {
    private String code;
    private String message;
    private result result;

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

    public com.example.networktest2.result getResult() {
        return result;
    }

    public void setResult(com.example.networktest2.result result) {
        this.result = result;
    }
}
class result{
    private String deptLevel;
    private String truename;

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }
}
