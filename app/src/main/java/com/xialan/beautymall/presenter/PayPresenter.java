package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AliPayStrBean;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.PayPrepareIDBean;
import com.xialan.beautymall.contract.PayContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/14.
 */

public class PayPresenter extends BasePresenter implements PayContract.Presenter {
    private PayContract.View mView;

    public PayPresenter(PayContract.View view) {
        this.mView=view;
    }

    @Override
    public void getPrepareId(String order_code, String fee, String product_name) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getPrepareID(order_code,fee,product_name), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                PayPrepareIDBean payPrepareIDBean = ParseUtils.parseData(responseBody, PayPrepareIDBean.class);
                boolean checkdata = ParseUtils.checkdata(payPrepareIDBean.getCode());
                if (checkdata) {
                    mView.onGetPrepareIdSuccessed(payPrepareIDBean.getData().getPrepare_id_pay());
                } else {
                    mView.onGetPrepareIdFailed(payPrepareIDBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
    @Override
    public void getOrderStrForAlipay(String order_code,String fee,String product_name) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getOrderStrForAliPay(order_code,fee,product_name), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                AliPayStrBean aliPayStrBean = ParseUtils.parseData(responseBody, AliPayStrBean.class);
                boolean checkdata = ParseUtils.checkdata(aliPayStrBean.getCode());
                if (checkdata) {
                    mView.onGetOrderStrSuccessed(aliPayStrBean.getData().getOrder_info());
                } else {
                    mView.onGetPrepareIdFailed(aliPayStrBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });

    }
}
