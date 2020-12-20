package edu.hzuapps.saveqq;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化界面
        initView();
        Map<String, String> userInfo = FileSaveQQ.getUserInfo(this);
        if(userInfo != null) {
            etUsername.setText(userInfo.get("username"));
            etPassword.setText(userInfo.get("password"));
        }
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.edit_username);
        etPassword = (EditText) findViewById(R.id.edit_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        //设置按钮的点击事件
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当单击登录按钮时，获取QQ账号和密码
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        //检测账号和密码是否正确
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this,"请输入QQ账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //登录成功
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //保存用户信息
        boolean isSaveSuccess = FileSaveQQ.saveUserInfo(this, username, password);
        if (isSaveSuccess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
}
