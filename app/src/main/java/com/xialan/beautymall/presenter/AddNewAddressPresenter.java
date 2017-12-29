package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.contract.AddNewAddressContract;
import com.xialan.beautymall.contract.AddressManageContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AddNewAddressPresenter extends BasePresenter implements AddNewAddressContract.Presenter {
    private  AddNewAddressContract.View mView;

    public AddNewAddressPresenter(AddNewAddressContract.View view) {
        this.mView=view;
    }

    @Override
    public void upDateNewDataToNet(String item, String uid, String address_id, String recv_title, String address_user_tel, String address_class_a, String address_class_b, String recv_name, String is_default) {

        NovateUtil.getInstance().call(NovateUtil.getApiService().upDataForAddress(item, uid, address_id, recv_title, address_user_tel, address_class_a, address_class_b, recv_name, is_default), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                mView.showCustomProgressBar("正在加载中...");
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.onUpDataSuccessed();
                }else{
                    mView.onUpDataFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
