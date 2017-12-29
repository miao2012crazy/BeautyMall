package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;

/**
 * Created by Administrator on 2017/11/22.
 */

public interface DetailContract {
    interface Model {
    }

    interface View  extends BaseView{
        /**
         * 加入成功
         */
        void onAddSuccess();

        /**
         * 加入失败
         * @param err_msg
         */
        void onAddFailed(String err_msg);
    }


    interface Presenter {
        /**
         * 加入购物车
         * @param product_id 商品id
         * @param uid 用户id
         * @param product_num 商品数量
          */
        void addToShoppingCart(String product_id,String uid,String product_num,String option_no);

    }
}
