package edu.hzuapps.forhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void send(View view) {
        Intent intent = new Intent();
        //定义广播类型
        intent.setAction("Custom_broadcast");
        //发送广播
        sendBroadcast(intent);
    }
}
