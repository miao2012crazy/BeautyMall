package com.xialan.beautymall.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alipay.sdk.auth.AlipaySDK;
import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AliPay;
import com.xialan.beautymall.bean.UserOrderBean;
import com.xialan.beautymall.contract.PayContract;
import com.xialan.beautymall.presenter.PayPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.utils.WXPayUtils;
import com.xialan.beautymall.view.order.OrderDetailActivity;
import com.xialan.beautymall.view.order.UserOrderActivity;
import com.xialan.beautymall.wxapi.WXIDConstants;
import com.xialan.beautymall.wxapi.WXPayEntryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/14.
 */

public class PayActivity extends BaseActivity implements PayContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.cb_wechat)
    CheckBox cbWechat;
    @BindView(R.id.card_alipay)
    CardView cardAlipay;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;
    @BindView(R.id.card_wechat)
    CardView cardWechat;
    @BindView(R.id.btn_pay)
    Button btnPay;
    private PayPresenter payPresenter;
    private boolean cb_state = false;
    private boolean is_first=true;


    @Override
    protected int setlayoutID() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        payPresenter = new PayPresenter(this);
        return payPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.card_alipay, R.id.card_wechat, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_alipay:
                //支付宝支付
                cbAlipay.setChecked(true);
                cbWechat.setChecked(false);
                cb_state = true;
                break;
            case R.id.card_wechat:
                //微信支付
                cb_state = false;
                cbAlipay.setChecked(false);
                cbWechat.setChecked(true);
                break;
            case R.id.btn_pay:
                if (is_first){
                    showDialog();
                    is_first =false;
                    return;
                }

                if (Float.parseFloat( MyApplaction.order_price)>2000.00){
                    showDialog();
                    return;
                }
                //获取预支付id
                if (cb_state){
                    payPresenter.getOrderStrForAlipay(MyApplaction.order_id, MyApplaction.order_price, MyApplaction.order_name);
                }else{
                    payPresenter.getPrepareId(MyApplaction.order_id, MyApplaction.order_price, MyApplaction.order_name);
                }
                break;
        }
    }
    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_chinaport, null);
        //初始化控件
        TextView choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
        TextView takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
        TextView tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //选择相片
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://ceb2pub.chinaport.gov.cn/limit/outIndex");
                intent.setData(content_url);
                startActivity(intent);
                dialog.dismiss();
            }
        });
//        //选择拍照
//        takePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dialog.dismiss();
//            }
//        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        Display d = dialogWindow.getWindowManager().getDefaultDisplay();
        lp.width = (int) (d.getWidth());
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    @Override
    public void onGetPrepareIdSuccessed(String prepare_id) {
            //微信
            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
            builder.setAppId(WXIDConstants.APP_ID)
                    .setPartnerId("1489896402")
                    .setPrepayId(prepare_id)
                    .setPackageValue("Sign=WXPay")
                    .build()
                    .toWXPayAndSign(this, WXIDConstants.APP_ID, "tianjinjdhkjgomei201766688888888");
    }

    @Override
    public void onGetPrepareIdFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onGetOrderStrSuccessed(String order_info) {
        AliPay.Builder builder = new AliPay.Builder(this);
        //支付宝
        builder.setPayCallBackListener(new AliPay.Builder.PayCallBackListener() {
                    @Override
                    public void onPayCallBack(int status, String resultStatus, String progress) {
                        if (status!=9000){
                            UIUtils.showToast("未完成支付!");
                            return;
                        }
                        MyApplaction.order_bean = new UserOrderBean.DataBean(MyApplaction.order_id, "", "", null);
                        startActivity(new Intent(PayActivity.this, OrderDetailActivity.class));
                        finish();
                    }
                });
        //支付
        builder.pay2(order_info);
    }

    @Override
    public void onGetOrderStrFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("支付");
        setIVReturn();
        tvName.setText( MyApplaction.order_name);
        tvOrderPrice.setText(MyApplaction.order_price);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        is_first=true;
    }
}
