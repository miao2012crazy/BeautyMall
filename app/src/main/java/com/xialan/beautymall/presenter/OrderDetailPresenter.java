package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.OrderDetailBean;
import com.xialan.beautymall.contract.OrderDetailContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/7.
 */

public class OrderDetailPresenter extends BasePresenter implements OrderDetailContract.Presenter {
    private final OrderDetailContract.View mView;

    public OrderDetailPresenter(OrderDetailContract.View view) {
        this.mView=view;
    }

    @Override
    public void cancelOrder(String uid, String order_code) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().cancelOrder(uid,order_code), new BaseSubscriber<ResponseBody>() {
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

    @Override
    public void getDetailForOrder(String uid, String order_code) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getDetailForOrder(uid,order_code), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                UIUtils.showToast("网络连接失败");
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                OrderDetailBean orderDetailBean = ParseUtils.parseData(responseBody, OrderDetailBean.class);
                boolean checkdata = ParseUtils.checkdata(orderDetailBean.getCode());
                if (checkdata) {
                    mView.getOrderDetailSuccess(orderDetailBean.getData());
                } else {
                    mView.getOrderDetailFailed(orderDetailBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
