package com.xialan.beautymall.presenter;

import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.contract.HotContract;
import com.xialan.beautymall.utils.RxBus;

/**
 * Created by Administrator on 2017/11/20.
 */

public class HotPresenter extends BasePresenter implements HotContract.Presenter {
    private final HotContract.View mView;
    public HotPresenter(HotContract.View view) {
        this.mView = view;
    }

    @Override
    public void getHotData() {
        HomeBean.DataBean dataBean = MyApplaction.dataBean;
        if (dataBean!=null){
         mView.onHotDataSuccessed(dataBean.getProduct_hot());
        }else{
            mView.onHotDataFailed("没有数据");
        }
    }
}
