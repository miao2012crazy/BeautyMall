package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.ShoppingBean;
import com.xialan.beautymall.contract.UpdatePassWordContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/13.
 */

public class UpdatePassWordPresenter extends BasePresenter implements UpdatePassWordContract.Presenter {

    private final UpdatePassWordContract.View mView;

    public UpdatePassWordPresenter(UpdatePassWordContract.View view) {
        this.mView=view;
    }

    @Override
    public void updatePsd(String uid, String old_psd, String new_psd) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().updateUserPsd(uid,old_psd,new_psd), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.onUpdateSuccessed();
                }else{
                    mView.onUpdateFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
