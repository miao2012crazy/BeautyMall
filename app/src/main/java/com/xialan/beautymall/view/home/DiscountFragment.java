package com.xialan.beautymall.view.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.DiscountAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.contract.DiscountContract;
import com.xialan.beautymall.presenter.DiscountPresenter;
import com.xialan.beautymall.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/11/20.
 */

public class DiscountFragment extends BaseFragment implements DiscountContract.View {
    @BindView(R.id.recycler_discount)
    RecyclerView recyclerDiscount;
    Unbinder unbinder;
    private ArrayList<HomeBean.DataBean.ProductDiscountBean> discountBeans;
    private DiscountAdapter discountAdapter;
    private DiscountPresenter discountPresenter;

    @Override
    protected int getContentId() {
        return R.layout.fragment_discount;
    }

    @Override
    protected void loadData() {
        discountBeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerDiscount.setLayoutManager(linearLayoutManager);
        discountAdapter = new DiscountAdapter(discountBeans);
        recyclerDiscount.setAdapter(discountAdapter);
        recyclerDiscount.setHasFixedSize(true);
        recyclerDiscount.setNestedScrollingEnabled(false);
        Observable<String> getdata = RxBus.get().register("gethomedata", String.class);
        getdata.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                discountPresenter.getDiscountData();
            }
        });
        recyclerDiscount.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyApplaction.search_classification=discountBeans.get(i).getCategory_id();
                RxBus.get().post("SEARCH_CLASSIFICATION","1");
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        discountPresenter = new DiscountPresenter(this);
        return discountPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDiscountDataSuccessed(List<HomeBean.DataBean.ProductDiscountBean> discountBeanList) {
        discountBeans.clear();
        discountBeans.addAll(discountBeanList);
        discountAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDiscountDataFailed(String err_msg) {

    }
}
