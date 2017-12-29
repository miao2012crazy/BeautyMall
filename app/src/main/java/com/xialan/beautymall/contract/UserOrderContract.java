package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.bean.UserOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public interface UserOrderContract {
    interface Model {
    }

    interface View extends BaseOrderContract.View {
        /**
         * 数据获取成功
         *
         * @param dataBeans
         */
        void onGetListDataSuccess(List<UserOrderBean.DataBean> dataBeans);

        /**
         * 数据获取失败
         *
         * @param msg 提示信息
         */
        void onGetListDataFailed(String msg);

    }

    interface Presenter extends BaseOrderContract.Presenter{
        void getListDataFromNet(String uid, String index, String item);

    }
}
