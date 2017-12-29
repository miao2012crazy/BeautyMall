package com.xialan.beautymall.http;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/1.
 * 请求网络的API接口类
 */
public interface ApiService {

    /**
     * 登陆
     *
     * @param user_tel
     * @param user_pwd
     * @return
     */
    @GET("http://61.181.111.115:80/IBSync/search_login.aspx")
    Observable<ResponseBody> login(@Query("id") String user_tel, @Query("password") String user_pwd);

    /**
     * 微信登录登陆
     *
     * @return
     */
    @GET("APPSync/search_login_app.aspx")
    Observable<ResponseBody> wechat_login(@Query("open_id") String openid);

    /**
     * 获取验证码
     *
     * @param user_number
     * @return
     */
    @GET("http://61.181.111.115:80/IBSync/sms/SMSJoinAuth.aspx")
    Observable<ResponseBody> getVerifyCode(@Query("mobile") String user_number);

    /**
     * 校验验证码
     *
     * @param msgid
     * @param verify_code
     * @return
     */
    @GET("http://61.181.111.115:80/IBSync/sms/SMSCheckValid.aspx")
    Observable<ResponseBody> checkVerifyCode(@Query("msgid") String msgid, @Query("code") String verify_code);

    /**
     * 上传用户注册信息
     *
     * @param smscode
     * @param tel
     * @param psd
     * @param nick_name
     * @param age
     * @param sex
     * @param recommender
     * @param pic
     * @return
     */
    @FormUrlEncoded
    @POST("/APPSync/join_member_app.aspx")
    Observable<ResponseBody> upLoadUserRegeditData(@Field("smscode") String smscode, @Field("mobile") String tel, @Field("password") String psd, @Field("nickname") String nick_name, @Field("age") String age, @Field("gender") String sex, @Field("recommender") String recommender, @Field("picture") String pic);

    /**
     * 上传用户注册信息
     *
     * @param smscode
     * @param tel
     * @param psd
     * @param nick_name
     * @param age
     * @param sex
     * @return
     */
    @FormUrlEncoded
    @POST("/APPSync/join_member_app.aspx")
    Observable<ResponseBody> upLoadUserWechatRegeditData(@Field("smscode") String smscode, @Field("mobile") String tel, @Field("password") String psd, @Field("nickname") String nick_name, @Field("age") String age, @Field("gender") String sex, @Field("recommender") String recommender, @Field("wechat_openid") String open_id);


    /**
     * 用户认证wechat
     *
     * @param wechat_id union_id
     * @param uid       用户id
     * @return
     */
    @FormUrlEncoded
    @POST("http://61.181.111.115:80/IBSync/update_wechat.aspx")
    Observable<ResponseBody> getAuthentication(@Field("union_id") String wechat_id, @Field("id") String uid);

    /**
     * 支付结果上传
     */
    @FormUrlEncoded
    @GET("/APPSync/zhifu_success.aspx")
    Observable<ResponseBody> commitPayResult(@Query("code") String order_id);

    /**
     * @param index       页索引
     * @param category_id 分类id
     * @param sort_type   排序方式 0按销量  1按价格
     * @param order_type  高低顺序 0由高到低  1由低到高
     * @return
     */
    @FormUrlEncoded
    @POST("/IBSync/new/search_product_app.ashx")
    Observable<ResponseBody> getClassificationData(@Field("index") String index, @Field("category") String category_id, @Field("sort_type") String sort_type, @Field("order_type") String order_type,@Field("search_value")String search_value);

    @FormUrlEncoded
    @POST("/IBSync/new/search_login_new.ashx")
    Observable<ResponseBody> getLoginData(@Field("id") String user_tel, @Field("password") String user_psd);

    /**
     * 首页轮播图数据
     *
     * @return
     */
    @GET("/IBSync/new/search_image_loop.ashx")
    Observable<ResponseBody> getADData();

    /**
     * 首页商品数据
     *
     * @return
     */
    @GET("/IBSync/new/search_home_product.ashx")
    Observable<ResponseBody> getHomeData();

    @POST("/IBSync/new/search_menu.ashx")
    Observable<ResponseBody> getCategory();

    /**
     * 加入购物车
     *
     * @param product_id  商品id
     * @param uid         用户id
     * @param product_num 商品数量
     * @return
     */
    @FormUrlEncoded
    @POST("/IBSync/new/add_to_shoppingcart.ashx")
    Observable<ResponseBody> addShoppingCart(@Field("product_id") String product_id, @Field("uid") String uid, @Field("product_num") String product_num, @Field("option_no") String option_no);

    @FormUrlEncoded
    @POST("/IBSync/new/search_user_shopcart.ashx")
    Observable<ResponseBody> getShoppingCart(@Field("uid") String uid, @Field("index") String index);

    @FormUrlEncoded
    @POST("/IBSync/new/search_address_manager.ashx")
    Observable<ResponseBody> getUserAddress(@Field("uid") String uid);

    @FormUrlEncoded
    @POST("/IBSync/new/update_address.ashx")
    Observable<ResponseBody> upDataForAddress(@Field("item") String item, @Field("uid") String uid, @Field("address_id") String address_id, @Field("recv_title") String recv_title, @Field("address_user_tel") String address_user_tel, @Field("address_class_a") String address_class_a, @Field("address_class_b") String address_class_b, @Field("recv_name") String recv_name, @Field("is_default") String is_default);

    @FormUrlEncoded
    @POST("/IBSync/new/update_address.ashx")
    Observable<ResponseBody> deleteAddress(@Field("item") String item, @Field("uid") String uid, @Field("address_id") String address_id);

    @FormUrlEncoded
    @POST("/IBSync/new/update_user_psd.ashx")
    Observable<ResponseBody> updateUserPsd(@Field("uid") String uid, @Field("old_psd") String old_psd, @Field("new_psd") String new_psd);

    @FormUrlEncoded
    @POST("/IBSync/new/search_history_for_skulp.ashx")
    Observable<ResponseBody> getHistoryData(@Field("uid") String uid, @Field("item") String item_type, @Field("index") String index);

    @FormUrlEncoded
    @POST("/IBSync/new/search_user_order.ashx")
    Observable<ResponseBody> getUserOrderData(@Field("uid") String uid, @Field("index") String index, @Field("item") String item);

    @FormUrlEncoded
    @POST("/IBSync/new/cancel_order.ashx")
    Observable<ResponseBody> cancelOrder(@Field("uid") String uid, @Field("order_code") String order_code);

    @FormUrlEncoded
    @POST("/IBSync/new/update_order_address.ashx")
    Observable<ResponseBody> updateOrderAddress(@Field("uid") String uid, @Field("order_code") String order_id, @Field("address_id") String address_id);

    @FormUrlEncoded
    @POST("/IBSync/new/search_order_detail.ashx")
    Observable<ResponseBody> getDetailForOrder(@Field("uid") String uid, @Field("order_code") String order_code);

    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Observable<ResponseBody> getAccessToken(@Query("appid") String app_id, @Query("secret") String app_secret, @Query("code") String code, @Query("grant_type") String grant_type);

    @GET("https://api.weixin.qq.com/sns/userinfo")
    Observable<ResponseBody> getWeChatUserInfo(@Query("access_token")String access_token,@Query("openid") String openid);

    @FormUrlEncoded
    @POST("/IBSync/new/check_wechat_user.ashx")
    Observable<ResponseBody> checkWeChatForUser(@Field("unionid")String unionid);
    @FormUrlEncoded
    @POST("/IBSync/new/prepare_to_order.ashx")
    Observable<ResponseBody> getPrepareDetail(@Field("uid")String uid,@Field("cart_list")String cart_list);

    @FormUrlEncoded
    @POST("/IBSync/new/commit_order.ashx")
    Observable<ResponseBody> CommitOrderDataToNet(@Field("uid")String uid, @Field("cart_list")String prepare_id, @Field("address_id")String address_id, @Field("message")String message);
    @FormUrlEncoded
    @POST("/IBSync/new/get_prepare_id.ashx")
    Observable<ResponseBody> getPrepareID(@Field("order_code")String order_code, @Field("fee")String fee, @Field("product_name")String product_name);

    @Multipart
    @POST("/IBSync/new/update_user_iden.ashx")
    Observable<ResponseBody> uploaduserid( @Part() List<MultipartBody.Part > files);

    @FormUrlEncoded
    @POST("/common/alipay.ashx")
    Observable<ResponseBody> getOrderStrForAliPay(@Field("order_code")String order_code, @Field("fee")String fee, @Field("product_name")String product_name);
    @FormUrlEncoded
    @POST("/IBSync/new/update_user_info.ashx")
    Observable<ResponseBody> updateUserInfo(@Field("uid") String uid, @Field("user_name") String user_nick_name, @Field("user_age")String user_age,@Field("user_sex") String user_sex);

    @Multipart
    @POST("/IBSync/new/update_head_img.ashx")
    Observable<ResponseBody> updateUserImg(@Part()List<MultipartBody.Part> paramas);
    @FormUrlEncoded
    @POST("/IBSync/new/search_user_iden.ashx")
    Observable<ResponseBody> getUserIden(@Field("uid")String uid);


    @FormUrlEncoded
    @POST("/IBSync/new/forget_user_psd.ashx")
    Observable<ResponseBody> updatepsd(@Field("uid") String mobile,@Field("new_psd") String etpsd);
    @FormUrlEncoded
    @POST("/IBSync/new/delete_product_for_cart.ashx")
    Observable<ResponseBody> deleteProductFromCart(@Field("uid")String uid,@Field("prepare_id") String prepare_id);

    @FormUrlEncoded
    @POST("/IBSync/new/search_shipment_info.ashx")
    Observable<ResponseBody> getGoodShipInfo(@Field("shipment_order") String ship_order);
}
