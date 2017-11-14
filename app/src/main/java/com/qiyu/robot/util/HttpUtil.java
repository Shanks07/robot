package com.qiyu.robot.util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by asus on 2017/11/13.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,String content, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("key", "91733eac5730494dadef4d7a00cd0201")
                .add("info", content).build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
}
