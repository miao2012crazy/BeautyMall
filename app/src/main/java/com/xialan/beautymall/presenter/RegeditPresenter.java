package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.RegeditContract;

/**
 * Created by Administrator on 2017/11/17.
 */

public class RegeditPresenter extends BasePresenter implements RegeditContract.Presenter {
    private  RegeditContract.View mView;

    public RegeditPresenter(RegeditContract.View view) {
        this.mView=view;
    }
}
