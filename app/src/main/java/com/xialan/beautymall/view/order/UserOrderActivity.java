package com.xialan.beautymall.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.UserOrderAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.bean.UserOrderBean;
import com.xialan.beautymall.contract.UserOrderContract;
import com.xialan.beautymall.presenter.UserOrderPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.PayActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/13.
 */

public class UserOrderActivity extends BaseActivity implements UserOrderContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.recycler_user_order)
    RecyclerView recyclerUserOrder;

    @BindView(R.id.easy_refresh_layout)
    EasyRefreshLayout easyRefreshLayout;
    private List<UserOrderBean.DataBean> mlist;
    private UserOrderAdapter adapter;
    private UserOrderPresenter userOrderPresenter;
    private int current_index = 0;
    private String uid;
    //查询类型
    private int item = 0;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_user_order;
    }

    @Override
    protected void initData() {
        initTabLayout();
        UserInfoBean.DataBean userInfoBean = MyApplaction.getInstance().getUserInfoBean();
        if (userInfoBean != null) {
            uid = userInfoBean.getUid();
        }
        initRecyclerView();
    }

    /**
     * 初始化列表
     */
    private void initRecyclerView() {
        mlist = new ArrayList<>();
        recyclerUserOrder.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserOrderAdapter(mlist);
        recyclerUserOrder.setAdapter(adapter);
        View inflate = getLayoutInflater().inflate(R.layout.layout_empty, (ViewGroup) recyclerUserOrder.getParent(), false);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_message_empty);
        textView.setText("暂无订单，快去下单心水的商品吧!");
        adapter.setEmptyView(inflate);



        recyclerUserOrder.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyApplaction.order_bean = mlist.get(i);
                startActivity(new Intent(UserOrderActivity.this, OrderDetailActivity.class));
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UserOrderBean.DataBean dataBean = mlist.get(position);
                switch (view.getId()) {
                    case R.id.btn_cancel_order:
                        userOrderPresenter.cancelOrder(uid, dataBean.getOrder_code());
                        break;
                    case R.id.btn_update_address:
                        MyApplaction.order_bean = mlist.get(position);
                        startActivity(new Intent(UserOrderActivity.this, SelectOrderAddressActivity.class));
                        break;
                    case R.id.btn_pay:
                        MyApplaction.order_id = dataBean.getOrder_code();
                        MyApplaction.order_price = dataBean.getTotal_price();
                        List<UserOrderBean.DataBean.ProductListBean> product_list = dataBean.getProduct_list();
                        MyApplaction.order_name += product_list.get(0).getProduct_name();
                        startActivity(new Intent(UserOrderActivity.this, PayActivity.class));
                        break;
                }
            }
        });


        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                current_index++;
                userOrderPresenter.getListDataFromNet(uid, current_index + "", item + "");
            }

            @Override
            public void onRefreshing() {
                easyRefreshLayout.setLoadMoreModel(LoadModel.COMMON_MODEL);
                current_index = 0;
                userOrderPresenter.getListDataFromNet(uid, current_index + "", item + "");
                easyRefreshLayout.refreshComplete();
            }
        });
        userOrderPresenter.getListDataFromNet(uid, current_index + "", item + "");
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("全部订单"));
        mTabLayout.addTab(mTabLayout.newTab().setText("待付款"));
        mTabLayout.addTab(mTabLayout.newTab().setText("待收货"));
        mTabLayout.addTab(mTabLayout.newTab().setText("待退货"));
        mTabLayout.addTab(mTabLayout.newTab().setText("待取消"));
        mTabLayout.addTab(mTabLayout.newTab().setText("已完成"));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                current_index = 0;
                switch (tab.getPosition()) {
                    case 0:
                        //全部订单
                        item = 0;
                        break;
                    case 1:
                        //待付款 待下单
                        item = 1;
                        break;
                    case 2:
                        //待收货 已下单
                        item = 2;
                        break;
                    case 3:
                        //待退货
                        item = 3;
                        break;
                    case 4:
                        //待取消
                        item = 4;
                        break;
                    case 5:
                        //已完成
                        item = 5;
                        break;
                }
                userOrderPresenter.getListDataFromNet(uid, current_index + "", item + "");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        userOrderPresenter = new UserOrderPresenter(this);
        return userOrderPresenter;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onGetListDataSuccess(List<UserOrderBean.DataBean> dataBeans) {
        if (easyRefreshLayout.isLoading()) {
            easyRefreshLayout.loadMoreComplete();
        } else if (easyRefreshLayout.isRefreshing()) {
            easyRefreshLayout.refreshComplete();
        }
        if (current_index == 0) {
            mlist.clear();
        }
        if (dataBeans.size() == 0) {
            UIUtils.showToast("暂无更多数据!");
            easyRefreshLayout.setLoadMoreModel(LoadModel.NONE);
        }
        mlist.addAll(dataBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetListDataFailed(String msg) {
        if (easyRefreshLayout.isLoading()) {
            easyRefreshLayout.loadMoreComplete();
        } else if (easyRefreshLayout.isRefreshing()) {
            easyRefreshLayout.refreshComplete();
        }
        UIUtils.showToast(ParseUtils.showErrMsg(msg));
    }

    @Override
    public void onCancelOrderSuccessed(String msg) {
        UIUtils.showToast(msg);
        userOrderPresenter.getListDataFromNet(uid, current_index + "", item + "");
    }

    @Override
    public void onCancelOrderFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfoBean.DataBean userInfoBean = MyApplaction.getInstance().getUserInfoBean();
        if (userInfoBean != null) {
            uid = userInfoBean.getUid();
        }
        tvTitle.setText("我的订单");
        setIVReturn();

    }
}
