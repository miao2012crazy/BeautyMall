package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HistoryBean;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.contract.BNAContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/6.
 */

public class BNAPresenter extends BasePresenter implements BNAContract.Presenter {
    private final BNAContract.View mView;

    public BNAPresenter(BNAContract.View view) {
        this.mView=view;
    }

    @Override
    public void getListDataFromNet(String uid, String item_type, String index) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getHistoryData(uid,item_type,index), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                HistoryBean historyBean = ParseUtils.parseData(responseBody, HistoryBean.class);
                assert historyBean != null;
                boolean checkdata = ParseUtils.checkdata(historyBean.getCode());
                if (checkdata){
                    mView.getDataSuccessed(historyBean.getData());
                }else{
                    mView.getDataFailed(historyBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
