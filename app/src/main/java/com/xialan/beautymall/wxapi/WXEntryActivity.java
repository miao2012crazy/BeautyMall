/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */
package com.xialan.beautymall.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import com.xialan.beautymall.applaction.MyApplaction;
import com.xialan.beautymall.bean.AccessTokenBean;
import com.xialan.beautymall.bean.UserInfoBean;
import com.xialan.beautymall.bean.WeChatUserInfoBean;
import com.xialan.beautymall.http.NovateUtil;
import com.xialan.beautymall.ui.CustomProgressBar;
import com.xialan.beautymall.utils.ParseUtils;
import com.xialan.beautymall.utils.RxBus;
import com.xialan.beautymall.utils.SharePreUtils;
import com.xialan.beautymall.utils.UIUtils;

import java.io.IOException;

import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2016/8/16.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    private BaseResp mBaseResp = null;
    private String APP_ID = WXIDConstants.APP_ID;
    private String APP_SECRET = WXIDConstants.APP_SECRET;
    private AccessTokenBean accessTokenBean;
    private CustomProgressBar customProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册API
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
        customProgressBar = new CustomProgressBar(this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    //  发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        if (resp != null) {
            this.mBaseResp = resp;
        }
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.i("savedInstanceState", "发送成功ERR_OKERR_OK");
                //发送成功
                String code = ((SendAuth.Resp) resp).code;
                getAccess_token(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.i("savedInstanceState", "发送取消ERR_USER_CANCEL");
                UIUtils.showToast("授权请求已取消");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.i("savedInstanceState", "发送取消ERR_AUTH_DENIEDERR_AUTH_DENIEDERR_AUTH_DENIED");
                UIUtils.showToast("授权请求已拒绝");
                //发送被拒绝
                break;
            default:
                Log.i("savedInstanceState", "发送返回breakbreakbreak");
                //发送返回
                break;
        }
    }

    /**
     * 获取微信的个人信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {

        NovateUtil.getInstance().call(NovateUtil.getApiService().getWeChatUserInfo(access_token, openid), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                UIUtils.showToast("获取用户信息失败!请检查网络");
                finish();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    Gson gson = new Gson();
                    WeChatUserInfoBean userInfoBean = gson.fromJson(responseBody.string(), WeChatUserInfoBean.class);
                    checkUser(userInfoBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 校验用户是否为使用ib设备注册的
     *
     * @param userInfoBean
     */
    private void checkUser(WeChatUserInfoBean userInfoBean) {

        NovateUtil.getInstance().call(NovateUtil.getApiService().checkWeChatForUser(userInfoBean.getUnionid()), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                UIUtils.showToast("获取用户信息失败!请检查网络");
                finish();
            }

            @Override
            public void onNext(ResponseBody responseBody) {

                UserInfoBean userInfoBean1 = ParseUtils.parseData(responseBody, UserInfoBean.class);
                boolean checkdata = ParseUtils.checkdata(userInfoBean1.getCode());
                if (checkdata) {
                    UserInfoBean.DataBean data = userInfoBean1.getData();
                    MyApplaction.userInfoBean = data;
                    UIUtils.showToast("登录成功!");
                    SharePreUtils.put(UIUtils.getContext(), "LOGIN_TIME", UIUtils.getTime());
                    SharePreUtils.put(UIUtils.getContext(),"uid",data.getUid());
                    SharePreUtils.put(UIUtils.getContext(),"age",data.getUser_age());
                    SharePreUtils.put(UIUtils.getContext(),"name",data.getUser_name());
                    SharePreUtils.put(UIUtils.getContext(),"sex",data.getUser_sex());
                    SharePreUtils.put(UIUtils.getContext(),"img",data.getUser_img());
                    SharePreUtils.put(UIUtils.getContext(),"wechat",data.getWechat_state());
                    finish();
                } else {
                    UIUtils.showToast(ParseUtils.showErrMsg(userInfoBean1.getErr_msg()));
                }
            }
        });

    }


    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(final String code) {
        NovateUtil.getInstance().call(NovateUtil.getApiService().getAccessToken(APP_ID, APP_SECRET, code, "authorization_code"), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                UIUtils.showToast("获取token失败!请重新尝试");
                finish();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Gson gson = new Gson();
                try {
                    accessTokenBean = gson.fromJson(responseBody.string(), AccessTokenBean.class);
                    if (accessTokenBean == null) {
                        UIUtils.showToast("获取token失败!请重新尝试");
                        finish();
                        return;
                    }
                    getUserMesg(accessTokenBean.getAccess_token(), accessTokenBean.getOpenid());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



	/*	String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + APP_ID
				+ "&secret="
				+ APP_SECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		httpUtil = new HttpUtil();
		httpUtil.getJson(path, new HttpUtil.HttpCallBack() {
			@Override
			public void onSusscess(String data) {
				try{
					Gson gson = new Gson();
					accessTokenBean = gson.fromJson(data, AccessTokenBean.class);
					if (accessTokenBean==null){
						return;
					}
					getUserMesg(accessTokenBean.getAccess_token(),accessTokenBean.getOpenid());

				}catch (Exception e){
					UIUtils.showToast(WXEntryActivity.this,"获取信息失败!");
				}

			}

			@Override
			public void onError(String meg) {
				UIUtils.showToast(WXEntryActivity.this,"网络连接失败!");
			}
		});*/

    }


}