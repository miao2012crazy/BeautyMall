package com.xialan.beautymall.bean;

/**
 * Created by Administrator on 2017/12/18.
 */

public class UserIdenBean {

    /**
     * code : S
     * data : {"iden_type":"0","real_name":"39221712000000000363","iden_no":"13136161616164","iden_state":"1","front_img":"18222703922_FRONT_20171215143911.jpg","bg_img":"18222703922_BACK_20171215143911.jpg"}
     */

    private String code;
    private DataBean data;
    private String err_msg;
    public String getCode() {
        return code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * iden_type : 0
         * real_name : 39221712000000000363
         * iden_no : 13136161616164
         * iden_state : 1
         * front_img : 18222703922_FRONT_20171215143911.jpg
         * bg_img : 18222703922_BACK_20171215143911.jpg
         */

        private String iden_type;
        private String real_name;
        private String iden_no;
        private String iden_state;
        private String front_img;
        private String bg_img;

        public String getIden_type() {
            return iden_type;
        }

        public void setIden_type(String iden_type) {
            this.iden_type = iden_type;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getIden_no() {
            return iden_no;
        }

        public void setIden_no(String iden_no) {
            this.iden_no = iden_no;
        }

        public String getIden_state() {
            return iden_state;
        }

        public void setIden_state(String iden_state) {
            this.iden_state = iden_state;
        }

        public String getFront_img() {
            return front_img;
        }

        public void setFront_img(String front_img) {
            this.front_img = front_img;
        }

        public String getBg_img() {
            return bg_img;
        }

        public void setBg_img(String bg_img) {
            this.bg_img = bg_img;
        }
    }
}
