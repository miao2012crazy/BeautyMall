package com.xialan.beautymall.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.RollAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.contract.HomeContract;
import com.xialan.beautymall.presenter.HomePresenter;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${苗} on 2017/11/8.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    Unbinder unbinder;

    @BindView(R.id.roll_view_pager)
    RollPagerView rollViewPager;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.btn_search)
    Button btn_search;

    private HomePresenter homePresenter;
    private RollAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_home;
    }

    @Override
    protected void loadData() {

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = et_search.getText().toString();
                if (query!=null&&! StringUtil.isEmpty(query)){
                    MyApplaction.search_value = query;
                    RxBus.get().post("SEARCH_CLASSIFICATION", "1");
                }
            }
        });
    }


    @Override
    protected BasePresenter createPresenter() {
        homePresenter = new HomePresenter(this);
        return homePresenter;
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

    /**
     * 初始化轮播图
     *
     * @param loop_list
     */
    private void initloop(final List<HomeBean.DataBean.LoopListBean> loop_list) {
        //设置播放时间间隔
        rollViewPager.setPlayDelay(3000);
        //设置透明度
        rollViewPager.setAnimationDurtion(500);
        adapter = new RollAdapter(loop_list);
        rollViewPager.setAdapter(adapter);
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
//        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));
        rollViewPager.setHintView(new TextHintView(getContext()));
        //mRollViewPager.setHintView(null);
        rollViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Integer integer = mlist.get(position);
                HomeBean.DataBean.LoopListBean loopListBean = loop_list.get(position);
                MyApplaction.detail_product_id = loopListBean.getProduct_id();
                startActivity(new Intent(getActivity(), DetailActivity.class));
            }
        });
    }

    @Override
    public void onGetDataSuccessed(HomeBean.DataBean dataBean) {
        RxBus.get().post("gethomedata", "");
        initloop(dataBean.getLoop_list());
    }


    @Override
    public void onGetDataFailed(String err_msg) {
        //获取首页数据失败

    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.getHomeData();

    }
}
