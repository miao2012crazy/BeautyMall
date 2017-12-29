package com.xialan.beautymall.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.utils.ImageLoaderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class DiscountAdapter extends BaseQuickAdapter<HomeBean.DataBean.ProductDiscountBean, BaseViewHolder> {

    public DiscountAdapter(List<HomeBean.DataBean.ProductDiscountBean> data) {
        super(R.layout.home_item_03, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeBean.DataBean.ProductDiscountBean productDiscountBean) {
        ImageView iv_item_3_image = (ImageView) baseViewHolder.getView(R.id.iv_item_3_image);
        ImageLoaderManager.displayImage(productDiscountBean.getCategory_img(), iv_item_3_image);
        baseViewHolder.setText(R.id.tv_item_3_title, productDiscountBean.getProduct_title())
                .setText(R.id.tv_discount_time, productDiscountBean.getProduct_sub_title());
    }
}
