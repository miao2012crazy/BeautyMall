package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.contract.AddressManageContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AddressManagePresenter extends BasePresenter implements AddressManageContract.Presenter {
    private final AddressManageContract.View mView;

    public AddressManagePresenter(AddressManageContract.View view) {
        this.mView = view;
    }

    @Override
    public void getListDataFromNet(String uid) {
        mView.showCustomProgressBar("地址获取中...");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getUserAddress(uid), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                AddressBean addressBean = ParseUtils.parseData(responseBody, AddressBean.class);
                boolean checkdata = ParseUtils.checkdata(addressBean.getCode());
                if (checkdata){
                    mView.onGetListDataSuccess(addressBean.getData());
                }else{
                    mView.onGetListDataFailed(addressBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }

    @Override
    public void deleteAddress(String uid, String address_no) {
        mView.showCustomProgressBar("正在删除...");
        NovateUtil.getInstance().call(NovateUtil.getApiService().deleteAddress("2",uid,address_no), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.onDeleteSuccessed();
                }else{
                    mView.onDeleteFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
