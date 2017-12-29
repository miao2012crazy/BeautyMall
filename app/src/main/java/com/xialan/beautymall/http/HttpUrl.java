package com.xialan.beautymall.http;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpUrl {
    /**
     * 电脑本地调试 true
     * 线上 false
     */
    private static boolean isDebug=false;
    public static String root_url=getRootUrl();
    /**
     * 返回baseurl
     * @return 返回baseurl
     */
    public static String baseUrl_IB() {
        return getRootUrl_ib();
    }

    /**
     * 返回baseurl
     * @return 返回baseurl
     */
    public static String baseUrl() {
        return getRootUrl_ib();
    }

    private static String getRootUrl_ib() {
        if (isDebug) {
            return "http://beauty.dawoonad.com/";
        } else {
            return "http://61.181.111.115:80/";
        }
    }

    private static String getRootUrl() {
        if (isDebug){
            return "http://192.168.1.140/home";
        }else{
            return "http://61.181.111.115";
        }
    }

    /**
     * http://192.168.1.148:80/
     * http://beauty.dawoonad.com/
     * 设置get请求体 和url
     *
     * @param paramas get请求拼接
     * @return
     */
    public static String setGetUrl(String paramas) {
        return getRootUrl() + paramas;
    }

}
