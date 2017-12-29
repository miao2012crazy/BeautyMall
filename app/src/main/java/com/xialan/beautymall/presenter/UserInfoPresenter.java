package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.UserInfoContract;

/**
 * Created by Administrator on 2017/11/13.
 */

public class UserInfoPresenter extends BasePresenter implements UserInfoContract.Presenter {
    private final UserInfoContract.View mView;

    public UserInfoPresenter(UserInfoContract.View view) {
        this.mView=view;
    }
}
