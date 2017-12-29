package com.xialan.beautymall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.UpdatePassWordContract;
import com.xialan.beautymall.presenter.UpdatePassWordPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/13.
 */

public class UpdatePassWordActivity extends BaseActivity implements UpdatePassWordContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.et_old_psd)
    EditText etOldPsd;
    @BindView(R.id.et_new_psd)
    EditText etNewPsd;
    @BindView(R.id.et_confirm_new_psd)
    EditText etConfirmNewPsd;
    @BindView(R.id.btn_save_psd)
    Button btnSavePsd;
    private UpdatePassWordPresenter updatePassWordPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_update_psd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        updatePassWordPresenter = new UpdatePassWordPresenter(this);
        return updatePassWordPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save_psd)
    public void onViewClicked() {
        String oldPsd = etOldPsd.getText().toString();
        String newPsd = etNewPsd.getText().toString();
        String confirmNewPsd = etConfirmNewPsd.getText().toString();
        if (StringUtil.isEmpty(oldPsd)){
            UIUtils.showToast("原密码不能为空");
        return;
        }
        if (StringUtil.isEmpty(newPsd)){
            UIUtils.showToast("新密码不能为空");
            return;
        }
        if (!confirmNewPsd.equals(newPsd)){
            UIUtils.showToast("两次输入密码不一致");
            return;
        }
        String uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        if (StringUtil.isEmpty(uid)){
            startActivity(new Intent(UpdatePassWordActivity.this,LoginActivity.class));
            return;
        }
        updatePassWordPresenter.updatePsd(uid,oldPsd,newPsd);
    }

    @Override
    public void onUpdateSuccessed() {
        UIUtils.showToast("密码修改成功");
        finish();
    }

    @Override
    public void onUpdateFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("修改密码");
        setIVReturn();
    }
}
