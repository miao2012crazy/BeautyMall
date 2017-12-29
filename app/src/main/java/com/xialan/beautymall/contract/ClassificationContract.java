package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.PopMenuBean;
import com.xialan.beautymall.bean.ProductBean;

import java.util.List;

/**
 * Created by ${苗} on 2017/11/8.
 */

public interface ClassificationContract {


    interface View extends BaseView{
        /**
         * 数据获取成功
         * @param dataBeans
         */
        void onGetListDataSuccess(List<ProductBean.DataBean> dataBeans);

        /**
         * 数据获取失败
         * @param msg 提示信息
         */
        void onGetListDataFailed(String msg);

        void onGetCategorySuccessed(List<PopMenuBean.DataBean> dataBeans);
        void onGetCategoryFailed(String err_msg);
    }

    interface Presenter {
        /**
         *
         * @param index 分页索引
         * @param categroy  分类 id
         * @param sort_type 排序方式
         *@param order_type 高低顺序
         */
        void getListDataFromNet(String index,String categroy,String sort_type,String order_type,String search_value);
        void getCategory();
    }
}
