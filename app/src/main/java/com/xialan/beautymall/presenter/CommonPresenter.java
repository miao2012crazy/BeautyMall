package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.CommonContract;

/**
 * Created by Administrator on 2017/12/6.
 */

public class CommonPresenter extends BasePresenter implements CommonContract.Presenter {
    private final CommonContract.View mView;

    public CommonPresenter(CommonContract.View view) {
        this.mView=view;
    }
}
