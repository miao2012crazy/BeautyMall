package com.xialan.beautymall.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.OrderDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ShipAdapter extends BaseQuickAdapter<OrderDetailBean.DataBean.ProductListBean,BaseViewHolder>{

    public ShipAdapter(List<OrderDetailBean.DataBean.ProductListBean> data) {
        super(R.layout.text_ship, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderDetailBean.DataBean.ProductListBean productListBean) {
        baseViewHolder.setText(R.id.tv_ship,productListBean.getProduct_name())
                .setTag(R.id.tv_ship,productListBean.getShipment_order());
    }
}
