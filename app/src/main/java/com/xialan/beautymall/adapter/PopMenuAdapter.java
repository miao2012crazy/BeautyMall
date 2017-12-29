package com.xialan.beautymall.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.PopMenuBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class PopMenuAdapter extends BaseQuickAdapter<PopMenuBean.DataBean,BaseViewHolder> {
    public PopMenuAdapter(List<PopMenuBean.DataBean> dataBeans) {
        super(R.layout.menu_item, dataBeans);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PopMenuBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_title,dataBean.getCategory_title())
                .setTag(R.id.card_view_pop,dataBean.getCategory_id());
    }
}
