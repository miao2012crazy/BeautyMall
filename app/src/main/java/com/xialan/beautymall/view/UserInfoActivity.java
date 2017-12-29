package com.xialan.beautymall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.UserInfoContract;
import com.xialan.beautymall.presenter.UserInfoPresenter;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/13.
 */
public class UserInfoActivity extends BaseActivity implements UserInfoContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.card_user_info)
    TextView cardUserInfo;
    @BindView(R.id.card_reset_psd)
    TextView cardResetPsd;
    @BindView(R.id.card_iden)
    TextView cardIden;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);
        return userInfoPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.card_user_info, R.id.card_reset_psd,R.id.card_iden})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_user_info:
                startActivity(new Intent(UserInfoActivity.this, UserinfoManageActivity.class));
                break;
            case R.id.card_reset_psd:
                startActivity(new Intent(UserInfoActivity.this, UpdatePassWordActivity.class));
                break;
                case R.id.card_iden:
                    MyApplaction.UpdateIden=true;
                startActivity(new Intent(UserInfoActivity.this, UpdateIDENActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("账户管理");
        setIVReturn();
    }


}
