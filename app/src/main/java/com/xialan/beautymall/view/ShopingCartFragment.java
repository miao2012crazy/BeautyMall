package com.xialan.beautymall.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.data.ImagePickerCropParams;
import com.xialan.beautymall.R;
import com.xialan.beautymall.adapter.ShoppingAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.bean.ShoppingBean;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.ShopingCartContract;
import com.xialan.beautymall.presenter.ShopingCartPresenter;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.UIUtils;

import java.text.DecimalFormat;
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

public class ShopingCartFragment extends BaseFragment implements ShopingCartContract.View {
    @BindView(R.id.recycler_shopping_cart)
    RecyclerView recyclerShoppingCart;
    Unbinder unbinder;
    @BindView(R.id.easy_refresh_layout)
    EasyRefreshLayout easyRefreshLayout;
    @BindView(R.id.tv_summation)
    TextView tvSummation;
    @BindView(R.id.cb_check_all)
    CheckBox cbCheckAll;
    @BindView(R.id.btn_settlement)
    Button btnSettlement;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_delete)
    TextView tvDelete;
    private ShopingCartPresenter shopingCartPresenter;
    private List<ShoppingBean.DataBean> mlist;
    private ShoppingAdapter adapter;
    private int current_index = 0;
    private String uid;
    private float total_price=0.00f;

    @Override
    protected int getContentId() {
        return R.layout.activity_shopiong_cart;
    }

    @Override
    protected void loadData() {
        initRecyclerView();
    }


    /**
     * 初始化购物车列表
     */
    private void initRecyclerView() {
        mlist = new ArrayList<>();
        recyclerShoppingCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShoppingAdapter(mlist);
        View inflate = getLayoutInflater().inflate(R.layout.layout_empty, (ViewGroup) recyclerShoppingCart.getParent(), false);
        adapter.setEmptyView(inflate);
        recyclerShoppingCart.setAdapter(adapter);
        //点击事件
        recyclerShoppingCart.addOnItemTouchListener(
                new OnItemClickListener() {
                    @Override
                    public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        MyApplaction.detail_product_id = mlist.get(i).getProduct_id();
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ShoppingBean.DataBean dataBean = mlist.get(position);
                        String product_num = dataBean.getProduct_num();
                        int sum = Integer.parseInt(product_num);
                        switch (view.getId()) {
                            case R.id.btn_reduce:
                                if (sum == 1) {
                                    return;
                                } else {
                                    sum -= 1;
                                }
                                dataBean.setProduct_num(String.valueOf(sum));
                                adapter.notifyItemChanged(position);
                                break;
                            case R.id.btn_add:
                                sum += 1;
                                dataBean.setProduct_num(String.valueOf(sum));
                                adapter.notifyItemChanged(position);
                                break;
                        }
                        summation();
                    }
                    @Override
                    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                        final ShoppingBean.DataBean dataBean = (ShoppingBean.DataBean) baseQuickAdapter.getData().get(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("是否删除当前商品?")
                                .setNegativeButton("立即删除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        shopingCartPresenter.deleteproduct(uid,dataBean.getPrepare_id());
                                    }
                                })
                                .setPositiveButton("保留", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).create().show();
                    }
                }
        );

        UserInfoBean.DataBean userInfoBean = MyApplaction.getInstance().getUserInfoBean();
        if (userInfoBean == null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        } else {
            uid = MyApplaction.userInfoBean.getUid();
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        shopingCartPresenter = new ShopingCartPresenter(this);
        return shopingCartPresenter;
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
    public void onGetListDataSuccess(List<ShoppingBean.DataBean> dataBeans) {
        summation();
        if (easyRefreshLayout.isLoading()) {
            easyRefreshLayout.loadMoreComplete();
        } else if (easyRefreshLayout.isRefreshing()) {
            easyRefreshLayout.refreshComplete();
        }
        if (current_index == 0) {
            mlist.clear();
        }
        if (dataBeans.size() == 0) {
            UIUtils.showToast("没有更多数据了!");
            easyRefreshLayout.setLoadMoreModel(LoadModel.NONE);
        } else {
            mlist.addAll(dataBeans);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onGetListDataFailed(String msg) {

        UIUtils.showToast(ParseUtils.showErrMsg(msg));
        if (easyRefreshLayout.isLoading()) {
            easyRefreshLayout.loadMoreComplete();
        } else if (easyRefreshLayout.isRefreshing()) {
            easyRefreshLayout.refreshComplete();
        }
    }

    @Override
    public void onDeleteSuccessed() {
        UIUtils.showToast("已删除");
        shopingCartPresenter.getListDataFromNet(uid, current_index + "");
    }

    @Override
    public void onDeleteFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfoBean.DataBean userInfoBean = MyApplaction.getInstance().getUserInfoBean();
        if (userInfoBean == null) {
            return;
        } else {
            uid = MyApplaction.userInfoBean.getUid();
        }
        tvTitle.setText("购物车");
        shopingCartPresenter.getListDataFromNet(uid, current_index + "");
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                current_index++;
                shopingCartPresenter.getListDataFromNet(uid, current_index + "");
            }

            @Override
            public void onRefreshing() {
                current_index = 0;
                shopingCartPresenter.getListDataFromNet(uid, current_index + "");
                easyRefreshLayout.refreshComplete();
            }
        });
        initCheckListener();
        initCheckAll();

    }

    /**
     * 求总价
     */
    public void summation() {
        float sum = 0.00f;
        List<ShoppingBean.DataBean> checkData = adapter.getCheckData();
        for (ShoppingBean.DataBean item : checkData) {
            float v = Float.parseFloat(item.getProduct_price()) * Integer.parseInt(item.getProduct_num());
            sum += v;
        }
        //取两位
//        sum = (float) (Math.round(sum * 100) / 100);
        DecimalFormat df = new DecimalFormat("#.00");
        sum = Float.parseFloat(df.format(sum));
        total_price=sum;
            tvSummation.setText("¥" + sum);

    }


    private void initCheckListener() {
        Observable<String> check_change = RxBus.get().register("CHECK_CHANGE", String.class);
        check_change.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                summation();
            }
        });
    }


    @OnClick(R.id.btn_settlement)
    public void onViewClicked() {
        String prepard_id = "";
        List<ShoppingBean.DataBean> checkData = adapter.getCheckData();
        for (int i = 0; i < checkData.size(); i++) {
            String id = checkData.get(i).getPrepare_id();
            prepard_id += id;
            if (i != checkData.size() - 1) {
                prepard_id += "#";
            }
        }
        MyApplaction.PREPARE_ID = prepard_id;
        //修改数量
        if (total_price>2000){
            showDialog();
            return;
        }
        if (prepard_id.equals("")){
            UIUtils.showToast("还没有选择商品!");
            return;
        }
        //结算
        startActivity(new Intent(getActivity(), SettlementActivity.class));
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_chinaport, null);
        //初始化控件
        TextView choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
        TextView takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
        TextView tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //选择相片
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://ceb2pub.chinaport.gov.cn/limit/outIndex");
                intent.setData(content_url);
                startActivity(intent);
                dialog.dismiss();
            }
        });
//        //选择拍照
//        takePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dialog.dismiss();
//            }
//        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        Display d = dialogWindow.getWindowManager().getDefaultDisplay();
        lp.width = (int) (d.getWidth());
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }




    private void initCheckAll() {
        cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                adapter.setCheckAll(b);
                adapter.notifyDataSetChanged();
                summation();
            }
        });
    }
}
