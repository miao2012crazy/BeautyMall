package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.UserIdenBean;

import java.io.File;

/**
 * Created by Administrator on 2017/12/13.
 */

public interface UpdateIDENContract {
    interface Model {
    }

    interface View extends BaseView{
        void upDataSuccessed();
        void upDataFailed(String err_msg);

        void  getUserIdenSuccessed(UserIdenBean.DataBean dataBean);
        void  getUserIdenFailed(String err_msg);


    }

    interface Presenter {
        void upDataForUserIden(String uid, String real_name, String iden_no, String iden_type , File front_file, File bg_file);

        void getUserInfo(String uid);

    }
}
