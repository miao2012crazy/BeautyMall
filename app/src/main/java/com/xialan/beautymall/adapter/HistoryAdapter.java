package com.xialan.beautymall.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.HistoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class HistoryAdapter  extends BaseQuickAdapter<HistoryBean.DataBean,BaseViewHolder>{


    public HistoryAdapter( List<HistoryBean.DataBean> data) {
        super(R.layout.history_item_1, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HistoryBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_time,dataBean.getDate())
                .setText(R.id.tv_address,dataBean.getAddress());
    }
}
