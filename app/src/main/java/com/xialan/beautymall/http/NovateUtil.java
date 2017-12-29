package com.xialan.beautymall.http;

import com.tamic.novate.Novate;
import com.xialan.beautymall.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */

public class NovateUtil {

    private static Novate novate;
    private static ApiService apiService;

    public static Novate getInstance() {
        if (novate != null) {
            return novate;
        }
        //连接时间 可以忽略
        Map<String, String> headers = new HashMap<>();
        headers.put("Cache-Control","max-age=1000*60");
        novate = new Novate.Builder(UIUtils.getContext())
                .baseUrl(HttpUrl.baseUrl())
//                .addHeader(headers)
                .addCache(true)
                .connectTimeout(8)  //连接时间 可以忽略
                .addLog(true)
                .build();

        return novate;
    }

    /**
     * 获取apiservice对象
     * @return
     */
    public static ApiService getApiService(){
        if (apiService!=null){
            return apiService;
        }
        apiService = novate.create(ApiService.class);
        return apiService;
    }
}
