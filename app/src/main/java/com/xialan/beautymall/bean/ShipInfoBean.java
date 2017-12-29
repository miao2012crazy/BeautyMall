package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ShipInfoBean {

    /**
     * responses : {"response":{"mail_no":"7700000000869","scan_infos":{"scan_info":[{"time":"2017-12-19 03:15:33","remark":"包裹清关完毕，送交后程派送 Customs clearance Completed","scan_type":"NULL"},{"time":"2017-12-19 03:13:36","remark":"包裹配载航班已抵达，发送至清关口岸 Arrived in Destination Country","scan_type":"NULL"},{"time":"2017-12-19 03:13:16","remark":"包裹载配航班已起飞, 发往目的地国家 Exported to Destination Country","scan_type":"NULL"},{"time":"2017-12-19 03:12:00","remark":"【英国主仓】的收件员【admin】已收件","scan_type":"NULL"},{"time":"2017-12-19 03:10:23","remark":"订单生成7700000000869 Shipment Created","scan_type":"NULL"},{"time":"2017-07-20 17:51:50","remark":"到达：【中国-上海】，国际干线运输已从【1】启程，航（班）次号：1","scan_type":"NULL"},{"time":"2016-06-22 12:04:42","remark":"天津宁河县公司 快件已被 已签收 签收","scan_type":"signed"},{"time":"2016-06-22 08:29:07","remark":"天津宁河县公司 进行快件扫描，将发往：天津分拨中心","scan_type":"out"},{"time":"2016-06-22 07:41:15","remark":"天津宁河县公司 到达目的地网点，快件将很快进行派送","scan_type":"arrived"},{"time":"2016-06-21 22:27:44","remark":"天津分拨中心 从站点发出，本次转运目的地：天津宁河县公司","scan_type":"out"},{"time":"2016-06-21 22:13:25","remark":"天津分拨中心 在分拨中心进行称重扫描","scan_type":"weight"},{"time":"2016-06-21 17:53:23","remark":"天津东丽区二公司 进行发出扫描，将发往：天津分拨中心","scan_type":"out"}]},"status":"1","msg":"Success"}}
     */

    private ResponsesBean responses;

    public ResponsesBean getResponses() {
        return responses;
    }

    public void setResponses(ResponsesBean responses) {
        this.responses = responses;
    }

    public static class ResponsesBean {
        /**
         * response : {"mail_no":"7700000000869","scan_infos":{"scan_info":[{"time":"2017-12-19 03:15:33","remark":"包裹清关完毕，送交后程派送 Customs clearance Completed","scan_type":"NULL"},{"time":"2017-12-19 03:13:36","remark":"包裹配载航班已抵达，发送至清关口岸 Arrived in Destination Country","scan_type":"NULL"},{"time":"2017-12-19 03:13:16","remark":"包裹载配航班已起飞, 发往目的地国家 Exported to Destination Country","scan_type":"NULL"},{"time":"2017-12-19 03:12:00","remark":"【英国主仓】的收件员【admin】已收件","scan_type":"NULL"},{"time":"2017-12-19 03:10:23","remark":"订单生成7700000000869 Shipment Created","scan_type":"NULL"},{"time":"2017-07-20 17:51:50","remark":"到达：【中国-上海】，国际干线运输已从【1】启程，航（班）次号：1","scan_type":"NULL"},{"time":"2016-06-22 12:04:42","remark":"天津宁河县公司 快件已被 已签收 签收","scan_type":"signed"},{"time":"2016-06-22 08:29:07","remark":"天津宁河县公司 进行快件扫描，将发往：天津分拨中心","scan_type":"out"},{"time":"2016-06-22 07:41:15","remark":"天津宁河县公司 到达目的地网点，快件将很快进行派送","scan_type":"arrived"},{"time":"2016-06-21 22:27:44","remark":"天津分拨中心 从站点发出，本次转运目的地：天津宁河县公司","scan_type":"out"},{"time":"2016-06-21 22:13:25","remark":"天津分拨中心 在分拨中心进行称重扫描","scan_type":"weight"},{"time":"2016-06-21 17:53:23","remark":"天津东丽区二公司 进行发出扫描，将发往：天津分拨中心","scan_type":"out"}]},"status":"1","msg":"Success"}
         */

        private ResponseBean response;

        public ResponseBean getResponse() {
            return response;
        }

        public void setResponse(ResponseBean response) {
            this.response = response;
        }

        public static class ResponseBean {
            /**
             * mail_no : 7700000000869
             * scan_infos : {"scan_info":[{"time":"2017-12-19 03:15:33","remark":"包裹清关完毕，送交后程派送 Customs clearance Completed","scan_type":"NULL"},{"time":"2017-12-19 03:13:36","remark":"包裹配载航班已抵达，发送至清关口岸 Arrived in Destination Country","scan_type":"NULL"},{"time":"2017-12-19 03:13:16","remark":"包裹载配航班已起飞, 发往目的地国家 Exported to Destination Country","scan_type":"NULL"},{"time":"2017-12-19 03:12:00","remark":"【英国主仓】的收件员【admin】已收件","scan_type":"NULL"},{"time":"2017-12-19 03:10:23","remark":"订单生成7700000000869 Shipment Created","scan_type":"NULL"},{"time":"2017-07-20 17:51:50","remark":"到达：【中国-上海】，国际干线运输已从【1】启程，航（班）次号：1","scan_type":"NULL"},{"time":"2016-06-22 12:04:42","remark":"天津宁河县公司 快件已被 已签收 签收","scan_type":"signed"},{"time":"2016-06-22 08:29:07","remark":"天津宁河县公司 进行快件扫描，将发往：天津分拨中心","scan_type":"out"},{"time":"2016-06-22 07:41:15","remark":"天津宁河县公司 到达目的地网点，快件将很快进行派送","scan_type":"arrived"},{"time":"2016-06-21 22:27:44","remark":"天津分拨中心 从站点发出，本次转运目的地：天津宁河县公司","scan_type":"out"},{"time":"2016-06-21 22:13:25","remark":"天津分拨中心 在分拨中心进行称重扫描","scan_type":"weight"},{"time":"2016-06-21 17:53:23","remark":"天津东丽区二公司 进行发出扫描，将发往：天津分拨中心","scan_type":"out"}]}
             * status : 1
             * msg : Success
             */

            private String mail_no;
            private ScanInfosBean scan_infos;
            private String status;
            private String msg;

            public String getMail_no() {
                return mail_no;
            }

            public void setMail_no(String mail_no) {
                this.mail_no = mail_no;
            }

            public ScanInfosBean getScan_infos() {
                return scan_infos;
            }

            public void setScan_infos(ScanInfosBean scan_infos) {
                this.scan_infos = scan_infos;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public static class ScanInfosBean {
                private List<ScanInfoBean> scan_info;

                public List<ScanInfoBean> getScan_info() {
                    return scan_info;
                }

                public void setScan_info(List<ScanInfoBean> scan_info) {
                    this.scan_info = scan_info;
                }

                public static class ScanInfoBean {
                    /**
                     * time : 2017-12-19 03:15:33
                     * remark : 包裹清关完毕，送交后程派送 Customs clearance Completed
                     * scan_type : NULL
                     */

                    private String time;
                    private String remark;
                    private String scan_type;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public String getScan_type() {
                        return scan_type;
                    }

                    public void setScan_type(String scan_type) {
                        this.scan_type = scan_type;
                    }
                }
            }
        }
    }
}
