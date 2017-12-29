package com.xialan.beautymall.view.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xialan.beautymall.R;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.CommonContract;
import com.xialan.beautymall.presenter.CommonPresenter;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/6.
 */
public class HistoryActivity extends BaseActivity implements CommonContract.View {

    @BindView(R.id.cv_bna)
    RelativeLayout cvBna;
    @BindView(R.id.cv_skulp)
    RelativeLayout cvSkulp;
    @BindView(R.id.cv_skin)
    RelativeLayout cvSkin;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;



    @Override
    protected int setlayoutID() {
        return R.layout.activity_history;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        CommonPresenter commonPresenter = new CommonPresenter(this);
        return commonPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cv_bna, R.id.cv_skulp, R.id.cv_skin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_bna:
                startActivity(new Intent(HistoryActivity.this, BNAActivity.class));
                break;
            case R.id.cv_skulp:
                startActivity(new Intent(HistoryActivity.this, SkulpActivity.class));
                break;
            case R.id.cv_skin:
                startActivity(new Intent(HistoryActivity.this, SkinActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        tvTitle.setText("历史记录");
        setIVReturn();
    }
}
