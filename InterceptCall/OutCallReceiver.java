package edu.hzuapps.interceptcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取当前电话号码
        String outcalphone = getResultData();
        //创建SharedPreferences对象获取拦截号码
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "");
        if (outcalphone.equals(phone)) {
            //拦截电话
            setResultData(null);
        }
    }
}
