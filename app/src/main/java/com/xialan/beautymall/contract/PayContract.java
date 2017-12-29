package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface PayContract {
    interface Model {
    }

    interface View extends BaseView{
        void onGetPrepareIdSuccessed(String prepare_id);
        void onGetPrepareIdFailed(String err_msg);

        void onGetOrderStrSuccessed(String order_info);
        void onGetOrderStrFailed(String err_msg);

    }

    interface Presenter {
        void getPrepareId(String order_code,String fee,String product_name);

        void getOrderStrForAlipay(String order_code,String fee,String product_name);
    }
}
