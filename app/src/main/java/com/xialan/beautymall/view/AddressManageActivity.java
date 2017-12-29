package com.xialan.beautymall.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.AddressAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.contract.AddressManageContract;
import com.xialan.beautymall.presenter.AddressManagePresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AddressManageActivity extends BaseActivity implements AddressManageContract.View {

    @BindView(R.id.recycler_view_address)
    RecyclerView recyclerViewAddress;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<AddressBean.DataBean> mlist;
    private AddressManagePresenter addressManagePresenter;
    private AddressAdapter adapter;
    private boolean isCheckboxShow = false;
    private boolean isRefresh = false;
    private String uid;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_address;
    }

    @Override
    protected void initData() {
        mlist = new ArrayList<>();
        recyclerViewAddress.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(mlist);
        recyclerViewAddress.setAdapter(adapter);

//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                adapter.openLoadMore(0);
//                addressManagePresenter.getListDataFromNet("18222703922");
//            }
//        });
        recyclerViewAddress.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AddressBean.DataBean dataBean = (AddressBean.DataBean) baseQuickAdapter.getData().get(i);
                MyApplaction.updateAddress = true;
                MyApplaction.addressbean = dataBean;
                Intent intent = new Intent(AddressManageActivity.this, AddNewAddressActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                final AddressBean.DataBean dataBean = (AddressBean.DataBean) baseQuickAdapter.getData().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressManageActivity.this);
                builder.setMessage("是否删除当前地址?")
                        .setNegativeButton("立即删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                addressManagePresenter.deleteAddress(uid,dataBean.getAddress_no());
                            }
                        })
                        .setPositiveButton("继续使用", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create().show();
            }
        });
        initRefresh();
    }

    private void initRefresh() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                addressManagePresenter.getListDataFromNet(uid);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        addressManagePresenter = new AddressManagePresenter(this);
        return addressManagePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("地址管理");
        setIVReturn();
        //刷新页面
        isRefresh = true;
        uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        if (StringUtil.isEmpty(uid)) {
        } else {
            addressManagePresenter.getListDataFromNet(uid);
        }
    }

    @Override
    public void onGetListDataSuccess(List<AddressBean.DataBean> dataBeans) {
//        adapter.hideLoadingMore();
        if (isRefresh) {
            mlist.clear();
            refresh.setRefreshing(false);
        }
        if (dataBeans.size() == 0) {
            UIUtils.showToast("暂无地址相关信息...");
        } else {
            mlist.addAll(dataBeans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetListDataFailed(String msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(msg));
    }

    @Override
    public void onDeleteSuccessed() {
        //删除成功
        UIUtils.showToast("删除成功");
        //刷新页面
        isRefresh = true;
        uid = MyApplaction.getInstance().getUserInfoBean().getUid();
        if (StringUtil.isEmpty(uid)) {
        } else {
            addressManagePresenter.getListDataFromNet(uid);
        }
    }

    @Override
    public void onDeleteFailed(String msg) {
        //删除失败
        UIUtils.showToast(ParseUtils.showErrMsg(msg));
    }

    @OnClick({R.id.tv_delete, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                //点击了编译
                if (!isCheckboxShow) {
                    tvDelete.setText("删除");
                    adapter.setCheckBoxShow(true);
                    isCheckboxShow = true;
                } else {
                    tvDelete.setText("编译");
                    adapter.setCheckBoxShow(false);
                    isCheckboxShow = false;
                    initDelete();
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_add_address:
                MyApplaction.updateAddress = false;
                //添加新地址
                Intent intent = new Intent(this, AddNewAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * rxjava 异步加载数据方法
     */
    private void initDelete() {
    }


}
