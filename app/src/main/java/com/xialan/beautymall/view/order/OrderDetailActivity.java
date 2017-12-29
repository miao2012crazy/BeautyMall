package com.xialan.beautymall.view.order;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.ShipAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.OrderDetailBean;
import com.xialan.beautymall.contract.OrderDetailContract;
import com.xialan.beautymall.presenter.OrderDetailPresenter;
import com.xialan.beautymall.utils.CommonUtils;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.PayActivity;
import com.xialan.beautymall.view.ShipInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/7.
 */

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_order_state)
    TextView tvOrderState;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_consignee_tel)
    TextView tvConsigneeTel;
    @BindView(R.id.tv_address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.card_view_training)
    RelativeLayout cardViewTraining;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.btn_after_sale_service)
    Button btnAfterSaleService;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.btn_update_address)
    Button btnUpdateAddress;
    @BindView(R.id.btn_cancel_order)
    Button btnCancelOrder;
    @BindView(R.id.btn_order_ship)
    Button btnOrderShip;
    @BindView(R.id.btn_return_goods)
    Button btnReturnGoods;
    @BindView(R.id.tv_recv_name)
    TextView tvRecvName;
    @BindView(R.id.tv_pay_state)
    TextView tvPayState;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    private OrderDetailPresenter orderDetailPresenter;
    private String order_code;
    private String uid;
    private OrderDetailBean.DataBean mDataBeans;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initData() {

    }

    /**
     * <include layout="@layout/layout_order_detail_2" />
     * 初始化各个组件
     *
     * @param dataBeans
     */
    private void initView(OrderDetailBean.DataBean dataBeans) {

        tvOrderNo.setText("订单编号：" + dataBeans.getOrder_code());
        tvOrderState.setText(CommonUtils.getOrderState(dataBeans.getOrder_state()));
        checkBtnShowState(dataBeans.getOrder_state());
        llContainer.removeAllViews();
        for (int i = 0; i < dataBeans.getProduct_list().size(); i++) {
            View inflate = UIUtils.inflate(R.layout.layout_order_detail_2);
            initViewForProduct(inflate, dataBeans.getProduct_list(), i);
            llContainer.addView(inflate, i);
            tvRecvName.setText(dataBeans.getProduct_list().get(0).getShipment_name());
            tvAddressDetail.setText(dataBeans.getProduct_list().get(0).getShipment_address());
            tvConsigneeTel.setText(dataBeans.getProduct_list().get(0).getShipment_tel());
        }
        tvNeedPay.setText("¥" + dataBeans.getTotal_price());
        tvPayState.setText(setPayState(dataBeans.getPay_state()));
        //修改地址
        cardViewTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplaction.PREPARE_STATE = false;
                startActivity(new Intent(OrderDetailActivity.this, SelectOrderAddressActivity.class));
            }
        });


    }

    /**
     * 按钮显示状态
     *
     * @param order_state
     */
    private void checkBtnShowState(String order_state) {
        switch (order_state) {
            case "0":
                break;
            case "1":
                btnPay.setVisibility(View.GONE);
                break;
            case "2":
                btnOrderShip.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnUpdateAddress.setVisibility(View.GONE);
                btnCancelOrder.setVisibility(View.GONE);
                break;
            case "3":
                btnOrderShip.setVisibility(View.VISIBLE);
                btnAfterSaleService.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnUpdateAddress.setVisibility(View.GONE);
                btnCancelOrder.setVisibility(View.GONE);
                btnReturnGoods.setVisibility(View.VISIBLE);
                break;
            case "4":
            case "5":
            case "6":
            case "7":
                btnOrderShip.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnUpdateAddress.setVisibility(View.GONE);
                btnCancelOrder.setVisibility(View.GONE);
            default:
                break;
        }

    }

    /**
     * 设置支付状态
     *
     * @param pay_state
     */
    private String setPayState(String pay_state) {
        switch (pay_state) {
            case "0":
                return "待支付";
            case "1":
                return "已支付";
            case "2":
                return "待改款";
            case "3":
                return "已改款";
            case "4":
                return "待退款";
            case "5":
                return "已退款";
            default:
                return "";
        }
    }

    private void initViewForProduct(View inflate, List<OrderDetailBean.DataBean.ProductListBean> product_list, int position) {
        OrderDetailBean.DataBean.ProductListBean productListBean = product_list.get(position);
        TextView tv_product_name = (TextView) inflate.findViewById(R.id.tv_product_name);
        TextView tv_product_specifications = (TextView) inflate.findViewById(R.id.tv_product_specifications);
        TextView tv_product_num = (TextView) inflate.findViewById(R.id.tv_product_num);
        TextView tv_slip_price = (TextView) inflate.findViewById(R.id.tv_slip_price);
        TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);
        TextView tv_message = (TextView) inflate.findViewById(R.id.tv_message);
        TextView tv_order_time = (TextView) inflate.findViewById(R.id.tv_order_time);
        TextView tv_tax = (TextView) inflate.findViewById(R.id.tv_tax);
        tv_tax.setText("¥" + productListBean.getTax_price());
        tv_product_name.setText(productListBean.getProduct_name());
        tv_product_specifications.setText("规格：" + productListBean.getOption_name());
        tv_product_num.setText("数量：" + productListBean.getProduct_num());
        tv_slip_price.setText("¥" + productListBean.getShipment());
        tv_price.setText("¥" + productListBean.getPrice());
        Log.e("miaomiao", productListBean.getProduct_img() + "");
        ImageLoaderManager.getImageLoader().displayImage(productListBean.getProduct_img(), imageView);
        tv_message.setText("留言：" + productListBean.getDescribe_msg());
        tv_order_time.setText("下单时间：" + productListBean.getOrder_time());

    }

    @Override
    protected BasePresenter createPresenter() {
        orderDetailPresenter = new OrderDetailPresenter(this);
        return orderDetailPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_after_sale_service, R.id.btn_pay, R.id.btn_update_address, R.id.btn_cancel_order, R.id.btn_order_ship, R.id.btn_return_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_after_sale_service:
                break;
            case R.id.btn_pay:
                MyApplaction.order_id = order_code;
                MyApplaction.order_price = mDataBeans.getTotal_price();
                MyApplaction.order_name += mDataBeans.getProduct_list().get(0).getProduct_name();
                startActivity(new Intent(OrderDetailActivity.this, PayActivity.class));
                break;
            case R.id.btn_update_address:
                MyApplaction.PREPARE_STATE = false;
                startActivity(new Intent(OrderDetailActivity.this, SelectOrderAddressActivity.class));
                break;
            case R.id.btn_cancel_order:
                String uid = MyApplaction.getInstance().getUserInfoBean().getUid();
                orderDetailPresenter.cancelOrder(uid, order_code);
                break;
            case R.id.btn_order_ship:
                showDialog(mDataBeans.getProduct_list());
                break;
            case R.id.btn_return_goods:
                //退换货申请页面
                break;
        }
    }

    public void showDialog(List<OrderDetailBean.DataBean.ProductListBean> product_list) {
        final Dialog dialog = new Dialog(this);
        View inflate = UIUtils.inflate(R.layout.dialog_ship);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_ship);
        TextView tvCancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShipAdapter adapter = new ShipAdapter(product_list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                OrderDetailBean.DataBean.ProductListBean productListBean = mDataBeans.getProduct_list().get(i);
                MyApplaction.SHIP_ID = productListBean;
                startActivity(new Intent(OrderDetailActivity.this, ShipInfoActivity.class));
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();//显示对话框
        Window window = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.7); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 0.99); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
    }

    @Override
    public void onCancelOrderSuccessed(String msg) {
        UIUtils.showToast(msg);
        finish();
    }

    @Override
    public void onCancelOrderFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void getOrderDetailSuccess(OrderDetailBean.DataBean dataBeans) {
        mDataBeans = dataBeans;
        initView(dataBeans);
    }

    @Override
    public void getOrderDetailFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("订单详情");
        setIVReturn();
        uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        order_code = MyApplaction.getInstance().getOrderBean().getOrder_code();
        orderDetailPresenter.getDetailForOrder(uid, order_code);
    }
}
