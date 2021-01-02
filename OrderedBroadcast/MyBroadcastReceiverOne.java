package edu.hzuapps.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiverOne extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadcastReceiverOne", "有序广播接收者One，接收到了广播事件");
        abortBroadcast();
        Log.i("MyBroadcastReceiverOne", "广播已在此处被拦截");
    }
}
