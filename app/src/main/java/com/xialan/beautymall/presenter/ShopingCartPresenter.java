package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.bean.ShoppingBean;
import com.xialan.beautymall.contract.ShopingCartContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class ShopingCartPresenter extends BasePresenter implements ShopingCartContract.Presenter {
    private final ShopingCartContract.View mView;

    public ShopingCartPresenter(ShopingCartContract.View view) {
        this.mView=view;
    }

    @Override
    public void getListDataFromNet(String uid,String index) {
//
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getShoppingCart(uid,index), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                ShoppingBean shoppingBean = ParseUtils.parseData(responseBody, ShoppingBean.class);
                boolean checkdata = ParseUtils.checkdata(shoppingBean.getCode());
                if (checkdata){
                    mView.onGetListDataSuccess(shoppingBean.getData());
                }else{
                    mView.onGetListDataFailed(shoppingBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }

        });
    }
    @Override
    public void deleteproduct(String uid, String prepare_id) {
        NovateUtil.getInstance().call(NovateUtil.getApiService().deleteProductFromCart(uid,prepare_id), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.onDeleteSuccessed();
                }else{
                    mView.onDeleteFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }

        });
    }
}
