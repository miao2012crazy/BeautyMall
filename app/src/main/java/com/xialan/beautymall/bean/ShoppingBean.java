package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ShoppingBean {
    /**
     * code : S
     * data : [{"product_id":"166","product_name":"大肠检测","product_url":"http://61.181.111.115:80/Storage/mall/productsb3f936ce-088d-421a-9c9f-ce18830e5ee0.jpg","product_price":"80000","product_norms":"IB产品","product_num":"2"},{"product_id":"146","product_name":"打造你的概念红色套盒 21亮白 or  23自然 ","product_url":"http://61.181.111.115:80/Storage/mall/products7945b593-be35-48f0-a26e-5c6fc6021467.jpg","product_price":"54000","product_norms":"IB产品","product_num":"4"},{"product_id":"173","product_name":"SELDICINE 赛迪新 去痛热敷霜","product_url":"http://61.181.111.115:80/Storage/mall/products6e9cb4ae-6ae4-44b0-86d6-5952f2517daa.jpg","product_price":"66000","product_norms":"IB产品","product_num":"1"},{"product_id":"177","product_name":"JUNGJINHO EFFECT  焕颜美肌精华霜 ","product_url":"http://61.181.111.115:80/Storage/mall/products0cf684e4-26a2-4c92-a7e2-253f613106d5.jpg","product_price":"250000","product_norms":"IB产品","product_num":"1"},{"product_id":"148","product_name":"蒂佳婷（Dr.Jart+）维生素活颜亮白霜（V7素颜霜）","product_url":"http://61.181.111.115:80/Storage/mall/products71c5c4e9-28b1-4380-b631-23785691dfa0.jpg","product_price":"48000","product_norms":"IB产品","product_num":"2"},{"product_id":"169","product_name":"Vita Pure 维他命淋浴过滤器","product_url":"http://61.181.111.115:80/Storage/mall/products8ecf8673-6971-4f5b-a8f8-52f523e974f0.jpg","product_price":"59800","product_norms":"IB产品","product_num":"1"}]
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
         * product_id : 166
         * product_name : 大肠检测
         * product_url : http://61.181.111.115:80/Storage/mall/productsb3f936ce-088d-421a-9c9f-ce18830e5ee0.jpg
         * product_price : 80000
         * product_norms : IB产品
         * product_num : 2
         */
        private String prepare_id;
        private String product_id;
        private String product_name;
        private String product_url;
        private String product_price;
        private String product_norms;
        private String product_num;

        public String getPrepare_id() {
            return prepare_id;
        }

        public void setPrepare_id(String prepare_id) {
            this.prepare_id = prepare_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_url() {
            return product_url;
        }

        public void setProduct_url(String product_url) {
            this.product_url = product_url;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getProduct_norms() {
            return product_norms;
        }

        public void setProduct_norms(String product_norms) {
            this.product_norms = product_norms;
        }

        public String getProduct_num() {
            return product_num;
        }

        public void setProduct_num(String product_num) {
            this.product_num = product_num;
        }
    }
}
