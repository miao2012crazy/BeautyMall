package com.xialan.beautymall.view.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.ForgetPsdContract;
import com.xialan.beautymall.presenter.ForgetPsdPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ForgetPsdActivity extends BaseActivity implements ForgetPsdContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.et_user_tel)
    EditText etUserTel;
    @BindView(R.id.et_vetify_code)
    EditText etVetifyCode;
    @BindView(R.id.btn_get_verify_code)
    Button btnGetVerifyCode;
    @BindView(R.id.et_psd)
    EditText etPsd;
    @BindView(R.id.et_confirm_psd)
    EditText etConfirmPsd;
    @BindView(R.id.update_psd)
    Button updatePsd;
    private ForgetPsdPresenter forgetPsdPresenter;
    private String mobile;
    private String etpsd;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_forget_psd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        forgetPsdPresenter = new ForgetPsdPresenter(this);
        return forgetPsdPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_get_verify_code, R.id.update_psd})
    public void onViewClicked(View view) {
        mobile = etUserTel.getText().toString();
        boolean telPhoneNumber = UIUtils.isTelPhoneNumber(mobile);
        if (!telPhoneNumber){
            UIUtils.showToast("手机号码输入有误!");
            return;
        }
        switch (view.getId()) {
            case R.id.btn_get_verify_code:
                    //获取验证码接口
                    forgetPsdPresenter.getVerifyCode(mobile);
                break;
            case R.id.update_psd:
                //修改密码
                etpsd = etPsd.getText().toString();
                String etconpsd = etConfirmPsd.getText().toString();
                if (TextUtils.isEmpty(etpsd) || !etpsd.matches("^[a-zA-Z0-9]{6,12}$")) {
                    UIUtils.showToast("未输入密码或非法字符!");
                    return;
                }
                if (TextUtils.isEmpty(etconpsd) || !etconpsd.matches("^[a-zA-Z0-9]{6,12}$")) {
                    UIUtils.showToast("未输入确认密码或非法字符!");
                    return;
                }
                if (!etconpsd.equals(etpsd)) {
                    UIUtils.showToast("两次输入密码不一致!");
                    return;
                }
                String str1 = etVetifyCode.getText().toString();
                if (TextUtils.isEmpty(str1)) {
                    UIUtils.showToast( "需要填写验证码!");
                    return;
                }
                forgetPsdPresenter.checkVerifyCode(str1.trim());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("忘记密码");
        setIVReturn();
    }

    @Override
    public void onGetVerifyCodeSuccess() {
        RxBus.countdown(60)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
//                        appendLog("开始计时");
                        btnGetVerifyCode.setClickable(false);
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        btnGetVerifyCode.setClickable(true);
//                        appendLog("计时完成");
                        btnGetVerifyCode.setText("再次获取");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
//                        appendLog("当前计时：" + integer);
                        btnGetVerifyCode.setText(String.valueOf(integer)+"s");
                    }
                });

    }

    @Override
    public void onGetVerifyCodeFailed(String err_msg) {
        UIUtils.showToast(err_msg);
    }

    @Override
    public void OnCheckVerifyCodeSuccess() {
        forgetPsdPresenter.upDatePsd(mobile,etpsd);
    }

    @Override
    public void OnCheckVerifyCodeFailed(String err_msg) {
        UIUtils.showToast(err_msg);
    }

    @Override
    public void onUpdatePsdSuccessed() {
        UIUtils.showToast("密码修改成功");
        finish();
    }

    @Override
    public void onUpdatePsdFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }
}
