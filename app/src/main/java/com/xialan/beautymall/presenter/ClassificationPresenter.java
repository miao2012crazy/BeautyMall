package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.PopMenuBean;
import com.xialan.beautymall.bean.ProductBean;
import com.xialan.beautymall.contract.ClassificationContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import okhttp3.ResponseBody;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class ClassificationPresenter extends BasePresenter implements ClassificationContract.Presenter {

    private final ClassificationContract.View mView;

    public ClassificationPresenter(ClassificationContract.View view) {
        this.mView = view;
    }

    @Override
    public void getListDataFromNet(String index,String category,String sort_type,String order_type,String search_value) {
        mView.showCustomProgressBar("");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getClassificationData(index,category,sort_type,order_type,search_value), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                ProductBean productBean = ParseUtils.parseData(responseBody, ProductBean.class);
                assert productBean != null;
                boolean checkdata = ParseUtils.checkdata(productBean.getCode());
                if (checkdata){
                    mView.onGetListDataSuccess(productBean.getData());
                }else{
                    mView.onGetListDataFailed(productBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }

        });
    }


    @Override
    public void getCategory() {
        NovateUtil.getInstance().call(NovateUtil.getApiService().getCategory(), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                PopMenuBean popMenuBean = ParseUtils.parseData(responseBody, PopMenuBean.class);
                assert popMenuBean != null;
                boolean checkdata = ParseUtils.checkdata(popMenuBean.getCode());
                if (checkdata) {
                    mView.onGetCategorySuccessed(popMenuBean.getData());
                } else {
                    mView.onGetCategoryFailed(ParseUtils.showErrMsg(popMenuBean.getCode()));
                }
            }
        });
    }
}
