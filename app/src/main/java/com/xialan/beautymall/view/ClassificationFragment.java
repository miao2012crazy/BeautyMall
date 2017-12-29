package com.xialan.beautymall.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.ClassificationAdapter;
import com.xialan.beautymall.adapter.PopMenuAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.PopMenuBean;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.contract.ClassificationContract;
import com.xialan.beautymall.presenter.ClassificationPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by ${苗} on 2017/11/8.
 */

public class ClassificationFragment extends BaseFragment implements ClassificationContract.View {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.recycler_classification)
    RecyclerView recyclerClassification;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    //    @BindView(R.id.searchView)
//    SearchView searchView;
    Unbinder unbinder;
    @BindView(R.id.iv_pop_menu)
    ImageView ivPopMenu;
    @BindView(R.id.easy_refresh_layout)
    EasyRefreshLayout easyRefreshLayout;
    @BindView(R.id.search_view)
    EditText searchView;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.content_frame)
    FrameLayout fram_sliding;
    @BindView(R.id.recycler_menu)
    RecyclerView recyclerViewMenu;
    private List<ProductBean.DataBean> mlist;
    private ClassificationPresenter classificationPresenter;
    private ClassificationAdapter adapter;
    private List<PopMenuBean.DataBean> dataBeansCate = new ArrayList<>();
    private PopMenuAdapter popMenuAdapter;
    private PopupWindow window;
    private int current_index;
    private String category_no = "";
    //排序状态保存
    private String sort_type = "";
    //高低顺序控制状态
    private String order_type = "";
    private String search_value = "";

    @Override
    protected int getContentId() {
        return R.layout.activity_classification;
    }

    @Override
    protected void loadData() {
        mTabLayout.addTab(mTabLayout.newTab().setText("综合"));
        mTabLayout.addTab(mTabLayout.newTab().setText("销量"));
        mTabLayout.addTab(mTabLayout.newTab().setText("价格"));
        initRecyclerView();
        initSearchView();
        initPopMenu();
        initTablayout();
        Observable<String> refresh = RxBus.get().register("REFRESH", String.class);
        refresh.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String search_classification) {
                classificationPresenter.getListDataFromNet(current_index + "", search_classification, sort_type, order_type, search_value);
            }
        });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    private void initTablayout() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        //综合排序
                        sort_type = "";
                        order_type = "";
                        break;
                    case 1:
                        //按销量  默认高到低
                        sort_type = "0";
                        order_type = "0";
                        break;
                    case 2:
                        //按价格 默认高到低
                        sort_type = "1";
                        order_type = "0";
                        break;
                    case 3:

                        break;
                }
                //刷新数据
                classificationPresenter.getListDataFromNet(current_index + "", category_no, sort_type, order_type, search_value);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initSearchView() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchView.getText().toString();
                if (query != null && !StringUtil.isEmpty(query)) {
                    current_index = 0;
                    //获取全部数据
                    classificationPresenter.getListDataFromNet(current_index + "", category_no, "", "", query);
                }
            }
        });
    }

    /**
     * 初始化recycler list
     */
    private void initRecyclerView() {
        current_index = 0;
        mlist = new ArrayList<>();
        recyclerClassification.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ClassificationAdapter(mlist);
        recyclerClassification.setAdapter(adapter);

        recyclerClassification.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getTag() != null) {
                    MyApplaction.detail_product_id = view.getTag() + "";
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    startActivity(intent);
                } else {
                    UIUtils.showToast("暂无详细数据!");
                }

            }
        });
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                current_index++;
                classificationPresenter.getListDataFromNet(current_index + "", category_no, sort_type, order_type, search_value);
            }

            @Override
            public void onRefreshing() {
                current_index = 0;
                category_no = "";
                MyApplaction.search_value = "";
                search_value = "";
                MyApplaction.order_id = "";
                category_no = "";
                classificationPresenter.getListDataFromNet(current_index + "", category_no, sort_type, order_type, search_value);
                easyRefreshLayout.refreshComplete();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        classificationPresenter = new ClassificationPresenter(this);
        return classificationPresenter;
    }


    @Override
    public void onGetListDataSuccess(List<ProductBean.DataBean> dataBeans) {
        if (easyRefreshLayout.isLoading()) {
            easyRefreshLayout.loadMoreComplete();
        } else if (easyRefreshLayout.isRefreshing()) {
            easyRefreshLayout.refreshComplete();
        }
        if (current_index == 0) {
            mlist.clear();
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
    public void onGetCategorySuccessed(List<PopMenuBean.DataBean> dataBeans) {
        dataBeansCate.clear();
        dataBeansCate.addAll(dataBeans);
        popMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCategoryFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
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

    @OnClick(R.id.iv_pop_menu)
    public void onViewClicked() {
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
//        window.showAsDropDown(ivPopMenu, 0, 0);

        drawerLayout.openDrawer(Gravity.LEFT);

    }

    private void initPopMenu() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMenu.setLayoutManager(linearLayoutManager);
        popMenuAdapter = new PopMenuAdapter(dataBeansCate);
        recyclerViewMenu.setAdapter(popMenuAdapter);
        recyclerViewMenu.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                current_index = 0;
                category_no = view.getTag() + "";
                classificationPresenter.getListDataFromNet(current_index + "", category_no, sort_type, order_type, search_value);
                drawerLayout.closeDrawers();
            }
        });
        classificationPresenter.getCategory();
    }

    @Override
    public void onResume() {
        super.onResume();
        search_value = MyApplaction.search_value;
        category_no = MyApplaction.search_classification;
        //获取全部数据
        classificationPresenter.getListDataFromNet(current_index + "", category_no, "", "", search_value);
    }


}
