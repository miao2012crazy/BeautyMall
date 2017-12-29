package com.xialan.beautymall.contract;

import com.xialan.beautymall.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public interface DiscountContract {
    interface Model {
    }

    interface View {
        void onDiscountDataSuccessed(List<HomeBean.DataBean.ProductDiscountBean> discountBeanList);
        void onDiscountDataFailed(String err_msg);
    }

    interface Presenter {
        void getDiscountData();
    }
}
