package edu.hzuapps.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ShowActivity extends AppCompatActivity {
    private ProgressBar mProgressBar1;
    private ProgressBar mProgressBar2;
    private ProgressBar mProgressBar3;
    private TextView tv_name;
    private TextView tv_password;
    private TextView mLifeTV;
    private TextView mAttackTV;
    private TextView mSpeedTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //获取Intent对象
        Intent intent = getIntent();
        mLifeTV = (TextView) findViewById(R.id.tv_life_progress);
        mAttackTV = (TextView) findViewById(R.id.tv_attack_progress);
        mSpeedTV = (TextView) findViewById(R.id.tv_speed_progress);
        //初始化进度条
        initProgress();
        initUser();
    }
    private void initProgress() {
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        mProgressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        //设置最大值
        mProgressBar1.setMax(1000);
        mProgressBar2.setMax(1000);
        mProgressBar3.setMax(1000);
    }
    private void initUser() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_password = (TextView) findViewById(R.id.tv_password);
    }
    //开启新的Activity并获取其返回值
    public void click(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        //返回请求结果，请求码为1
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            if(requestCode == 1) {
                if(resultCode == 1) {
                    ItemInfo info = (ItemInfo) data.getSerializableExtra("Qlucky");
                    //更新ProgressBar的值
                    updateProgress(info);
                    //更新用户名
                    updateUser(info);
                }
            }
        }
    }

    private void updateUser(ItemInfo info) {
        tv_name.setText("用户名："+info.getUsername());
        tv_password.setText("密  码："+info.getPassword());
    }

    private void updateProgress(ItemInfo info) {
        int progress1 = mProgressBar1.getProgress();
        int progress2 = mProgressBar2.getProgress();
        int progress3 = mProgressBar3.getProgress();
        mProgressBar1.setProgress(progress1+info.getLife());
        mProgressBar2.setProgress(progress2+info.getAttack());
        mProgressBar3.setProgress(progress3+info.getSpeed());
        mLifeTV.setText(mProgressBar1.getProgress()+"");
        mAttackTV.setText(mProgressBar2.getProgress()+"");
        mSpeedTV.setText(mProgressBar3.getProgress()+"");
    }
}
