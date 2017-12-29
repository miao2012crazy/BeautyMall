package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

import java.io.File;

/**
 * Created by Administrator on 2017/11/13.
 */

public interface UserinfoManageContract {


    interface View extends BaseView{
        void updateInfoSuccessed();
        void updateInfoFailed(String err_msg);
    }

    interface Presenter {
        void updateUserInfo(String uid,String user_nick_name,String user_age,String user_sex,File file);
    }
}
