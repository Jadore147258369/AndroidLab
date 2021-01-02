package edu.hzuapps.interceptcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.et_phone);
        //创建SharedPreferences对象来将要拦截的号码保存
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
    }
    public void click(View view) {
        //获取用户输入的拦截号码
        String phone = editText.getText().toString().trim();
        //创建Editor对象保存号码
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.commit();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
}