package com.xialan.beautymall.presenter;

import android.app.Activity;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.LoginContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.SharePreUtils;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.LoginActivity;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/12.
 */

public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {
    private final LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView=view;
    }

    @Override
    public void getLogin(String user_tel, String user_psd) {

        mView.showCustomProgressBar("登录中...");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getLoginData(user_tel,user_psd), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
                UIUtils.showToast("网络连接失败!");
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                UserInfoBean userInfoBean = ParseUtils.parseData(responseBody, UserInfoBean.class);
                assert userInfoBean != null;
                boolean checkdata = ParseUtils.checkdata(userInfoBean.getCode());
                if (checkdata){
                    mView.loginSuccessed(userInfoBean.getData());
                    //登录成功 保存用户数据
                        SharePreUtils.put(UIUtils.getContext(),"LOGIN_TIME",UIUtils.getTime());
                }else{
                    mView.loginFailed(userInfoBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });


    }


}
