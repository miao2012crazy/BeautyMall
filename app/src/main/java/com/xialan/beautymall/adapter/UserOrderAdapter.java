package com.xialan.beautymall.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.beautymall.R;
import com.xialan.beautymall.bean.UserOrderBean;
import com.xialan.beautymall.utils.CommonUtils;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.UIUtils;

import java.util.List;

/**
 * 订单adapter
 * Created by Administrator on 2017/11/14.
 */
public class UserOrderAdapter extends BaseQuickAdapter<UserOrderBean.DataBean, BaseViewHolder> {

    public UserOrderAdapter(List<UserOrderBean.DataBean> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserOrderBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_order_num, "订单编号：" + dataBean.getOrder_code())
                .setText(R.id.tv_order_state, CommonUtils.getOrderState(dataBean.getOrder_state()))
        .addOnClickListener(R.id.btn_cancel_order).addOnClickListener(R.id.btn_pay);
        Button btn_cancel_order = (Button) baseViewHolder.getView(R.id.btn_cancel_order);
        Button btn_pay = (Button) baseViewHolder.getView(R.id.btn_pay);
        Button btnUpdateAddress = (Button) baseViewHolder.getView(R.id.btn_update_address);
        LinearLayout ll_container = (LinearLayout) baseViewHolder.getView(R.id.ll_container_order);
        ll_container.removeAllViews();
        for (int i = 0; i < dataBean.getProduct_list().size(); i++) {
            View inflate = UIUtils.inflate(R.layout.order_item);
            ll_container.addView(inflate, i);
            initView(inflate, dataBean.getProduct_list(), i);
//            btnUpdateAddress.setVisibility(CommonUtils.CheckBtnUpdateAddressState(dataBean.getOrder_state(), dataBean.getProduct_list().get(i).getShipment_state()));
        }
        CommonUtils.checkBtnShowState(dataBean.getOrder_state(),btn_pay,btnUpdateAddress,btn_cancel_order);
    }

    /**
     * 初始化子view
     *
     * @param inflate
     * @param product_list
     * @param position
     */
    private void initView(View inflate, List<UserOrderBean.DataBean.ProductListBean> product_list, int position) {
        UserOrderBean.DataBean.ProductListBean productListBean = product_list.get(position);
        TextView tv_product_name = (TextView) inflate.findViewById(R.id.tv_product_name);
        TextView tv_product_specifications = (TextView) inflate.findViewById(R.id.tv_product_specifications);
        TextView tv_product_num = (TextView) inflate.findViewById(R.id.tv_product_num);
        TextView tv_slip_price = (TextView) inflate.findViewById(R.id.tv_slip_price);
        TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);

        tv_product_name.setText(productListBean.getProduct_name());
        tv_product_specifications.setText("规格：" + productListBean.getOption_name());
        tv_product_num.setText("数量：" + productListBean.getProduct_num());
        tv_slip_price.setText("运费：¥" + productListBean.getShipment());
        tv_price.setText("¥" + productListBean.getPrice());
        ImageLoaderManager.getImageLoader().displayImage(productListBean.getProduct_img(), imageView);
    }
}
