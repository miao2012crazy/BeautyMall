package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

/**
 * Created by Administrator on 2017/11/13.
 */

public interface UpdatePassWordContract {

    interface View  extends BaseView{
        void onUpdateSuccessed();
        void onUpdateFailed(String err_msg);
    }

    interface Presenter {
        void updatePsd(String uid,String old_psd,String new_psd);
    }
}
