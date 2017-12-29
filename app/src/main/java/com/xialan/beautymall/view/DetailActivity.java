package com.xialan.beautymall.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.DetailContract;
import com.xialan.beautymall.presenter.DetailPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/22.
 */

public class DetailActivity extends BaseActivity implements DetailContract.View {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.btn_move_shopcart)
    Button btnMoveShopcart;
    private DetailPresenter detailPresenter;
    private String product_id;
    private String product_num;
    private String uid;
    private String option_no;
    private boolean isPay=false;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_detail;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initData() {
        String detail_product_id = MyApplaction.detail_product_id;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl("http://61.181.111.115:80/IBSync/new/search_product_detail.aspx?no=" + detail_product_id);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(DetailActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        detailPresenter = new DetailPresenter(this);
        return detailPresenter;
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
        tvTitle.setText("商品详情");
        setIVReturn();


    }

    @SuppressLint("NewApi")
    @OnClick({R.id.btn_add_shop,R.id.btn_move_shopcart})
    public void onViewClicked(View view) {
        webView.evaluateJavascript("javascript:getShopID()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                // 得到商品ID
//                        UIUtils.showToast(s);
                product_id = s.trim();
            }
        });

        webView.evaluateJavascript("javascript:getShopNum()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                //得到商品数量
//                        UIUtils.showToast("商品数量"+s);
                product_num = s.trim();
            }
        });

        webView.evaluateJavascript("javascript:getShopOptionNo()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                //得到商品规格
                option_no = s;
            }
        });

        switch (view.getId()) {
            case R.id.btn_add_shop:
                addToShoppingCart(product_id, product_num);
                break;
            case R.id.btn_move_shopcart:
                RxBus.get().post("SEARCH_CLASSIFICATION","2");
                finish();
                break;

        }
    }

    /**
     * 加入购物车
     *
     * @param product_id
     * @param product_num
     */
    private void addToShoppingCart(String product_id, String product_num) {
        if (StringUtil.isEmpty(product_id) || StringUtil.isEmpty(product_num)) {
            return;
        }
        UserInfoBean.DataBean userInfoBean = MyApplaction.getInstance().getUserInfoBean();
        if (userInfoBean == null) {
            startActivity(new Intent(DetailActivity.this, LoginActivity.class));
            return;
        } else {
            uid = MyApplaction.userInfoBean.getUid();
        }
        detailPresenter.addToShoppingCart(product_id, uid, product_num, option_no);
    }

    @Override
    public void onAddSuccess() {
        UIUtils.showToast("已加入购物车");
    }

    @Override
    public void onAddFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }


}
