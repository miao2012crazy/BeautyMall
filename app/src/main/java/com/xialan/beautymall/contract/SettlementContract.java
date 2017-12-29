package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.CommitOrderResponseBean;
import com.xialan.beautymall.bean.PrepareOrderBean;

/**
 * Created by Administrator on 2017/12/13.
 */

public interface SettlementContract {
    interface Model {
    }

    interface View extends BaseView{
        void OnGetDataSuccessed(PrepareOrderBean.DataBean dataBean);
        void OnGetDataFailed(String err_msg);

        void onCommitSuccessed(CommitOrderResponseBean.DataBean dataBean);
        void onCommitFailed(String err_msg);

    }

    interface Presenter {
        void  getDataFromNet(String uid,String cart_list);
        void commitDataToNet(String uid,String prepare_id,String address_id,String message);
    }
}
