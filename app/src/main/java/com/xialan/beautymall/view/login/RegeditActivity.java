package com.xialan.beautymall.view.login;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.RegeditContract;
import com.xialan.beautymall.presenter.RegeditPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/17.
 */
public class RegeditActivity extends BaseActivity implements RegeditContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.et_user_tel)
    EditText etUserTel;
    @BindView(R.id.et_vetify_code)
    EditText etVetifyCode;
    @BindView(R.id.btn_get_verify_code)
    Button btnGetVerifyCode;
    @BindView(R.id.btn_regedit)
    Button btnRegedit;
    private RegeditPresenter regeditPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_regedit;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        regeditPresenter = new RegeditPresenter(this);
        return regeditPresenter;
    }


    @OnClick({R.id.btn_get_verify_code, R.id.btn_regedit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_verify_code:
                //获取验证码

                break;
            case R.id.btn_regedit:
                //注册

                break;
        }
    }
}
