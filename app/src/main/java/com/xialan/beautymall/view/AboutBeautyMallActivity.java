package com.xialan.beautymall.view;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.contract.CommonContract;
import com.xialan.beautymall.presenter.CommonPresenter;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/11.
 */

public class AboutBeautyMallActivity extends BaseActivity implements CommonContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.web_view)
    WebView webView;
    private CommonPresenter commonPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.common_webview;
    }

    @Override
    protected void initData() {
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("file:///android_asset/about_beauty_mall.html");
    }

    @Override
    protected BasePresenter createPresenter() {
        commonPresenter = new CommonPresenter(this);
        return commonPresenter;
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
        tvTitle.setText("关于购美");
        setIVReturn();
    }
}
