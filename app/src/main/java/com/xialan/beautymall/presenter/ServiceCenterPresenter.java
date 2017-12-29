package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.ServiceCenterContract;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ServiceCenterPresenter extends BasePresenter implements ServiceCenterContract.Presenter {
    private final ServiceCenterContract.View mView;

    public ServiceCenterPresenter(ServiceCenterContract.View view) {
        this.mView=view;
    }
}
