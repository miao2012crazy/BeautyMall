package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommitOrderResponseBean;
import com.xialan.beautymall.bean.HistoryBean;
import com.xialan.beautymall.bean.PrepareOrderBean;
import com.xialan.beautymall.contract.SettlementContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.view.SettlementActivity;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/13.
 */
public class SettlementPresenter extends BasePresenter implements SettlementContract.Presenter {
    private final SettlementContract.View mView;

    public SettlementPresenter(SettlementContract.View view) {
        this.mView=view;
    }

    @Override
    public void getDataFromNet(String uid, String cart_list) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getPrepareDetail(uid,cart_list), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                PrepareOrderBean prepareOrderBean = ParseUtils.parseData(responseBody, PrepareOrderBean.class);
                assert prepareOrderBean != null;
                boolean checkdata = ParseUtils.checkdata(prepareOrderBean.getCode());
                if (checkdata){
                    mView.OnGetDataSuccessed(prepareOrderBean.getData());
                }else{
                    mView.OnGetDataFailed(prepareOrderBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });




    }

    @Override
    public void commitDataToNet(String uid, String prepare_id, String address_id, String message) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().CommitOrderDataToNet(uid,prepare_id,address_id,message), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {

                CommitOrderResponseBean commitOrderResponseBean = ParseUtils.parseData(responseBody, CommitOrderResponseBean.class);
                assert commitOrderResponseBean != null;
                boolean checkdata = ParseUtils.checkdata(commitOrderResponseBean.getCode());
                if (checkdata){
                    mView.onCommitSuccessed(commitOrderResponseBean.getData());
                }else{
                    mView.onCommitFailed(commitOrderResponseBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });

    }
}
