package edu.hzuapps.saveqq;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveQQ {
    //保存QQ账号和登录密码到data.xml文件中
    public static boolean saveUserInfo(Context context, String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        return true;
    }
    //从data.xml文件中获取存储的QQ账号和密码
    public static Map<String, String> getUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("username", username);
        userMap.put("password", password);
        return userMap;
    }
}
