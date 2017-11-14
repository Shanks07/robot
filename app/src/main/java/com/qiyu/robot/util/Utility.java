package com.qiyu.robot.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by asus on 2017/11/13.
 */

public class Utility {

    public static String handleParserJson(String response) {
        String text = "";
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String code = jsonObject.getString("code");
                text = jsonObject.getString("text");

                if (code.equals("40001")) {
                    text = "参数错误";
                } else if (code.equals("40002")) {
                    text = "请求内容为空";
                } else if (code.equals("40004")) {
                    text = "当天请求次数已使用完";
                } else if (code.equals("40007")) {
                    text.equals("数据格式异常/请按规定的要求进行加密");
                } else {
                    text = jsonObject.getString("text");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

}
