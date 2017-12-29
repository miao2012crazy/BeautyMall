package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.HomeBean;

import java.util.List;

/**
 * Created by ${苗} on 2017/11/8.
 */

public interface HomeContract {


    interface View extends BaseView{

        /**
         * 打折商品数据
         * @param dataBean 数据源
         */
        void onGetDataSuccessed(HomeBean.DataBean dataBean);

        /**
         * 获取数据失败
         * @param err_msg
         */
        void onGetDataFailed(String err_msg);
    }
    interface Presenter {
    void getHomeData();

    }
}
