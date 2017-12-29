package com.xialan.beautymall.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.utils.ImageLoaderManager;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class HotAdapter extends BaseQuickAdapter<HomeBean.DataBean.ProductHotBean,BaseViewHolder>{
    public HotAdapter(List<HomeBean.DataBean.ProductHotBean> data) {
        super(R.layout.home_item_02,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeBean.DataBean.ProductHotBean productHotBean) {
        baseViewHolder.setText(R.id.tv_big_title,productHotBean.getProduct_title())
                .setText(R.id.tv_small_title,productHotBean.getProduct_sub_title()).addOnClickListener(R.id.iv_left).addOnClickListener(R.id.iv_right);
        ImageView iv_left = (ImageView) baseViewHolder.getView(R.id.iv_left);
        ImageView iv_right = (ImageView) baseViewHolder.getView(R.id.iv_right);
        iv_left.setTag(productHotBean.getProduct_id_left());
        iv_right.setTag(productHotBean.getProduct_id_right());
        ImageLoaderManager.displayImage(productHotBean.getProduct_img_left(),iv_left);
        ImageLoaderManager.displayImage(productHotBean.getProduct_img_right(),iv_right);
    }
}
