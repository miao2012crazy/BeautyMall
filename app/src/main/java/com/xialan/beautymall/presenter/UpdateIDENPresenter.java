package com.xialan.beautymall.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.UserIdenBean;
import com.xialan.beautymall.contract.UpdateIDENContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/13.
 */
public class UpdateIDENPresenter extends BasePresenter implements UpdateIDENContract.Presenter {
    private  UpdateIDENContract.View mView;

    public UpdateIDENPresenter(UpdateIDENContract.View view) {
        this.mView=view;
    }

    @Override
    public void upDataForUserIden(String uid, String real_name, String iden_no, String iden_type, File front_file, File bg_file) {
        mView.showCustomProgressBar("证件上传中...");
        List<MultipartBody.Part> paramas=new ArrayList<>();
        MultipartBody.Part no0 =MultipartBody.Part.createFormData("uid",uid);
        MultipartBody.Part no1 =MultipartBody.Part.createFormData("real_name",real_name);
        MultipartBody.Part no2 =MultipartBody.Part.createFormData("iden_no",iden_no);
        MultipartBody.Part no3 =MultipartBody.Part.createFormData("iden_type",iden_type);


        RequestBody requestBody =RequestBody.create(MediaType.parse("image/png"), front_file);
        //参数1 数组名，参数2 文件名。
        MultipartBody.Part photo1part = MultipartBody.Part.createFormData("file1", front_file.getName(), requestBody);
        RequestBody requestBody1 =RequestBody.create(MediaType.parse("image/png"), bg_file);
        //参数1 数组名，参数2 文件名。
        MultipartBody.Part photo1part1 = MultipartBody.Part.createFormData("file2", bg_file.getName(), requestBody1);

        paramas.add(no0);
        paramas.add(no1);
        paramas.add(no2);
        paramas.add(no3);
        paramas.add(photo1part);
        paramas.add(photo1part1);

        NovateUtil.getInstance().call(NovateUtil.getApiService().uploaduserid(paramas), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                assert commonBean != null;
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.upDataSuccessed();
                }else {
                    mView.upDataFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }


    @Override
    public void getUserInfo(String uid) {
        NovateUtil.getInstance().call(NovateUtil.getApiService().getUserIden(uid), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                UserIdenBean userIdenBean = ParseUtils.parseData(responseBody, UserIdenBean.class);
                assert userIdenBean != null;
                boolean checkdata = ParseUtils.checkdata(userIdenBean.getCode());
                if (checkdata){
                    mView.getUserIdenSuccessed(userIdenBean.getData());
                }else {
                    mView.getUserIdenFailed(userIdenBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });
    }
}
