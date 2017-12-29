package com.xialan.beautymall.view.order;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.SelectAddressAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.contract.SelectOrderAddressContract;
import com.xialan.beautymall.presenter.SelectOrderAddressPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SelectOrderAddressActivity extends BaseActivity implements SelectOrderAddressContract.View{
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
    private SelectAddressAdapter adapter;
    private boolean isCheckboxShow = false;
    private boolean isRefresh = false;
    private String uid;
    private SelectOrderAddressPresenter addressManagePresenter;
    private int lastPositon = -1;
    private String address_id="";
    private AddressBean.DataBean dataBean;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initData() {
        mlist = new ArrayList<>();
        recyclerViewAddress.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectAddressAdapter(mlist);
        recyclerViewAddress.setAdapter(adapter);
        recyclerViewAddress.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (lastPositon != -1) {
                    AddressBean.DataBean dataBean1 = (AddressBean.DataBean) baseQuickAdapter.getData().get(lastPositon);
                    dataBean1.setIs_checked(false);
                    adapter.notifyItemChanged(lastPositon);
                }
                lastPositon = i;
                //单选
                dataBean = (AddressBean.DataBean) baseQuickAdapter.getData().get(i);
                dataBean.setIs_checked(true);
                address_id= dataBean.getAddress_no();
                adapter.notifyItemChanged(i);
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
        addressManagePresenter = new SelectOrderAddressPresenter(this);
        return addressManagePresenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText("选择地址");
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

    }

    @Override
    public void onDeleteFailed(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_address)
    public void onViewClicked() {
        if (address_id.equals("")){
            UIUtils.showToast("还没有选择收货地址!");
            return;
        }
        boolean prepareState = MyApplaction.PREPARE_STATE;
        if (prepareState){
            MyApplaction.addressbean=dataBean;
            finish();
        }else{
            String uid = MyApplaction.getInstance().getUserInfoBean().getUid();
            String order_id = MyApplaction.getInstance().getOrderBean().getOrder_code();
            //设置为收货地址
            addressManagePresenter.setRecvAddress(uid,order_id,address_id);
        }


    }


    @Override
    public void onSetRecvAddressSuccessed() {
        UIUtils.showToast("修改收货地址成功!");
        finish();
    }

    @Override
    public void onSetRecvAddressFailed(String msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(msg));
    }

}
