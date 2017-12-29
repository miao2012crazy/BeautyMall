package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class BaseOrderContract {
    interface Presenter {
        /**
         * @param uid
         * @param order_code
         */
        void cancelOrder(String uid, String order_code);

    }
    interface View extends BaseView {

        void onCancelOrderSuccessed(String msg);

        void onCancelOrderFailed(String err_msg);
    }

}
