package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class HistoryBean {

    /**
     * code : S
     * data : [{"address":"夏览科技店","date":"2017-10-31","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171031110530.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-30","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171030114524.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-30","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171030114450.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029181941.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029180850.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029180846.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029180845.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029180843.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029180842.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-29","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171029153504.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-09","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171009155236.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-09","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171009143839.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-08","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171008122454.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-08","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171008121826.jpg","title":"","describe":"","comment":""},{"address":"夏览科技店","date":"2017-10-08","image_path":"http://www.gou-mei.com/IBSync/skulp/18222703922_20171008121802.jpg","title":"","describe":"","comment":""}]
     */

    private String code;
    private List<DataBean> data;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 夏览科技店
         * date : 2017-10-31
         * image_path : http://www.gou-mei.com/IBSync/skulp/18222703922_20171031110530.jpg
         * title :
         * describe :
         * comment :
         */

        private String address;
        private String date;
        private String image_path;
        private String title;
        private String describe;
        private String comment;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
