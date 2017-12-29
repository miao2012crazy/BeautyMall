package com.xialan.beautymall.contract;

/**
 * Created by Administrator on 2017/12/11.
 */

public interface SelectOrderAddressContract {


    interface View  extends AddressManageContract.View{
        void onSetRecvAddressSuccessed();
        void onSetRecvAddressFailed(String msg);
    }

    interface Presenter extends AddressManageContract.Presenter{
        void  setRecvAddress(String uid, String order_id,String address_id );
    }
}
