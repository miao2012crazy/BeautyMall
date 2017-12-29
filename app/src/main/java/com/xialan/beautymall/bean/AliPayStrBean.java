package com.xialan.beautymall.bean;

/**
 * Created by Administrator on 2017/12/14.
 */

public class AliPayStrBean {
    /**
     * code : S
     * data : {"order_code":"39221712000000000249"}
     */

    private String code;
    private AliPayStrBean.DataBean data;
    private String err_msg;

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AliPayStrBean.DataBean getData() {
        return data;
    }

    public void setData(AliPayStrBean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_code : 39221712000000000249
         */

        private String order_info;

        public String getOrder_info() {
            return order_info;
        }

        public void setOrder_info(String order_info) {
            this.order_info = order_info;
        }
    }
}
