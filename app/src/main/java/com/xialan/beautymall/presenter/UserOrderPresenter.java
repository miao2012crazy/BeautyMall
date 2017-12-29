package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.bean.UserOrderBean;
import com.xialan.beautymall.contract.UserInfoContract;
import com.xialan.beautymall.contract.UserOrderContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/14.
 */
public class UserOrderPresenter extends BasePresenter implements UserOrderContract.Presenter {
    private final UserOrderContract.View mView;

    public UserOrderPresenter(UserOrderContract.View view) {
        this.mView = view;
    }

    @Override
    public void getListDataFromNet(String uid, String index,String item) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getUserOrderData(uid,index,item), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                UserOrderBean userOrderBean = ParseUtils.parseData(responseBody, UserOrderBean.class);
                boolean checkdata = ParseUtils.checkdata(userOrderBean.getCode());
                if (checkdata) {
                    mView.onGetListDataSuccess(userOrderBean.getData());
                } else {
                    mView.onGetListDataFailed(userOrderBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }

    @Override
    public void cancelOrder(String uid, String cancelOrder) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().cancelOrder(uid,cancelOrder), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata) {
                    mView.onCancelOrderSuccessed(commonBean.getMsg());
                } else {
                    mView.onCancelOrderFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
