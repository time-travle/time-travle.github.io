/**
 * Project Name: blog project
 * File Name: HttpClientUtil
 * Package Name: org.joven.base.utils
 * Date: 2019/11/30 21:08
 * Copyright (c) 2019,All Rights Reserved.
 */
package org.joven.base.utils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * CreateBy Administrator
 * Date: 2019/11/30 21:08
 * Version: 1.0
 * Remark: 用来发送和接受httpClient请求
 */
public final class HttpClientUtil {
    private static HttpClientUtil httpClientUtil = null;
    private static final String ENCODING="UTF-8";
    private HttpClientUtil(){}

    /**
     * 获得实例
     * @return
     */
    public static HttpClientUtil getInstance(){
        if(null == httpClientUtil){
            httpClientUtil = new HttpClientUtil();
        }
        return httpClientUtil;
    }

    public static void  sendPostRequest(){
        // 创建一个httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


    }
}
