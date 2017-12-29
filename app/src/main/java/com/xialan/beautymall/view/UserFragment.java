package com.xialan.beautymall.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.beautymall.R;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.UserContract;
import com.xialan.beautymall.http.HttpUrl;
import com.xialan.beautymall.presenter.UserPresenter;
import com.xialan.beautymall.utils.ImageLoaderManager;
import com.xialan.beautymall.utils.SharePreUtils;
import com.xialan.beautymall.utils.StringUtil;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.history.HistoryActivity;
import com.xialan.beautymall.view.order.UserOrderActivity;
import com.xialan.beautymall.view.servicecenter.ServiceCenterActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class UserFragment extends BaseFragment implements UserContract.View {

    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_regedit)
    TextView tvRegedit;
    @BindView(R.id.ll_login_regedit)
    LinearLayout llLoginRegedit;
    @BindView(R.id.ll_user_info_manage)
    LinearLayout llUserInfoManage;
    @BindView(R.id.ll_user_order)
    LinearLayout llUserOrder;
    @BindView(R.id.ll_user_address)
    LinearLayout llUserAddress;
    @BindView(R.id.ll_user_history)
    LinearLayout llUserHistory;
    @BindView(R.id.ll_user_sercice)
    LinearLayout llUserSercice;
    @BindView(R.id.ll_user_about)
    LinearLayout llUserAbout;
    @BindView(R.id.ll_user_exit)
    LinearLayout llUserExit;
    @BindView(R.id.tv_user_nick_name)
    TextView tvUserNickName;
    @BindView(R.id.iv_user_sex)
    ImageView ivUserSex;
    Unbinder unbinder;

    @Override
    protected int getContentId() {
        return R.layout.activity_user;
    }

    @Override
    protected void loadData() {

    }
    @Override
    protected BasePresenter createPresenter() {
        UserPresenter userPresenter = new UserPresenter(this);
        return userPresenter;
    }

    @OnClick({R.id.iv_user_header, R.id.rl_user_info, R.id.btn_login, R.id.tv_regedit, R.id.ll_user_info_manage, R.id.ll_user_order, R.id.ll_user_address, R.id.ll_user_history, R.id.ll_user_sercice, R.id.ll_user_about, R.id.ll_user_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                if (MyApplaction.getInstance().getUserInfoBean()==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }

                //启动个人信息管理页面
                startActivity(new Intent(getActivity(),UserinfoManageActivity.class));
                break;
            case R.id.rl_user_info:
                break;
            case R.id.btn_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_regedit:
                break;
            case R.id.ll_user_info_manage:
                if (MyApplaction.getInstance().getUserInfoBean()==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.ll_user_order:
                if (MyApplaction.getInstance().getUserInfoBean()==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), UserOrderActivity.class));

                break;
            case R.id.ll_user_address:
                if (MyApplaction.getInstance().getUserInfoBean()==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                String uid = MyApplaction.getInstance().getUserInfoBean().getUid();
                if (StringUtil.isEmpty(uid)){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), AddressManageActivity.class));
                break;
            case R.id.ll_user_history:
                if (MyApplaction.getInstance().getUserInfoBean()==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(),HistoryActivity.class));
                break;
            case R.id.ll_user_sercice:
                startActivity(new Intent(getActivity(), ServiceCenterActivity.class));
                break;
            case R.id.ll_user_about:
                startActivity(new Intent(getActivity(),AboutBeautyMallActivity.class));
                break;
            case R.id.ll_user_exit:
                SharePreUtils.put(UIUtils.getContext(),"uid","");
                SharePreUtils.put(UIUtils.getContext(),"age","");
                SharePreUtils.put(UIUtils.getContext(),"name","");
                SharePreUtils.put(UIUtils.getContext(),"sex","");
                SharePreUtils.put(UIUtils.getContext(),"img","");
                SharePreUtils.put(UIUtils.getContext(),"wechat","");
                MyApplaction.userInfoBean=null;
                initLogin(null);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfoBean.DataBean userInfoBean = MyApplaction.getInstance().getUserInfoBean();
        if (userInfoBean != null) {

            initLogin(userInfoBean);
        }
    }

    /**
     * 初始化用户信息
     *
     * @param userInfoBean
     */
    private void initLogin(UserInfoBean.DataBean userInfoBean) {
        if (userInfoBean==null||userInfoBean.getUid().equals("")){
            rlUserInfo.setVisibility(View.GONE);
            llLoginRegedit.setVisibility(View.VISIBLE);
            return;
        }
        rlUserInfo.setVisibility(View.VISIBLE);
        llLoginRegedit.setVisibility(View.GONE);
        tvUserNickName.setText(userInfoBean.getUser_name());
        ImageLoaderManager.displayHeadIcon(HttpUrl.baseUrl_IB()+"IBSync/new/client_profile/"+userInfoBean.getUser_img(),ivUserHeader);
        switch (userInfoBean.getUser_sex()){
            case "0":
                ivUserSex.setImageDrawable(UIUtils.getDrawable(R.drawable.boy_user));
                break;
            case "1":
                ivUserSex.setImageDrawable(UIUtils.getDrawable(R.drawable.nv));
                break;
        }
    }
}
