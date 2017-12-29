package com.xialan.beautymall.bean;

/**
 * Created by Administrator on 2017/12/14.
 */

public class PayPrepareIDBean {
    /**
     * code : S
     * data : {"order_code":"39221712000000000249"}
     */

    private String code;
    private PayPrepareIDBean.DataBean data;
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

    public PayPrepareIDBean.DataBean getData() {
        return data;
    }

    public void setData(PayPrepareIDBean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_code : 39221712000000000249
         */

        private String prepare_id_pay;

        public String getPrepare_id_pay() {
            return prepare_id_pay;
        }

        public void setPrepare_id_pay(String prepare_id_pay) {
            this.prepare_id_pay = prepare_id_pay;
        }
    }
}
