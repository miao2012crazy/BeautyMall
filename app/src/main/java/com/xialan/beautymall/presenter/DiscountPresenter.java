package com.xialan.beautymall.presenter;

import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.contract.DiscountContract;

/**
 * Created by Administrator on 2017/11/20.
 */

public class DiscountPresenter extends BasePresenter implements DiscountContract.Presenter {

    private final DiscountContract.View mView;

    public DiscountPresenter(DiscountContract.View view) {
        this.mView=view;
    }

    @Override
    public void getDiscountData() {
        HomeBean.DataBean dataBean = MyApplaction.dataBean;
        if (dataBean!=null) {
            mView.onDiscountDataSuccessed(dataBean.getProduct_discount());
        }else{
            mView.onDiscountDataFailed("没有数据!");
        }
    }
}
