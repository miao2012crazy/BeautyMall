package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.DetailContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/22.
 */

public class DetailPresenter extends BasePresenter implements DetailContract.Presenter {
    private final DetailContract.View mView;

    public DetailPresenter(DetailContract.View view) {
        this.mView=view;
    }

    @Override
    public void addToShoppingCart(String product_id, String uid, String product_num,String option_no) {

        mView.showCustomProgressBar("正在加入...");
        NovateUtil.getInstance().call(NovateUtil.getApiService().addShoppingCart(product_id,uid,product_num,option_no), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
                UIUtils.showToast("连接服务器失败!");
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata1 = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata1){
                    mView.onAddSuccess();
                }else{
                    mView.onAddFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });




    }
}
