package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/19.
 */

public class HomeBean {

    /**
     * code : S
     * data : {"loop_list":[{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER01.jpg","product_id":"35"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER02.jpg","product_id":"51"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER03.jpg","product_id":"85"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER04.jpg","product_id":"114"},{"url":"http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER05.jpg","product_id":"130"}],"product_hot":[{"product_title":"热销商品","product_sub_title":"全球热卖只因为你","product_id":"51","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"热销商品","product_sub_title":"全球热卖只因为你","product_id":"27","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"包邮专区","product_sub_title":"包邮包税还在等什么","product_id":"85","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"包邮专区","product_sub_title":"包邮包税还在等什么","product_id":"110","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"限时秒杀","product_sub_title":"错过这次回眸五百年","product_id":"142","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"限时秒杀","product_sub_title":"错过这次回眸五百年","product_id":"61","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"满减惠民","product_sub_title":"精品满减实惠到底","product_id":"32","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"},{"product_title":"满减惠民","product_sub_title":"精品满减实惠到底","product_id":"47","product_img_left":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg","product_img_right":"http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg"}],"product_discount":[{"product_title":"【品牌美妆】人气护肤平价好物剁手不停","product_sub_title":"","category_id":"6","category_img":"http://61.181.111.115:80/Storage/mall/appbanner/CATEGORY_BANNER_01.jpg"},{"product_title":"【化妆护肤】秋冬护肤必备清单","product_sub_title":"","category_id":"7","category_img":"http://61.181.111.115:80/Storage/mall/appbanner/CATEGORY_BANNER_02.jpg"},{"product_title":"【美容美发】感受自然神力","product_sub_title":"","category_id":"8","category_img":"http://61.181.111.115:80/Storage/mall/appbanner/CATEGORY_BANNER_03.jpg"},{"product_title":"【养生保健】美好人生养生护航","product_sub_title":"","category_id":"11","category_img":"http://61.181.111.115:80/Storage/mall/appbanner/CATEGORY_BANNER_04.jpg"},{"product_title":"【生活用品】发现好物生活用品保证生活品质","product_sub_title":"","category_id":"12","category_img":"http://61.181.111.115:80/Storage/mall/appbanner/CATEGORY_BANNER_05.jpg"}]}
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
        private List<LoopListBean> loop_list;
        private List<ProductHotBean> product_hot;
        private List<ProductDiscountBean> product_discount;

        public List<LoopListBean> getLoop_list() {
            return loop_list;
        }

        public void setLoop_list(List<LoopListBean> loop_list) {
            this.loop_list = loop_list;
        }

        public List<ProductHotBean> getProduct_hot() {
            return product_hot;
        }

        public void setProduct_hot(List<ProductHotBean> product_hot) {
            this.product_hot = product_hot;
        }

        public List<ProductDiscountBean> getProduct_discount() {
            return product_discount;
        }

        public void setProduct_discount(List<ProductDiscountBean> product_discount) {
            this.product_discount = product_discount;
        }

        public static class LoopListBean {
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

        public static class ProductHotBean {
            /**
             * product_title : 热销商品
             * product_sub_title : 全球热卖只因为你
             * product_id : 51
             * product_img_left : http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_01.jpg
             * product_img_right : http://61.181.111.115:80/Storage/mall/appbanner/SELECT_PRODUCT_02.jpg
             */

            private String product_title;
            private String product_sub_title;
            private String product_id_left;
            private String product_img_left;
            private String product_img_right;
            private String product_id_right;

            public String getProduct_id_left() {
                return product_id_left;
            }

            public void setProduct_id_left(String product_id_left) {
                this.product_id_left = product_id_left;
            }

            public String getProduct_id_right() {
                return product_id_right;
            }

            public void setProduct_id_right(String product_id_right) {
                this.product_id_right = product_id_right;
            }

            public String getProduct_title() {
                return product_title;
            }

            public void setProduct_title(String product_title) {
                this.product_title = product_title;
            }

            public String getProduct_sub_title() {
                return product_sub_title;
            }

            public void setProduct_sub_title(String product_sub_title) {
                this.product_sub_title = product_sub_title;
            }

            public String getProduct_img_left() {
                return product_img_left;
            }

            public void setProduct_img_left(String product_img_left) {
                this.product_img_left = product_img_left;
            }

            public String getProduct_img_right() {
                return product_img_right;
            }

            public void setProduct_img_right(String product_img_right) {
                this.product_img_right = product_img_right;
            }
        }

        public static class ProductDiscountBean {
            /**
             * product_title : 【品牌美妆】人气护肤平价好物剁手不停
             * product_sub_title :
             * category_id : 6
             * category_img : http://61.181.111.115:80/Storage/mall/appbanner/CATEGORY_BANNER_01.jpg
             */

            private String product_title;
            private String product_sub_title;
            private String category_id;
            private String category_img;

            public String getProduct_title() {
                return product_title;
            }

            public void setProduct_title(String product_title) {
                this.product_title = product_title;
            }

            public String getProduct_sub_title() {
                return product_sub_title;
            }

            public void setProduct_sub_title(String product_sub_title) {
                this.product_sub_title = product_sub_title;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCategory_img() {
                return category_img;
            }

            public void setCategory_img(String category_img) {
                this.category_img = category_img;
            }
        }
    }
}
