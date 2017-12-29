package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.MainContract;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private final MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        this.mView=view;
    }
}
