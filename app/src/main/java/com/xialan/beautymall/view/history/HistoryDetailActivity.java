package com.xialan.beautymall.view.history;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HistoryBean;
import com.xialan.beautymall.contract.HistoryDetailContract;
import com.xialan.beautymall.presenter.HistoryDetailPresenter;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 历史记录详情页
 * Created by Administrator on 2017/12/6.
 */
public class HistoryDetailActivity extends BaseActivity implements HistoryDetailContract.View {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_history_title)
    TextView tvHistoryTitle;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;


    private HistoryDetailPresenter historyDetailPresenter;
    private HistoryBean.DataBean mHistoryBean = null;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void initData() {
        MyApplaction instance = MyApplaction.getInstance();
        HistoryBean.DataBean historyBean = instance.getHistoryBean();
        if (historyBean != null) {
            mHistoryBean = historyBean;
            initDescribe();
        } else {
            return;
        }

    }

    private void initDescribe() {
        ImageLoaderManager.getImageLoader().displayImage(mHistoryBean.getImage_path(), ivImage);
        tvHistoryTitle.setText(mHistoryBean.getTitle());
        tvDescribe.setText(mHistoryBean.getDescribe());
        tvComment.setText(mHistoryBean.getComment());
    }


    @Override
    protected BasePresenter createPresenter() {
        historyDetailPresenter = new HistoryDetailPresenter(this);
        return historyDetailPresenter;
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
        if (mHistoryBean != null) {
            tvTitle.setText("详细记录");
            setIVReturn();
        }
    }
}
