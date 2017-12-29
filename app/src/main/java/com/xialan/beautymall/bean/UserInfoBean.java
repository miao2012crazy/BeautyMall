package com.xialan.beautymall.bean;

/**
 * Created by ${苗} on 2017/11/9.
 */

public class UserInfoBean {

    /**
     * code : S
     * data : {"user_name":"123","user_img":"头像路径","user_sex":"","user_age":"","wechat_state":""}
     */

    private String code;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_name : 123
         * user_img : 头像路径
         * user_sex :
         * user_age :
         * wechat_state :
         */
        private String uid="";
        private String user_name;
        private String user_img;
        private String user_sex;
        private String user_age;
        private String wechat_state;

        public DataBean() {
        }

        public DataBean(String uid, String user_name, String user_img, String user_sex, String user_age, String wechat_state) {
            this.uid = uid;
            this.user_name = user_name;
            this.user_img = user_img;
            this.user_sex = user_sex;
            this.user_age = user_age;
            this.wechat_state = wechat_state;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_age() {
            return user_age;
        }

        public void setUser_age(String user_age) {
            this.user_age = user_age;
        }

        public String getWechat_state() {
            return wechat_state;
        }

        public void setWechat_state(String wechat_state) {
            this.wechat_state = wechat_state;
        }
    }
}
