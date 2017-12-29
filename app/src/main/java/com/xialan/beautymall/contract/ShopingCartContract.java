package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.bean.ShoppingBean;

import java.util.List;

/**
 * Created by ${苗} on 2017/11/8.
 */

public interface ShopingCartContract {


    interface View extends BaseView{
        /**
         * 数据获取成功
         * @param dataBeans
         */
        void onGetListDataSuccess(List<ShoppingBean.DataBean> dataBeans);

        /**
         * 数据获取失败
         * @param msg 提示信息
         */
        void onGetListDataFailed(String msg);

        void onDeleteSuccessed();
        void onDeleteFailed(String err_msg);
    }

    interface Presenter {
        /**
         *
         * @param uid  用户id
         * @param index 页索引
         */
        void getListDataFromNet(String uid,String index);
        void deleteproduct(String uid, String prepare_id);
    }
}
