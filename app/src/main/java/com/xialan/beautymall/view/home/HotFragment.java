package com.xialan.beautymall.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.HotAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.contract.HotContract;
import com.xialan.beautymall.presenter.HotPresenter;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.DetailActivity;

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

public class HotFragment extends BaseFragment implements HotContract.View {

    @BindView(R.id.recycler_hot)
    RecyclerView recyclerHot;
    Unbinder unbinder;
    private HotPresenter hotPresenter;
    private ArrayList<HomeBean.DataBean.ProductHotBean> hotBeans;
    private HotAdapter hotAdapter;

    @Override
    protected int getContentId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void loadData() {
        hotBeans = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerHot.setLayoutManager(gridLayoutManager);
        hotAdapter = new HotAdapter(hotBeans);
        recyclerHot.setAdapter(hotAdapter);
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
                hotPresenter.getHotData();
            }
        });
        recyclerHot.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyApplaction.detail_product_id=view.getTag()+"";
                startActivity(new Intent(getActivity(), DetailActivity.class));
//                switch (view.getId()){
//                    case R.id.iv_left:
//                        MyApplaction.detail_product_id=view.getTag()+"";
//                        startActivity(new Intent(getActivity(), DetailActivity.class));
//                        break;
//                    case R.id.iv_right:
//
//                        break;
//                }
            }
        });


    }

    @Override
    protected BasePresenter createPresenter() {
        hotPresenter = new HotPresenter(this);
        return hotPresenter;
    }

    @Override
    public void onHotDataSuccessed(List<HomeBean.DataBean.ProductHotBean> productHotBeans) {
        hotBeans.clear();
        hotBeans.addAll(productHotBeans);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHotDataFailed(String err_msg) {
        UIUtils.showToast(err_msg);
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
}
