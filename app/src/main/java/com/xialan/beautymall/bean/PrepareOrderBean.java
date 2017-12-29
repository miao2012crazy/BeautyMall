package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class PrepareOrderBean {

    /**
     * code : S
     * data : {"address_id":"22","shipment_name":"喵喵","shipment_tel":"18222703922","shipment_address":"天津市西青区 天津市西青区赛达三大道五支路","list":[{"product_name":"A:口红 经典红","product_img":"http://www.gou-mei.com/Storage/mall/products//BR-KR-AC-10054/001.jpg","product_option":"粉色","product_count":"4","total_price":"286.00","shipment_price":"30.00","total_price_for_single":"316.00"},{"product_name":"A:口红 经典红","product_img":"http://www.gou-mei.com/Storage/mall/products//BR-KR-AC-10054/001.jpg","product_option":"摩卡粉色","product_count":"2","total_price":"143.00","shipment_price":"30.00","total_price_for_single":"173.00"}],"fTotalPrice":"489"}
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
         * address_id : 22
         * shipment_name : 喵喵
         * shipment_tel : 18222703922
         * shipment_address : 天津市西青区 天津市西青区赛达三大道五支路
         * list : [{"product_name":"A:口红 经典红","product_img":"http://www.gou-mei.com/Storage/mall/products//BR-KR-AC-10054/001.jpg","product_option":"粉色","product_count":"4","total_price":"286.00","shipment_price":"30.00","total_price_for_single":"316.00"},{"product_name":"A:口红 经典红","product_img":"http://www.gou-mei.com/Storage/mall/products//BR-KR-AC-10054/001.jpg","product_option":"摩卡粉色","product_count":"2","total_price":"143.00","shipment_price":"30.00","total_price_for_single":"173.00"}]
         * fTotalPrice : 489
         */

        private String address_id;
        private String shipment_name;
        private String shipment_tel;
        private String shipment_address;
        private String fTotalPrice;
        private List<ListBean> list;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getShipment_name() {
            return shipment_name;
        }

        public void setShipment_name(String shipment_name) {
            this.shipment_name = shipment_name;
        }

        public String getShipment_tel() {
            return shipment_tel;
        }

        public void setShipment_tel(String shipment_tel) {
            this.shipment_tel = shipment_tel;
        }

        public String getShipment_address() {
            return shipment_address;
        }

        public void setShipment_address(String shipment_address) {
            this.shipment_address = shipment_address;
        }

        public String getFTotalPrice() {
            return fTotalPrice;
        }

        public void setFTotalPrice(String fTotalPrice) {
            this.fTotalPrice = fTotalPrice;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * product_name : A:口红 经典红
             * product_img : http://www.gou-mei.com/Storage/mall/products//BR-KR-AC-10054/001.jpg
             * product_option : 粉色
             * product_count : 4
             * total_price : 286.00
             * shipment_price : 30.00
             * total_price_for_single : 316.00
             */
            private String tax_price;
            private String product_name;
            private String product_img;
            private String product_option;
            private String product_count;
            private String total_price;
            private String shipment_price;
            private String total_price_for_single;
            private String message="";

            public String getTax_price() {
                return tax_price;
            }

            public void setTax_price(String tax_price) {
                this.tax_price = tax_price;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_img() {
                return product_img;
            }

            public void setProduct_img(String product_img) {
                this.product_img = product_img;
            }

            public String getProduct_option() {
                return product_option;
            }

            public void setProduct_option(String product_option) {
                this.product_option = product_option;
            }

            public String getProduct_count() {
                return product_count;
            }

            public void setProduct_count(String product_count) {
                this.product_count = product_count;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getShipment_price() {
                return shipment_price;
            }

            public void setShipment_price(String shipment_price) {
                this.shipment_price = shipment_price;
            }

            public String getTotal_price_for_single() {
                return total_price_for_single;
            }

            public void setTotal_price_for_single(String total_price_for_single) {
                this.total_price_for_single = total_price_for_single;
            }
        }
    }
}
