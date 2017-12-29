package com.xialan.beautymall.bean;

/**
 * Created by Administrator on 2017/9/20.
 */

public class CheckSmsVerifyCodeBean {
    private Boolean is_valid;
    /**
     * error : {"code":50012,"message":"verified code"}
     */

    private ErrorBean error;

    public Boolean getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Boolean is_valid) {
        this.is_valid = is_valid;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ErrorBean {
        /**
         * code : 50012
         * message : verified code
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
