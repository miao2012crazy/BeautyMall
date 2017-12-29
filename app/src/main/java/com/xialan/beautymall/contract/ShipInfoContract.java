package com.xialan.beautymall.contract;

import com.xialan.beautymall.base.BaseView;
import com.xialan.beautymall.bean.ShipInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public interface ShipInfoContract {
    interface Model {
    }

    interface View  extends BaseView{
        void  OnGetShipInfoSuccessed(List<ShipInfoBean.ResponsesBean.ResponseBean.ScanInfosBean.ScanInfoBean> scanInfoBeans);
        void  OnGetShipInfoFailed(String msg);
    }

    interface Presenter {
        void getShipInfo(String ship_order);
    }
}
