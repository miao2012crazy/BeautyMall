package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.contract.HomeContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class HomePresenter extends BasePresenter implements HomeContract.Presenter {
    private final HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        this.mView=view;
    }

    @Override
    public void getHomeData() {
        mView.showCustomProgressBar("加载中...");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getHomeData(), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
                UIUtils.showToast("连接服务器失败!");
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                HomeBean homeBean = ParseUtils.parseData(responseBody, HomeBean.class);
                assert homeBean != null;
                boolean checkdata = ParseUtils.checkdata(homeBean.getCode());
                if (checkdata){
                    MyApplaction.dataBean=homeBean.getData();
                    mView.onGetDataSuccessed(homeBean.getData());
                }else{
                    mView.onGetDataFailed(homeBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }

        });

    }

    /**
     * 解析轮播图数据
     * @param homeBean
     */
    public  List<HomeBean.DataBean.LoopListBean>  parseLoopData(HomeBean homeBean){
        List<HomeBean.DataBean.LoopListBean> loop_list = homeBean.getData().getLoop_list();
        return loop_list;
    }
}
