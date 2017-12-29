package com.xialan.beautymall.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.bean.UserOrderBean;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.order.OrderDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_order_detail)
    Button btnOrderDetail;
    @BindView(R.id.btn_return)
    Button btnReturn;
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, WXIDConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                UIUtils.showToast("支付成功!");
                finish();
                break;
            case -1:
                UIUtils.showToast("内部错误,请联系管理员!");
                finish();
                break;
            case -2:
                UIUtils.showToast("取消支付!");
                finish();
                break;
        }

    }

    @OnClick({R.id.btn_order_detail, R.id.btn_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_order_detail:
                MyApplaction.order_bean = new UserOrderBean.DataBean(MyApplaction.order_id, "", "", null);
                startActivity(new Intent(WXPayEntryActivity.this, OrderDetailActivity.class));
                finish();
                break;
            case R.id.btn_return:
                finish();
                break;
        }
    }
}