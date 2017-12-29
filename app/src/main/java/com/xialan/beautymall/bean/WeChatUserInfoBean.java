package com.xialan.beautymall.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class WeChatUserInfoBean {
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

    public WeChatUserInfoBean() {
    }

    public WeChatUserInfoBean(String openid, String nickname, int sex, String language, String city, String province, String country, String headimgurl, String unionid, List<?> privilege) {
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
        this.unionid = unionid;
        this.privilege = privilege;
    }

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

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
