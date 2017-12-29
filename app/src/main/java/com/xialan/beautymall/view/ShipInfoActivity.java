package com.xialan.beautymall.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.ShipInfoAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.OrderDetailBean;
import com.xialan.beautymall.bean.ShipInfoBean;
import com.xialan.beautymall.contract.ShipInfoContract;
import com.xialan.beautymall.presenter.ShipInfoPresenter;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ShipInfoActivity extends BaseActivity implements ShipInfoContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.recycler_ship_info)
    RecyclerView recyclerShipInfo;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_product_specifications)
    TextView tvProductSpecifications;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_slip_price)
    TextView tvSlipPrice;
    @BindView(R.id.ll_shipmet_price)
    LinearLayout ll_shipmet_price;
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.ll_tax)
    LinearLayout ll_tax;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_shipment_order)
    TextView tvShipmentOrder;
    private ShipInfoPresenter shipInfoPresenter;
    private List<ShipInfoBean.ResponsesBean.ResponseBean.ScanInfosBean.ScanInfoBean> mList = new ArrayList<>();
    private ShipInfoAdapter adapter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_ship_info;
    }

    @Override
    protected void initData() {
        OrderDetailBean.DataBean.ProductListBean productListBean = MyApplaction.SHIP_ID;
        try {
            tvShipmentOrder.setText(productListBean.getShipment_order());
            ll_tax.setVisibility(View.GONE);
            tvProductName.setText(productListBean.getProduct_name());
            tvProductSpecifications.setText("规格：" + productListBean.getOption_name());
            tvProductNum.setText("数量：" + productListBean.getProduct_num());
            ll_shipmet_price.setVisibility(View.GONE);
            tvPrice.setText("¥" + productListBean.getPrice());
            ImageLoaderManager.getImageLoader().displayImage(productListBean.getProduct_img(), imageView);
            tvMessage.setVisibility(View.GONE);
            tvOrderTime.setVisibility(View.GONE);
        } catch (Exception ex) {
        }
        recyclerShipInfo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShipInfoAdapter(mList);
        recyclerShipInfo.setAdapter(adapter);
        shipInfoPresenter.getShipInfo(productListBean.getShipment_order());
    }

    @Override
    protected BasePresenter createPresenter() {
        shipInfoPresenter = new ShipInfoPresenter(this);
        return shipInfoPresenter;
    }

    @Override
    public void OnGetShipInfoSuccessed(List<ShipInfoBean.ResponsesBean.ResponseBean.ScanInfosBean.ScanInfoBean> scanInfoBeans) {
        mList.clear();
        mList.addAll(scanInfoBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnGetShipInfoFailed(String msg) {
        UIUtils.showToast(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("物流信息");
        setIVReturn();
    }
}
