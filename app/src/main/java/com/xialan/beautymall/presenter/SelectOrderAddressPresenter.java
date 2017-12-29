package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.contract.AddressManageContract;
import com.xialan.beautymall.contract.SelectOrderAddressContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SelectOrderAddressPresenter extends AddressManagePresenter implements SelectOrderAddressContract.Presenter {
    private final SelectOrderAddressContract.View mView;

    public SelectOrderAddressPresenter(SelectOrderAddressContract.View view) {
        super(view);
        this.mView=view;
    }

    @Override
    public void setRecvAddress(String uid, String order_id, String address_id) {
        NovateUtil.getInstance().call(NovateUtil.getApiService().updateOrderAddress(uid,order_id,address_id), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.onSetRecvAddressSuccessed();
                }else{
                    mView.onSetRecvAddressFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
