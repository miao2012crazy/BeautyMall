package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */

public class AccessTokenBean {

    /**
     * openid : ol_8y07z_3kd0GB11_XSxW4TUVOo
     * nickname : 或许～
     * sex : 1
     * language : zh_CN
     * city :
     * province : Tianjin
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTITBaQAUJpbaGCw7lkw4kR9tOyjgL6mGfWgTcQsgVXYIhO0NfRbXRwckAHticVql64Zb7d1GyWDxdA/0
     * privilege : []
     * unionid : onwKL0sV4PRMMBP13gp9p1jHsDkw
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;
    /**
     * access_token : 3d_PRMT_trMbx6p_GnXEaYJ1tSXGFPny9tEIEeSLgUxvDAZI62_Hx2zuHTMvosE14kq-ZrROrve1kCzw18CHpw
     * expires_in : 7200
     * refresh_token : TUmBXwcSkpguPZUrFRlfkNVOpG8ILMCiLxQZoNuOWLJYyncEQYkVDRXUcilP1wuqz7X1rqGCCfQI3vgZZkCKHA
     * scope : snsapi_userinfo
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String scope;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
