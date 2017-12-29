package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.HistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface SkulpContract {
    interface Model {
    }

    interface View extends BaseView {
        void getDataSuccessed(List<HistoryBean.DataBean> dataBeans);
        void getDataFailed(String err_msg);
    }
    interface Presenter {
        void getListDataFromNet(String uid,String item_type,String index);
    }
}
