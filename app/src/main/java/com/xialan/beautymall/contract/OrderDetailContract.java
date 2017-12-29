package com.xialan.beautymall.contract;


import com.xialan.beautymall.bean.OrderDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface OrderDetailContract {


    interface View extends BaseOrderContract.View {
        void getOrderDetailSuccess(OrderDetailBean.DataBean dataBean);
        void getOrderDetailFailed(String err_msg);
    }

    interface Presenter extends  BaseOrderContract.Presenter{
        /**
         * 获取订单详情
         * @param uid
         * @param order_code
         */
        void getDetailForOrder(String uid ,String order_code);
    }
}
