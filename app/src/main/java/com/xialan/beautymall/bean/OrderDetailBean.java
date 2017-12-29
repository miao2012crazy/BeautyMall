package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class OrderDetailBean {

    /**
     * code : S
     * data : {"order_code":"39221711000000000077","order_state":"0","pay_state":"0","product_list":[{"product_name":"EZ DETECT 大便隐血检测试剂盒","option_name":"","shipment":"30","shipment_state":"0","price":"953.34","product_num":"2","product_img":"http://www.gou-mei.com/IBSync/images/b3f936ce-088d-421a-9c9f-ce18830e5ee0.jpg","shipment_name":"喵喵","shipment_address":"天津市西青区  天津市西青区赛达三大道五支路","shipment_tel":"18222703922","describe_msg":"","order_time":"2017-11-07 11:01:51"},{"product_name":"打造你的概念红色套盒 21亮白 or  23自然 ","option_name":"","shipment":"30","shipment_state":"0","price":"1287.00","product_num":"4","product_img":"http://www.gou-mei.com/IBSync/images/7945b593-be35-48f0-a26e-5c6fc6021467.jpg","shipment_name":"喵喵","shipment_address":"天津市西青区  天津市西青区赛达三大道五支路","shipment_tel":"18222703922","describe_msg":"","order_time":"2017-11-07 11:01:51"}]}
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
         * order_code : 39221711000000000077
         * order_state : 0
         * pay_state : 0
         * product_list : [{"product_name":"EZ DETECT 大便隐血检测试剂盒","option_name":"","shipment":"30","shipment_state":"0","price":"953.34","product_num":"2","product_img":"http://www.gou-mei.com/IBSync/images/b3f936ce-088d-421a-9c9f-ce18830e5ee0.jpg","shipment_name":"喵喵","shipment_address":"天津市西青区  天津市西青区赛达三大道五支路","shipment_tel":"18222703922","describe_msg":"","order_time":"2017-11-07 11:01:51"},{"product_name":"打造你的概念红色套盒 21亮白 or  23自然 ","option_name":"","shipment":"30","shipment_state":"0","price":"1287.00","product_num":"4","product_img":"http://www.gou-mei.com/IBSync/images/7945b593-be35-48f0-a26e-5c6fc6021467.jpg","shipment_name":"喵喵","shipment_address":"天津市西青区  天津市西青区赛达三大道五支路","shipment_tel":"18222703922","describe_msg":"","order_time":"2017-11-07 11:01:51"}]
         */

        private String order_code;
        private String order_state;
        private String pay_state;
        private String total_price;



        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }
        private List<ProductListBean> product_list;

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getPay_state() {
            return pay_state;
        }

        public void setPay_state(String pay_state) {
            this.pay_state = pay_state;
        }

        public List<ProductListBean> getProduct_list() {
            return product_list;
        }

        public void setProduct_list(List<ProductListBean> product_list) {
            this.product_list = product_list;
        }

        public static class ProductListBean {
            /**
             * product_name : EZ DETECT 大便隐血检测试剂盒
             * option_name :
             * shipment : 30
             * shipment_state : 0
             * price : 953.34
             * product_num : 2
             * product_img : http://www.gou-mei.com/IBSync/images/b3f936ce-088d-421a-9c9f-ce18830e5ee0.jpg
             * shipment_name : 喵喵
             * shipment_address : 天津市西青区  天津市西青区赛达三大道五支路
             * shipment_tel : 18222703922
             * describe_msg :
             * order_time : 2017-11-07 11:01:51
             */
            private String tax_price;
            private String product_name;
            private String option_name;
            private String shipment;
            private String shipment_state;
            private String price;
            private String product_num;
            private String product_img;
            private String shipment_name;
            private String shipment_address;
            private String shipment_tel;
            private String describe_msg;
            private String order_time;
            private String shipment_order;

            public String getShipment_order() {
                return shipment_order;
            }

            public void setShipment_order(String shipment_order) {
                this.shipment_order = shipment_order;
            }

            public String getTax_price() {
                return tax_price;
            }

            public void setTax_price(String tax_price) {
                this.tax_price = tax_price;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getOption_name() {
                return option_name;
            }

            public void setOption_name(String option_name) {
                this.option_name = option_name;
            }

            public String getShipment() {
                return shipment;
            }

            public void setShipment(String shipment) {
                this.shipment = shipment;
            }

            public String getShipment_state() {
                return shipment_state;
            }

            public void setShipment_state(String shipment_state) {
                this.shipment_state = shipment_state;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProduct_num() {
                return product_num;
            }

            public void setProduct_num(String product_num) {
                this.product_num = product_num;
            }

            public String getProduct_img() {
                return product_img;
            }

            public void setProduct_img(String product_img) {
                this.product_img = product_img;
            }

            public String getShipment_name() {
                return shipment_name;
            }

            public void setShipment_name(String shipment_name) {
                this.shipment_name = shipment_name;
            }

            public String getShipment_address() {
                return shipment_address;
            }

            public void setShipment_address(String shipment_address) {
                this.shipment_address = shipment_address;
            }

            public String getShipment_tel() {
                return shipment_tel;
            }

            public void setShipment_tel(String shipment_tel) {
                this.shipment_tel = shipment_tel;
            }

            public String getDescribe_msg() {
                return describe_msg;
            }

            public void setDescribe_msg(String describe_msg) {
                this.describe_msg = describe_msg;
            }

            public String getOrder_time() {
                return order_time;
            }

            public void setOrder_time(String order_time) {
                this.order_time = order_time;
            }
        }
    }
}
