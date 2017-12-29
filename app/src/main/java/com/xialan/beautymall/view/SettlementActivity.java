package com.xialan.beautymall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.bean.CommitOrderResponseBean;
import com.xialan.beautymall.bean.PrepareOrderBean;
import com.xialan.beautymall.contract.SettlementContract;
import com.xialan.beautymall.presenter.SettlementPresenter;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.order.SelectOrderAddressActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/13.
 */

public class SettlementActivity extends BaseActivity implements SettlementContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_recv_name)
    TextView tvRecvName;
    @BindView(R.id.tv_consignee_tel)
    TextView tvConsigneeTel;
    @BindView(R.id.tv_address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.card_view_training)
    RelativeLayout cardViewTraining;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.btn_cancel_order)
    Button btnCancelOrder;
    private SettlementPresenter settlementPresenter;
    private String uid;
    private String prepareId;
    private String address_id;
    private String message = "";
    private List<PrepareOrderBean.DataBean.ListBean> list;
    private PrepareOrderBean.DataBean mDatabeans;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_settle;
    }

    @Override
    protected void initData() {
//        uid = MyApplaction.getInstance().getUserInfoBean().getUid();
//        prepareId = MyApplaction.PREPARE_ID;
//        settlementPresenter.getDataFromNet(uid, prepareId);
    }

    @Override
    protected BasePresenter createPresenter() {
        settlementPresenter = new SettlementPresenter(this);
        return settlementPresenter;
    }

    @Override
    public void OnGetDataSuccessed(PrepareOrderBean.DataBean dataBean) {
        initView(dataBean);
    }

    @Override
    public void OnGetDataFailed(String err_msg) {
        if (err_msg.equals("60070")) {
            startActivity(new Intent(SettlementActivity.this, UpdateIDENActivity.class));
            finish();
            return;
        }
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onCommitSuccessed(CommitOrderResponseBean.DataBean dataBean) {
        MyApplaction.order_id=dataBean.getOrder_code();
        String name="";
        for (int i=0;i<list.size();i++){
            name=name+list.get(i).getProduct_name()+" ";
        }
        MyApplaction.order_name=name;
        MyApplaction.order_price=mDatabeans.getFTotalPrice();
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCommitFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * <include layout="@layout/layout_order_detail_2" />
     * 初始化各个组件
     *
     * @param dataBeans
     */
    private void initView(PrepareOrderBean.DataBean dataBeans) {
        llContainer.removeAllViews();
        address_id=dataBeans.getAddress_id();
        mDatabeans=dataBeans;
        tvRecvName.setText(dataBeans.getShipment_name());
        tvConsigneeTel.setText(dataBeans.getShipment_tel());
        tvAddressDetail.setText(dataBeans.getShipment_address());
        tvNeedPay.setText(dataBeans.getFTotalPrice());
        list = dataBeans.getList();
        for (int i = 0; i < list.size(); i++) {
            View inflate = UIUtils.inflate(R.layout.prepare_item);
            initViewForProduct(inflate, list.get(i), i);
            llContainer.addView(inflate, i);
        }
        //修改地址
        cardViewTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplaction.PREPARE_STATE = true;
                startActivity(new Intent(SettlementActivity.this, SelectOrderAddressActivity.class));
            }
        });
        AddressBean.DataBean addressBean = MyApplaction.getAddressBean();
        if (addressBean != null) {
            tvConsigneeTel.setText(addressBean.getAddress_user_tel());
            tvAddressDetail.setText(addressBean.getAddress_class_a() + " " + addressBean.getAddress_class_b());
            address_id = addressBean.getAddress_no();
            MyApplaction.addressbean = null;
        }
    }

    private void initViewForProduct(View inflate, final PrepareOrderBean.DataBean.ListBean productListBean, int position) {
        TextView tv_product_name = (TextView) inflate.findViewById(R.id.tv_product_name);
        TextView tv_product_specifications = (TextView) inflate.findViewById(R.id.tv_product_specifications);
        TextView tv_product_num = (TextView) inflate.findViewById(R.id.tv_product_num);
        TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);
        EditText et_message = (EditText) inflate.findViewById(R.id.et_message);
        TextView tv_taxes = (TextView) inflate.findViewById(R.id.tv_taxes);
        //税金
        tv_taxes.setText("¥" + productListBean.getTax_price());
        et_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                productListBean.setMessage(editable.toString());
            }
        });
        //运费
        TextView tv_shipment_price = (TextView) inflate.findViewById(R.id.tv_shipment_price);

        tv_product_name.setText(productListBean.getProduct_name());
        tv_product_specifications.setText("规格：" + productListBean.getProduct_option());
        tv_product_num.setText("数量：" + productListBean.getProduct_count());
        tv_shipment_price.setText("¥" + productListBean.getShipment_price());
        tv_price.setText("¥" + productListBean.getTotal_price());
        ImageLoaderManager.getImageLoader().displayImage(productListBean.getProduct_img(), imageView);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("确认订单");
        setIVReturn();
        uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        prepareId = MyApplaction.PREPARE_ID;
        settlementPresenter.getDataFromNet(uid, prepareId);
    }

    @OnClick(R.id.btn_cancel_order)
    public void onViewClicked() {
        //提交订单 提交数据到服务器 成功后跳转到支付页面
        //获取留言信息
        for (int i = 0; i < list.size(); i++) {
            message += list.get(i).getMessage();
            if (i!=list.size()-1){
                message+="#";
            }
        }
        settlementPresenter.commitDataToNet(uid, prepareId, address_id, message);
    }
}
