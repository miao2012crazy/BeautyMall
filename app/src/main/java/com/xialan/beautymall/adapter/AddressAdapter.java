package com.xialan.beautymall.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.AddressBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/13.
 */
public class AddressAdapter extends BaseQuickAdapter<AddressBean.DataBean, BaseViewHolder> {
    //这个是checkbox的Hashmap集合
    private HashMap<Integer, Boolean> map = new HashMap<>();
    //是否显示单选框,默认false
    private boolean isshowBox = false;
    private List<AddressBean.DataBean> mData = null;

    public AddressAdapter(List<AddressBean.DataBean> data) {
        super(R.layout.address_item, data);
        mData = data;
        initMap(data);
    }

    private void initMap(List<AddressBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AddressBean.DataBean dataBean) {
        baseViewHolder.setVisible(R.id.cb_check, isshowBox).addOnClickListener(R.id.cb_check)
                .setText(R.id.tv_address_detail, dataBean.getAddress_class_b())
                .setText(R.id.tv_consignee_tel, dataBean.getAddress_user_tel())
                .setVisible(R.id.tv_is_default, checkDefault(dataBean.getIs_default()))
        .setText(R.id.tv_recv_name,dataBean.getAddress_recv_name());
    }

    private boolean checkDefault(String is_default) {
        if (is_default.equals("1")) {
            return true;
        } else {
            return false;
        }
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

    public void setCheckBoxShow(boolean isVisiable) {
        isshowBox = isVisiable;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

}
