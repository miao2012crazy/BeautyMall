package com.xialan.beautymall.applaction;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.xialan.beautymall.bean.AddressBean;
import com.xialan.beautymall.bean.HistoryBean;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.bean.OrderDetailBean;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.bean.UserOrderBean;
import com.xialan.beautymall.utils.ImageLoaderManager;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class MyApplaction extends Application {
    public static OrderDetailBean.DataBean.ProductListBean SHIP_ID = null;
    public static  boolean PREPARE_STATE = false;
    public static String PREPARE_ID = "";
    private static Context context;
    private static Handler handler;
    private static Thread mainThread;
    private static int mainThreadId;
    private static MyApplaction app;
    public static String order_id;
    public static boolean updateAddress=false;
    public static AddressBean.DataBean addressbean=null;
    public static UserInfoBean.DataBean userInfoBean=null;
    public static HomeBean.DataBean dataBean=null;
    public static String detail_product_id="";
    public static String search_classification="";
    public static HistoryBean.DataBean history_data_bean;
    public static UserOrderBean.DataBean order_bean=null;
    public static String order_price="";
    public static String order_name="";
    public static String search_value="";
    public static boolean UpdateIden=false;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //在此方法中需要获取handler对象,上下文环境
        context = getApplicationContext();
        //准备handler对象
        handler = new Handler();
        //hanlder应用场景(子线程往主线程中发送message)

        //获取主线程的对象,WLBApplication的onCreate运行在主线程中的代码
        mainThread = Thread.currentThread();
        //获取主线程(当前线程)id方法
        mainThreadId = android.os.Process.myTid();
        mMainThreadHandler = new Handler();
        //让ImageLoader进行初始化
        ImageLoaderManager.initImageLoader(getApplicationContext());
    }

    /**
     *  获取上下文对象
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取handler对象
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread() {
        return mainThread;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

    private static Handler mMainThreadHandler = null;

    /**
     * 获取主线程handler对象
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取applaction实例
     * @return
     */
    public static MyApplaction getInstance() {
        return app;
    }

    public  UserInfoBean.DataBean getUserInfoBean(){
        if (userInfoBean==null){
            return null;
        }else{
         return userInfoBean;
        }
    }

     public static AddressBean.DataBean getAddressBean(){
        if (addressbean==null){
            return null;
        }else{
         return addressbean;
        }
    }

     public  HistoryBean.DataBean getHistoryBean(){
        if (history_data_bean==null){
            return null;
        }else{
         return history_data_bean;
        }
    }
    public  UserOrderBean.DataBean getOrderBean(){
        if (order_bean==null){
            return null;
        }else{
         return order_bean;
        }
    }
}
