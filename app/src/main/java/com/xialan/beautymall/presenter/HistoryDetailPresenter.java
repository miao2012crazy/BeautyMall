package com.xialan.beautymall.presenter;

import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.HistoryDetailContract;

/**
 * Created by Administrator on 2017/12/6.
 */

public class HistoryDetailPresenter extends BasePresenter implements HistoryDetailContract.Presenter {
    private final HistoryDetailContract.View mView;

    public HistoryDetailPresenter(HistoryDetailContract.View view) {
        this.mView=view;
    }
}
