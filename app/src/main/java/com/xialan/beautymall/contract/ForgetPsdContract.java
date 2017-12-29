package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

/**
 * Created by Administrator on 2017/11/14.
 */

public interface ForgetPsdContract {
    interface Model {
    }

    interface View extends BaseView{
       void onGetVerifyCodeSuccess();
       void onGetVerifyCodeFailed(String err_msg);
        //验证码正确
        void OnCheckVerifyCodeSuccess();

        //验证码错误
        void OnCheckVerifyCodeFailed(String err_msg);

        void onUpdatePsdSuccessed();
        void onUpdatePsdFailed(String err_msg);
    }

    interface Presenter {
        void getVerifyCode(String mobile);
        void checkVerifyCode(String verify_code);
        void upDatePsd(String uid,String new_psd);

    }
}
