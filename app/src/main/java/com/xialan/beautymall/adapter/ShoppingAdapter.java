package com.xialan.beautymall.adapter;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.ShoppingBean;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ShoppingAdapter extends BaseQuickAdapter<ShoppingBean.DataBean, BaseViewHolder> {
    //这个是checkbox的Hashmap集合
    private HashMap<Integer, Boolean> map = new HashMap<>();
    private List<ShoppingBean.DataBean> check_data = new ArrayList<>();
    private List<ShoppingBean.DataBean> mData = new ArrayList<>();
    private boolean check_all = false;

    public ShoppingAdapter(List<ShoppingBean.DataBean> data) {
        super(R.layout.shopping_item, data);
        mData = data;
        initMap(data);
    }

    private void initMap(List<ShoppingBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            map.put(i, check_all);
        }
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShoppingBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_product_name_shop, dataBean.getProduct_name())
                .setText(R.id.tv_specifications_shop, "规格：" + dataBean.getProduct_norms())
                .setText(R.id.tv_product_price_shop, "¥"+dataBean.getProduct_price())
                .setText(R.id.tv_sum, dataBean.getProduct_num())
                .addOnClickListener(R.id.btn_reduce)
                .addOnClickListener(R.id.btn_add)
                .addOnClickListener(R.id.cb_check);
        ImageView view = (ImageView) baseViewHolder.getView(R.id.iv_product_img_shop);
        ImageLoaderManager.getImageLoader().displayImage(dataBean.getProduct_url(), view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        CheckBox cb_check = (CheckBox) holder.getView(R.id.cb_check);
        try {
            cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    map.put(positions, isChecked);
                    RxBus.get().post("CHECK_CHANGE", "");
                }
            });
        } catch (Exception e) {
        }
        // 设置CheckBox的状态
        if (map.get(positions) == null) {
            map.put(positions, false);
        }
        try {
            cb_check.setChecked(map.get(positions));
        } catch (Exception e) {

        }

    }

    /**
     * 获取选中集合
     *
     * @return
     */
    public List<ShoppingBean.DataBean> getCheckData() {
        check_data.clear();
        for (int i = 0; i < map.size(); i++) {
            Boolean aBoolean = map.get(i);
            if (aBoolean) {
                ShoppingBean.DataBean dataBean = mData.get(i);
                check_data.add(dataBean);
            }
        }
        return check_data;
    }

    /**
     * 全选和反选
     *
     * @param check
     */
    public void setCheckAll(boolean check) {
        for (int i = 0; i < mData.size(); i++) {
            map.put(i, check);
        }
    }
}
