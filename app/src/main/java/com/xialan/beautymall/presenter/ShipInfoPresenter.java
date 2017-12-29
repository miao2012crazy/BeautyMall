package com.xialan.beautymall.presenter;

import com.google.gson.Gson;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.ShipInfoBean;
import com.xialan.beautymall.bean.ShoppingBean;
import com.xialan.beautymall.contract.ShipInfoContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ShipInfoPresenter extends BasePresenter implements ShipInfoContract.Presenter {

    private ShipInfoContract.View mView;

    public ShipInfoPresenter(ShipInfoContract.View view) {
        this.mView = view;
    }

    @Override
    public void getShipInfo(String ship_order) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getGoodShipInfo(ship_order), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Gson gson = new Gson();
                try {
                    ShipInfoBean shipInfoBean = gson.fromJson(responseBody.string(), ShipInfoBean.class);
                    String status = shipInfoBean.getResponses().getResponse().getStatus();
                    switch (status) {
                        case "0":
                            mView.OnGetShipInfoFailed(shipInfoBean.getResponses().getResponse().getMsg());
                            break;
                        case "1":
                            mView.OnGetShipInfoSuccessed(shipInfoBean.getResponses().getResponse().getScan_infos().getScan_info());
                            break;
                        case "2":
                            mView.OnGetShipInfoFailed(shipInfoBean.getResponses().getResponse().getMsg());
                            break;
                        default:
                            UIUtils.showToast("物流信息异常!请稍后尝试");
                            break;
                    }
                } catch (IOException e) {
                    UIUtils.showToast("物流信息异常!请稍后尝试!");
                    e.printStackTrace();
                }
                mView.hideCustomProgressBar();
            }

        });
    }
}
