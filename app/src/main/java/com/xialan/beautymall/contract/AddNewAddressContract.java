package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

/**
 * Created by Administrator on 2017/11/13.
 */

public interface AddNewAddressContract {


    interface View extends BaseView{
        void onUpDataSuccessed();
        void onUpDataFailed(String msg);
    }

    interface Presenter {
        /**
         * 修改和新增地址
         * @param item 操作类型
         * @param uid 用户id
         * @param address_id 地址id 可传""
         * @param recv_title 收货地址小标签
         * @param address_user_tel 收货人电话
         * @param address_class_a 二级地址
         * @param address_class_b 详细地址
         * @param recv_name 收货人姓名
         * @param is_default 是否为默认
         */
        void  upDateNewDataToNet(String item,String uid,String address_id,String recv_title,String address_user_tel,String address_class_a,String address_class_b,String recv_name,String is_default);

    }
}
