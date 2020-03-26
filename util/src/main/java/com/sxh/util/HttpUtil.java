package com.sxh.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Http工具类
 * @author sxh
 * @date 2020/2/19
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {}

    private static class SingletonRestTemplate {
        /**
         * 单例对象实例
         */
        static final RestTemplate INSTANCE = new RestTemplate();
    }

    private static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    /**
     * get方式获取数据
     * @param url
     * @return
     */
    public static JSONObject doGet(String url) {
        return HttpUtil.getInstance().getForObject(url, JSONObject.class, new Object[]{});
    }

    /**
     * post方式获取数据
     * @param url
     * @param data
     * @return
     */
    public static String doPost(String url, String data) {
        return HttpUtil.getInstance().postForObject(url, null, String.class, data);
    }

}
