package com.xialan.beautymall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xialan.beautymall.adapter.MyFragmentAdapter;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BaseActivity;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.contract.MainContract;
import com.xialan.beautymall.presenter.MainPresenter;
import com.xialan.beautymall.ui.NoPreloadViewPager;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.SharePreUtils;
import com.xialan.beautymall.utils.UIUtils;
import com.xialan.beautymall.view.UpdateAppManager;
import com.xialan.beautymall.view.UpdateIDENActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Observable;
import rx.Subscriber;

public class MainActivity extends BaseActivity implements MainContract.View, SearchView.OnQueryTextListener,EasyPermissions.PermissionCallbacks{
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_classification)
    RadioButton rbClassification;
    @BindView(R.id.rb_shoping_cart)
    RadioButton rbShopingCart;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;

    private NoPreloadViewPager viewPager;
    private MainPresenter mainPresenter;
    private RadioButton[] rb;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initLogin();
        rb = new RadioButton[]{rbHome, rbClassification, rbShopingCart, rbUser};
//        startActivity(new Intent(MainActivity.this, UpdateIDENActivity.class));
        viewPager = (NoPreloadViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
        viewPager.setNoScroll(true);
        initFrag();
    }

    /**
     * 持久化登录
     */
    private void initLogin() {
        long time = UIUtils.getTime();
        long login_time = (long) SharePreUtils.get(UIUtils.getContext(), "LOGIN_TIME", Long.parseLong("0"));
        try {
            long distanceTime = UIUtils.getDistanceTime(time, login_time);
            if (distanceTime > 15) {
                SharePreUtils.remove(UIUtils.getContext(),"uid");
                SharePreUtils.remove(UIUtils.getContext(),"age");
                SharePreUtils.remove(UIUtils.getContext(),"name");
                SharePreUtils.remove(UIUtils.getContext(),"sex");
                SharePreUtils.remove(UIUtils.getContext(),"img");
                SharePreUtils.remove(UIUtils.getContext(),"wechat");
            } else {
                SharePreUtils.put(UIUtils.getContext(), "LOGIN_TIME", UIUtils.getTime());
                String uid = (String) SharePreUtils.get(UIUtils.getContext(), "uid", "");
                String age = (String) SharePreUtils.get(UIUtils.getContext(), "age", "");
                String name = (String) SharePreUtils.get(UIUtils.getContext(), "name", "");
                String sex = (String) SharePreUtils.get(UIUtils.getContext(), "sex", "");
                String img = (String) SharePreUtils.get(UIUtils.getContext(), "img", "");
                String wechat = (String) SharePreUtils.get(UIUtils.getContext(), "wechat", "");
                MyApplaction.userInfoBean=new UserInfoBean.DataBean(uid,name,img,sex,age,wechat);
            }
        } catch (Exception ex) {

        }
    }

    private void initFrag() {
        Observable<String> search_classification = RxBus.get().register("SEARCH_CLASSIFICATION", String.class);
        search_classification.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                viewPager.setFitsSystemWindows(false);
                viewPager.setCurrentItem(Integer.parseInt(s));
                rb[Integer.parseInt(s)].setChecked(true);
            }
        });
    }


    @Override
    protected BasePresenter createPresenter() {
        mainPresenter = new MainPresenter(this);
        return mainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rb_home, R.id.rb_classification, R.id.rb_shoping_cart, R.id.rb_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                viewPager.setFitsSystemWindows(false);
                viewPager.setCurrentItem(0);

                break;
            case R.id.rb_classification:
                viewPager.setFitsSystemWindows(false);
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_shoping_cart:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_user:
                viewPager.setCurrentItem(3);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "使用内存所必须的权限,不授权将严重影响您的体验!", 0, perms);
        }
        //自动更新
        UpdateAppManager updateAppManager = new UpdateAppManager(this);
        updateAppManager.getUpdateMsg();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        UIUtils.showToast(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    /*返回键按键监听*/
    private long lastPressedTime;
    private static final int PERIOD = 2000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    Toast.makeText(getApplicationContext(), "再按一次退出应用程序", Toast.LENGTH_SHORT).show();
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
