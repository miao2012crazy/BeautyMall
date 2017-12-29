package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class PopMenuBean {

    /**
     * code : S
     * data : [{"index":"","category_title":"全部"},{"index":"brand","category_title":"品牌美妆"},{"index":"cosmetic","category_title":"化妆护肤"},{"index":"beauty","category_title":"美容美发"},{"index":"health","category_title":"养生保健"},{"index":"living","category_title":"生活用品"},{"index":"riben","category_title":"日本馆"}]
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
         * index :
         * category_title : 全部
         */

        private String category_id;
        private String category_title;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_title() {
            return category_title;
        }

        public void setCategory_title(String category_title) {
            this.category_title = category_title;
        }
    }
}
