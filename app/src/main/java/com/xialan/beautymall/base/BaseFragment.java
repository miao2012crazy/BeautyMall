package com.xialan.beautymall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xialan.beautymall.ui.CustomProgressBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment 基类
 *
 * Created by ${苗春良}
 * on 2016/11/23.
 */
public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment implements BaseView {

    protected P mPresenter;
    private Unbinder bind;
    private CustomProgressBar customProgressBar;
    //    private CustomProgressBar customProgressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();//创建presenter
        mPresenter.attachView((V) this);
        initVariables();
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentId(), container, false);
        bind = ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        bind.unbind();
    }
    /**
     * 接收其他页面传递过来的数据 子类可直接重写
     */
    protected void initVariables() {

    }

    /**
     * 提供布局id
     * @return
     */
    protected abstract int getContentId();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 中间p层对象
     * @return
     */
    protected abstract P createPresenter();

    @Override
    public void showCustomProgressBar(String msg) {
        if (customProgressBar==null){
            customProgressBar = new CustomProgressBar(getActivity());
        }
        if (!msg.equals("")){
            customProgressBar.setMessage(msg);
        }
        customProgressBar.show();
    }

    @Override
    public void hideCustomProgressBar() {
        if (customProgressBar!=null){
            customProgressBar.dismiss();
        }
    }

}