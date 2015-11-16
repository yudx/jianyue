package com.yiming.jianyue.utils;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称：jianyue
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：15/11/16 下午10:15
 * 修改人：wengyiming
 * 修改时间：15/11/16 下午10:15
 * 修改备注：
 */
public class OkHttpClientUtil {
    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient get() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(Config.OKHTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClient.setWriteTimeout(Config.OKHTTP_CLIENT_WRITE_TIMEOUT, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(Config.OKHTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS);
        }

        return okHttpClient;
    }
}
