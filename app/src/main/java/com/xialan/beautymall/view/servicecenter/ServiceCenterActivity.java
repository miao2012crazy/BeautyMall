package com.xialan.beautymall.view.servicecenter;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.ServiceCenterContract;
import com.xialan.beautymall.presenter.ServiceCenterPresenter;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ServiceCenterActivity extends BaseActivity implements ServiceCenterContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;


    @Override
    protected int setlayoutID() {
        return R.layout.activity_service_center;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {

        ServiceCenterPresenter serviceCenterPresenter = new ServiceCenterPresenter(this);
        return serviceCenterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_common_problem, R.id.ll_user_service_tel, R.id.ll_feed_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_common_problem:
                break;
            case R.id.ll_user_service_tel:

                break;
            case R.id.ll_feed_back:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("服务中心");
        setIVReturn();

    }
}
