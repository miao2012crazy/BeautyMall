package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.bean.ProductBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public interface AddressManageContract {


    interface View extends BaseView {
        /**
         * 数据获取成功
         * @param dataBeans
         */
        void onGetListDataSuccess(List<AddressBean.DataBean> dataBeans);

        /**
         * 数据获取失败
         * @param msg 提示信息
         */
        void onGetListDataFailed(String msg);

        /**
         * 删除成功
         */
        void onDeleteSuccessed();

        /**
         * 删除失败
         * @param msg
         */
        void onDeleteFailed(String msg);
    }

    interface Presenter {
        /**
         * 获取地址列表
         * @param uid
         */
        void getListDataFromNet(String uid);

        /**
         * 删除地址
         * @param uid
         * @param address_no
         */
        void deleteAddress(String uid, String address_no);
    }
}
