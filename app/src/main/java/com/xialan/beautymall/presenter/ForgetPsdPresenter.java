package com.xialan.beautymall.presenter;

import com.google.gson.Gson;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.base.BasePresenter;
import com.xialan.beautymall.bean.CheckSmsVerifyCodeBean;
import com.xialan.beautymall.bean.CommonBean;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.bean.SmsMsgBean;
import com.xialan.beautymall.contract.ForgetPsdContract;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.UIUtils;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ForgetPsdPresenter extends BasePresenter implements ForgetPsdContract.Presenter {
    private final ForgetPsdContract.View mView;
    private String msg_id = "";

    public ForgetPsdPresenter(ForgetPsdContract.View view) {
        this.mView = view;
    }

    @Override
    public void getVerifyCode(String mobile) {
        mView.showCustomProgressBar("获取验证码中...");
        NovateUtil.getInstance().call(NovateUtil.getApiService().getVerifyCode(mobile), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.hideCustomProgressBar();
                UIUtils.showToast("连接服务器失败!");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Gson gson = new Gson();
                try {
                    if (responseBody != null) {
                        SmsMsgBean smsMsgBean = gson.fromJson(responseBody.string(), SmsMsgBean.class);
                        msg_id = smsMsgBean.getMsg_id();
                        mView.onGetVerifyCodeSuccess();
                    }else{
                        mView.onGetVerifyCodeFailed("获取验证码失败!请联系管理员");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.onGetVerifyCodeFailed("获取验证码失败!请联系管理员");
                }
                mView.hideCustomProgressBar();
            }

        });
    }

    @Override
    public void checkVerifyCode(String verify_code) {
        if (msg_id == ""||msg_id==null) {
            UIUtils.showToast("还没有发送验证码!");
            return;
        }
        NovateUtil.getInstance().call(NovateUtil.getApiService().checkVerifyCode(msg_id, verify_code), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.OnCheckVerifyCodeFailed("验证码错误!");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    Gson gson = new Gson();
                    CheckSmsVerifyCodeBean checkSmsVerifyCodeBean = gson.fromJson(responseBody.string(), CheckSmsVerifyCodeBean.class);
                    Boolean is_valid = checkSmsVerifyCodeBean.getIs_valid();
                    if (is_valid) {
                        mView.OnCheckVerifyCodeSuccess();
                    }else{
                        if (checkSmsVerifyCodeBean.getError().getCode()==50012){
                            mView.OnCheckVerifyCodeSuccess();
                        }else{
                            mView.OnCheckVerifyCodeFailed("验证码已失效 请重新获取!");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.onGetVerifyCodeFailed("未知错误,请联系管理员");
                }
            }
        });

    }
    @Override
    public void upDatePsd(String mobile, String etpsd) {
        NovateUtil.getInstance().call(NovateUtil.getApiService().updatepsd(mobile, etpsd), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                mView.OnCheckVerifyCodeFailed("网络错误!");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                mView.showCustomProgressBar("正在加载中...");
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.onUpdatePsdSuccessed();
                }else{
                    mView.onUpdatePsdFailed(commonBean.getErr_msg());
                }
                mView.hideCustomProgressBar();
            }
        });

    }
}
