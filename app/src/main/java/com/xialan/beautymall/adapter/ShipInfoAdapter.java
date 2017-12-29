package com.xialan.beautymall.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.ShipInfoBean;
import com.xialan.beautymall.utils.StringUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ShipInfoAdapter extends BaseQuickAdapter<ShipInfoBean.ResponsesBean.ResponseBean.ScanInfosBean.ScanInfoBean, BaseViewHolder> {

    public ShipInfoAdapter(List<ShipInfoBean.ResponsesBean.ResponseBean.ScanInfosBean.ScanInfoBean> data) {
        super(R.layout.activity_shipment_info, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShipInfoBean.ResponsesBean.ResponseBean.ScanInfosBean.ScanInfoBean scanInfoBean) {
        baseViewHolder.setText(R.id.tv_ship_time, scanInfoBean.getTime())
                .setText(R.id.tv_ship_tag, scanInfoBean.getScan_type())
                .setText(R.id.tv_ship_describe, scanInfoBean.getRemark());
        TextView view = (TextView) baseViewHolder.getView(R.id.tv_ship_tag);
        String scan_type = scanInfoBean.getScan_type();
        if (StringUtil.isEmpty(scan_type)||scan_type.equals("NULL")){
            view.setVisibility(View.GONE);
        }else{
            view.setText(getTag(scan_type));
        }
    }

    private String getTag(String scan_type) {
        switch (scan_type){
            case "got":
                return "揽件扫描";
            case "in":
                return "中转";
            case "weight":
                return "称重";
            case "arrived":
                return "到件";
            case "out":
                return "离开中转";
            case "next":
                return "下级站点扫描";
            case "air":
                return "航空扫描";
            case "deliver":
                return "派件";
            case "signfail":
                return "签收异常";
            case "goback":
                return "回退件";
            case "signed":
                return "签收";
            default :
                return "运输中";
        }
    }
}
