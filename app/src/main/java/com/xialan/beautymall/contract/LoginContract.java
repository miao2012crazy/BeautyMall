package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.UserInfoBean;

/**
 * Created by Administrator on 2017/11/12.
 */

public interface LoginContract {

    interface View extends BaseView{
        void loginSuccessed(UserInfoBean.DataBean dataBean);
        void loginFailed(String msg);
    }

    interface Presenter {
        void getLogin(String user_tel,String user_psd);


    }
}
