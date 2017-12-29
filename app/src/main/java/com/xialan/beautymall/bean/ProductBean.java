package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by ${苗} on 2017/11/10.
 */

public class ProductBean {

    /**
     * code : S
     * data : [{"product_id":"21","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10001/001.jpg","product_name":"WHOO后津率享红华凝香系列护肤品礼盒","product_price":"80000","product_original_price":"160000","product_tag":["Whoo后","韩国","150ml+110ml+40ml"]},{"product_id":"22","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10002/001.jpg","product_name":"WHOO GIPOOM","product_price":"100000","product_original_price":"200000","product_tag":["Whoo后","韩国","150ml+110ml+40ml"]},{"product_id":"23","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10003/001.jpg","product_name":"su:m37度 水漾清润系列礼盒","product_price":"65000","product_original_price":"130000","product_tag":["Whoo后","韩国","170ml+120ml+20ml"]},{"product_id":"24","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10004/001.jpg","product_name":"su:m37度 时光肌底系列礼盒","product_price":"57500","product_original_price":"115000","product_tag":["Whoo后","韩国","160ml+130ml+50ml"]},{"product_id":"25","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10006/001.jpg","product_name":"su:m37度 呼吸魔法系列礼盒","product_price":"60000","product_original_price":"120000","product_tag":["Whoo后","韩国","150ml+130ml"]},{"product_id":"26","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10007/001.jpg","product_name":"su:m37度 净透无暇系列礼盒","product_price":"100000","product_original_price":"200000","product_tag":["Whoo后","韩国","150ml+130ml+20ml"]},{"product_id":"27","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10008/001.jpg","product_name":"雪花秀 Sulwhasoo 滋盈肌本平衡水乳套装","product_price":"60000","product_original_price":"120000","product_tag":["雪花秀/Sulwhasoo","韩国","125ml+125ml"]},{"product_id":"28","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10009/001.jpg","product_name":"雪花秀/Sulwhasoo 玉璨净柔面膜","product_price":"21000","product_original_price":"42000","product_tag":["雪花秀/Sulwhasoo","韩国","150ml"]},{"product_id":"29","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10010/001.jpg","product_name":"雪花秀/Sulwhasoo 滋盈肌本平衡水乳套装","product_price":"26000","product_original_price":"52000","product_tag":["雪花秀/Sulwhasoo","韩国","150ml"]},{"product_id":"30","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10011/001.jpg","product_name":"雪花秀/Sulwhasoo 润致焕活肌底精华露","product_price":"45000","product_original_price":"90000","product_tag":["雪花秀/Sulwhasoo","韩国","60ml"]},{"product_id":"31","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10012/001.jpg","product_name":"雪花秀/Sulwhasoo 顺行柔和洁面泡沫","product_price":"17500","product_original_price":"35000","product_tag":["雪花秀/Sulwhasoo","韩国","200ml"]},{"product_id":"32","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10013/001.jpg","product_name":"雪花秀/Sulwhasoo 采淡致美气垫粉底液","product_price":"32500","product_original_price":"65000","product_tag":["雪花秀/Sulwhasoo","韩国","15g*2"]},{"product_id":"33","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10014/001.jpg","product_name":"赫拉/HERA 气垫粉底液","product_price":"18500","product_original_price":"37000","product_tag":["雪花秀/Sulwhasoo","韩国","15g*2"]},{"product_id":"34","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-AM-10015/001.jpg","product_name":"赫拉/HERA 清爽型防晒隔离霜","product_price":"17000","product_original_price":"34000","product_tag":["雪花秀/Sulwhasoo","韩国","70ml"]},{"product_id":"35","image_url":"http://61.181.111.115:80/Storage/mall/products//BR-KR-DZ-10016/001.jpg","product_name":"蒂佳婷（Dr.Jart+）维生素活颜亮白霜（V7素颜霜）","product_price":"24000","product_original_price":"48000","product_tag":["蒂佳婷Dr.Jart+","韩国","50ml"]}]
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
         * product_id : 21
         * image_url : http://61.181.111.115:80/Storage/mall/products//BR-KR-LG-10001/001.jpg
         * product_name : WHOO后津率享红华凝香系列护肤品礼盒
         * product_price : 80000
         * product_original_price : 160000
         * product_tag : ["Whoo后","韩国","150ml+110ml+40ml"]
         */
        private String product_id;
        private String image_url;
        private String product_name;
        private String product_price;
        private String product_original_price;
        private List<String> product_tag;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getProduct_original_price() {
            return product_original_price;
        }

        public void setProduct_original_price(String product_original_price) {
            this.product_original_price = product_original_price;
        }

        public List<String> getProduct_tag() {
            return product_tag;
        }

        public void setProduct_tag(List<String> product_tag) {
            this.product_tag = product_tag;
        }
    }
}
