package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/18.
 */

public class ADBean {

    /**
     * code : S
     * data : [{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER01.jpg","product_id":"35"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER02.jpg","product_id":"51"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER03.jpg","product_id":"85"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER04.jpg","product_id":"114"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER05.jpg","product_id":"130"}]
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
         * url : http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER01.jpg
         * product_id : 35
         */

        private String url;
        private String product_id;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }
    }
}
