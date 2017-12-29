package com.xialan.beautymall.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.LoginContract;
import com.xialan.beautymall.presenter.LoginPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.SharePreUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.login.ForgetPsdActivity;
import com.xialan.beautymall.view.login.RegeditActivity;
import com.xialan.beautymall.wxapi.WXIDConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/12.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.et_user_tel)
    EditText etUserTel;
    @BindView(R.id.et_user_psd)
    EditText etUserPsd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_psd)
    TextView tvForgetPsd;
    @BindView(R.id.tv_regedit_new_user)
    TextView tvRegeditNewUser;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    private LoginPresenter loginPresenter;
    private String user_tel;
    private IWXAPI api;

    @Override
    protected int setlayoutID() {
        return R.layout.layout_login;
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(this, WXIDConstants.APP_ID, false);
        //将应用注册到微信
        api.registerApp(WXIDConstants.APP_ID);

//        Toolbar toolbar = getToolbar();
//        if (toolbar != null) {
//            toolbar.setTitle("登录");
//            toolbar.setNavigationIcon(R.mipmap.home);
//        }
    }

    @Override
    protected BasePresenter createPresenter() {
        loginPresenter = new LoginPresenter(this);
        return loginPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_return, R.id.btn_login, R.id.tv_forget_psd, R.id.tv_regedit_new_user, R.id.ib_btn_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.btn_login:
                initLogin();
                break;
            case R.id.tv_forget_psd:
                startActivity(new Intent(this, ForgetPsdActivity.class));
                break;
            case R.id.tv_regedit_new_user:
                //注册新用户
                Intent intent = new Intent(this, RegeditActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_btn_wechat:
                //启动微信登录
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "diandi_wx_login";
                api.sendReq(req);
                finish();
                break;
        }
    }

    /**
     * 登录
     */
    private void initLogin() {
        user_tel = etUserTel.getText().toString();
        String psd = etUserPsd.getText().toString();
        if (StringUtil.isEmpty(user_tel)) {
            UIUtils.showToast("手机号码不能为空");
            return;
        }
        if (StringUtil.isEmpty(psd)) {
            UIUtils.showToast("密码不能为空");
            return;
        }
        if (!UIUtils.isTelPhoneNumber(user_tel)) {
            UIUtils.showToast("手机号码格式不正确");
            return;
        }
        loginPresenter.getLogin(user_tel, psd);
    }

    @Override
    public void loginSuccessed(UserInfoBean.DataBean userInfoBean) {
        UIUtils.showToast("登录成功");
        MyApplaction.userInfoBean = userInfoBean;
        MyApplaction.userInfoBean.setUid(user_tel);
        SharePreUtils.put(UIUtils.getContext(),"uid",user_tel);
        SharePreUtils.put(UIUtils.getContext(),"age",userInfoBean.getUser_age());
        SharePreUtils.put(UIUtils.getContext(),"name",userInfoBean.getUser_name());
        SharePreUtils.put(UIUtils.getContext(),"sex",userInfoBean.getUser_sex());
        SharePreUtils.put(UIUtils.getContext(),"img",userInfoBean.getUser_img());
        SharePreUtils.put(UIUtils.getContext(),"wechat",userInfoBean.getWechat_state());
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(msg));
    }


}
