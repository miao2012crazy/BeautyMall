package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.contract.UserinfoManageContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/13.
 */

public class UserinfoManagePresenter extends BasePresenter implements UserinfoManageContract.Presenter {
    private final UserinfoManageContract.View mView;

    public UserinfoManagePresenter(UserinfoManageContract.View view) {
        this.mView=view;
    }

    @Override
    public void updateUserInfo(String uid,String user_nick_name, String user_age, String user_sex, File file) {
        if (file != null) {
            updateImage(uid, file);
        }
//        updateInfo(uid,user_nick_name,user_age,user_sex);
        NovateUtil.getInstance().call(NovateUtil.getApiService().updateUserInfo(uid, user_nick_name, user_age, user_sex), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata) {
                    mView.updateInfoSuccessed();
                } else {
                    mView.updateInfoFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }



    private void updateImage(String uid, File file) {
        List<MultipartBody.Part> paramas=new ArrayList<>();
        MultipartBody.Part no0 =MultipartBody.Part.createFormData("uid",uid);
        RequestBody requestBody =RequestBody.create(MediaType.parse("image/png"), file);
        //参数1 数组名，参数2 文件名。
        MultipartBody.Part photo1part = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        paramas.add(no0);
        paramas.add(photo1part);
        NovateUtil.getInstance().call(NovateUtil.getApiService().updateUserImg(paramas), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata) {
                    UIUtils.showToast("头像上传成功!");
                } else {
                    UIUtils.showToast(ParseUtils.showErrMsg(commonBean.getErr_msg()));
                }
                mView.hideCustomProgressBar();
            }

        });
    }
}
