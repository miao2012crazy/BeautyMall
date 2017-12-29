package com.xialan.beautymall.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.AddressBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */
public class SelectAddressAdapter extends BaseQuickAdapter<AddressBean.DataBean, BaseViewHolder> {
    //这个是checkbox的Hashmap集合
    private HashMap<Integer, Boolean> map = new HashMap<>();
    //是否显示单选框,默认false
    private boolean isshowBox = true;
    private List<AddressBean.DataBean> mData = null;
    private int lastPostion=-1;

    public SelectAddressAdapter(List<AddressBean.DataBean> data) {
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
        baseViewHolder.setChecked(R.id.cb_check, dataBean.isIs_checked())
                .setText(R.id.tv_address_detail, dataBean.getAddress_class_b())
                .setText(R.id.tv_consignee_tel, dataBean.getAddress_user_tel())
                .setText(R.id.tv_recv_name,dataBean.getAddress_recv_name())
                .setVisible(R.id.tv_is_default, checkDefault(dataBean.getIs_default()));
    }

    private boolean checkDefault(String is_default) {
        if (is_default.equals("1")) {
            return true;
        } else {
            return false;
        }
    }
}
