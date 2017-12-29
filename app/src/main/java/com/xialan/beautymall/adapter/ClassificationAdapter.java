package com.xialan.beautymall.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.ui.FlowLayout;
import com.xialan.beautymall.utils.DrawableUtil;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by ${苗} on 2017/11/10.
 */

public class ClassificationAdapter extends BaseQuickAdapter<ProductBean.DataBean, BaseViewHolder> {
    private List<String> tagList=new ArrayList<>();
    public ClassificationAdapter(List<ProductBean.DataBean> data) {
        super(R.layout.product_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProductBean.DataBean dataBean) {
        ImageView view = (ImageView) baseViewHolder.getView(R.id.iv_product_img);
        FlowLayout ll_container = (FlowLayout) baseViewHolder.getView(R.id.ll_container);
        TextView tv_original_price = (TextView) baseViewHolder.getView(R.id.tv_original_price);
        tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ImageLoaderManager.displayImageList(dataBean.getImage_url(), view, 2);
        baseViewHolder.setText(R.id.tv_product_title, dataBean.getProduct_name())
                .setText(R.id.tv_price, "¥" + dataBean.getProduct_price())
                .setText(R.id.tv_original_price, "原价：¥" + dataBean.getProduct_original_price())
                .setTag(R.id.card_view_training, dataBean.getProduct_id());
        tagList.clear();
        tagList.addAll(dataBean.getProduct_tag());
        inittag(ll_container, tagList);
    }

    private void inittag(FlowLayout ll_container, List<String> product_tag) {
        ll_container.removeAllViews();
        for (String item : product_tag) {
            View inflate = UIUtils.inflate(R.layout.flow_text);
            TextView tv_tag = (TextView) inflate.findViewById(R.id.tv_tag);
            tv_tag.setText(item);
            int red = 30+new Random().nextInt(210);
            int green = 30+new Random().nextInt(210);
            int blue = 30+new Random().nextInt(210);
            tv_tag.setBackgroundColor(Color.parseColor("#aaaaaa"));
            //随机颜色
//            int rgb = Color.rgb(red, green, blue);
            tv_tag.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.shape_tv_back));
            //设置未选中图片
//            int radius = UIUtils.dip2px(1);
//            Drawable drawableBitmapPress = DrawableUtil.drawableBitmap(rgb,radius);
//            //设置选中图片
//            Drawable drawableBitmapNormal = DrawableUtil.drawableBitmap(Color.GRAY,radius);
            //添加状态选择器
//            StateListDrawable drawableState = DrawableUtil.getStateListDrawable(drawableBitmapPress,drawableBitmapNormal);
//            tv_tag.setBackgroundDrawable(drawableState);
            ll_container.addView(inflate);
        }
    }

}
