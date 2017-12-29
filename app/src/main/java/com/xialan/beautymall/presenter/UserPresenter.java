package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.UserContract;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class UserPresenter extends BasePresenter implements UserContract.Presenter {

    private final UserContract.View mView;

    public UserPresenter(UserContract.View view) {
        this.mView=view;
    }
}
