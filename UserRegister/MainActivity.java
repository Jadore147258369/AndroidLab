package edu.hzuapps.userregister;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_psw;
    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        btn_send = (Button) findViewById(R.id.btn_send);
        //点击注册按钮进行数据传输
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDate();
            }
        });
    }
    //数据传输函数
    public void passDate() {
        //创建Intent对象启动ShowActivity
        Intent intent = new Intent(this,ShowActivity.class);
        //将数据存入Intent对象
        intent.putExtra("username",et_name.getText().toString().trim());
        intent.putExtra("password",et_psw.getText().toString().trim());
        startActivity(intent);
    }
}
