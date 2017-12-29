package com.xialan.beautymall.contract;

import com.xialan.beautymall.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public interface HotContract {
    interface Model {
    }

    interface View {
        void  onHotDataSuccessed(List<HomeBean.DataBean.ProductHotBean> productHotBeans);
        void  onHotDataFailed(String err_msg);
    }

    interface Presenter {
        void getHotData();
    }
}
