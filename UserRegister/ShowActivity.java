package edu.hzuapps.userregister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView tv_name;
    private TextView tv_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //获取Intent对象
        Intent intent = getIntent();
        //取出key对应的value值
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_password = (TextView) findViewById(R.id.tv_psw);
        tv_name.setText("用户名：" + username);
        tv_password.setText("密  码：" + password);
    }
}