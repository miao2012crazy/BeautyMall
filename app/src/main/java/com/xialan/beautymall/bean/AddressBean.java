package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by ${苗} on 2017/11/10.
 */

public class AddressBean {


    private String err_msg;
    /**
     * code : S
     * data : [{"address_no":"10","recv_title":"家","address_class_a":"天津市西青大寺佳和雅庭3#1门902","address_class_b":"考虑考虑","address_user_tel":"18222703922","is_default":"1"}]
     */

    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * address_no : 10
         * recv_title : 家
         * address_class_a : 天津市西青大寺佳和雅庭3#1门902
         * address_class_b : 考虑考虑
         * address_user_tel : 18222703922
         * is_default : 1
         */

        private String address_no;
        private String recv_title;
        private String address_class_a;
        private String address_class_b;
        private String address_user_tel;
        private String address_recv_name;
        private String is_default;
        private boolean is_checked=false;

        public boolean isIs_checked() {
            return is_checked;
        }

        public void setIs_checked(boolean is_checked) {
            this.is_checked = is_checked;
        }

        public String getAddress_recv_name() {
            return address_recv_name;
        }

        public void setAddress_recv_name(String address_recv_name) {
            this.address_recv_name = address_recv_name;
        }

        public String getAddress_no() {
            return address_no;
        }

        public void setAddress_no(String address_no) {
            this.address_no = address_no;
        }

        public String getRecv_title() {
            return recv_title;
        }

        public void setRecv_title(String recv_title) {
            this.recv_title = recv_title;
        }

        public String getAddress_class_a() {
            return address_class_a;
        }

        public void setAddress_class_a(String address_class_a) {
            this.address_class_a = address_class_a;
        }

        public String getAddress_class_b() {
            return address_class_b;
        }

        public void setAddress_class_b(String address_class_b) {
            this.address_class_b = address_class_b;
        }

        public String getAddress_user_tel() {
            return address_user_tel;
        }

        public void setAddress_user_tel(String address_user_tel) {
            this.address_user_tel = address_user_tel;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}
