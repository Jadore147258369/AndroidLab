package edu.hzuapps.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener{
    private ItemInfo itemInfo;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_shop);
        itemInfo = new ItemInfo("小A", "Jadore", "123456", 100,80,80);
        findViewById(R.id.rl).setOnClickListener(this);
        TextView mNameTV = (TextView) findViewById(R.id.tv_name);
        TextView mLifeTV = (TextView) findViewById(R.id.tv_life);
        TextView mAttackTV = (TextView) findViewById(R.id.tv_attack);
        TextView mSpeedTV = (TextView) findViewById(R.id.tv_speed);
        mNameTV.setText(itemInfo.getName()+"");
        mLifeTV.setText("生命值+"+itemInfo.getLife());
        mAttackTV.setText("攻击力+"+itemInfo.getAttack());
        mSpeedTV.setText("敏捷度+"+itemInfo.getSpeed());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl:
                Intent intent = new Intent();
                intent.putExtra("Qlucky",itemInfo);
                setResult(1,intent);
                finish();
            break;
        }
    }
}
