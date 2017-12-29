package com.xialan.beautymall.view.history;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.HistoryAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HistoryBean;
import com.xialan.beautymall.contract.SkinContract;
import com.xialan.beautymall.presenter.SkinPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/6.
 */

public  class SkinActivity extends BaseActivity implements SkinContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    @BindView(R.id.recycler_classification)
    RecyclerView recyclerClassification;
    @BindView(R.id.easy_refresh_layout)
    EasyRefreshLayout easyRefreshLayout;
    private int current_index = 0;
    private ArrayList<HistoryBean.DataBean> mlist;
    private HistoryAdapter adapter;
    private String uid;
    private SkinPresenter skinPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_skin;
    }

    @Override
    protected void initData() {
        try {
            uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        }catch (Exception e){
            uid="";
        }
        if (StringUtil.isEmpty(uid)) {
            startActivity(new Intent(SkinActivity.this, LoginActivity.class));
        }
        initRecyclerView();
    }
    @Override
    protected BasePresenter createPresenter() {
        skinPresenter = new SkinPresenter(this);
        return skinPresenter;
    }
    /**
     * 初始化recycler list
     */
    private void initRecyclerView() {
        current_index = 0;
        mlist = new ArrayList<>();
        recyclerClassification.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(mlist);
        recyclerClassification.setAdapter(adapter);
        //获取全部数据
        skinPresenter.getListDataFromNet(uid, "1", current_index + "");
        recyclerClassification.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyApplaction.history_data_bean=mlist.get(i);
                Intent intent = new Intent(SkinActivity.this, HistoryDetailActivity.class);
                startActivity(intent);
            }
        });
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                current_index++;
                skinPresenter.getListDataFromNet(uid, "1", current_index + "");
            }

            @Override
            public void onRefreshing() {
                current_index = 0;
                skinPresenter.getListDataFromNet(uid, "1", current_index + "");
                easyRefreshLayout.refreshComplete();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        }catch (Exception e){
            uid="";
        }
        tvTitle.setText("皮肤详细记录");
        setIVReturn();
    }

    @Override
    public void getDataSuccessed(List<HistoryBean.DataBean> dataBeans) {
        if (easyRefreshLayout.isLoading()){
            easyRefreshLayout.loadMoreComplete();
        }else if (easyRefreshLayout.isRefreshing()){
            easyRefreshLayout.refreshComplete();
        }
        if (current_index==0){
            mlist.clear();
        }
        mlist.addAll(dataBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getDataFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }
}
