package edu.hzuapps.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCity;
    private TextView tvWeather;
    private TextView tvTemp;
    private TextView tvWind;
    private TextView tvPm;
    private ImageView ivIcon;
    private Map<String, String> map;
    private List<Map<String, String>> list;
    private String temp, weather, name, pm, wind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化文本控件
        initView();
        try {
            //读取weather1.xml文件
            InputStream is = this.getResources().openRawResource(R.raw.weather1);
			/*
			  *读取weather2.json
			  *InputStream is = this.getResources().openRawResource(R.raw.weather2);
			  */
            //将每个城市的天气信息集合存到weatherInfos中
            List<WeatherInfo> weatherInfos = WeatherService.getInfosFromXML(is);
			/*
			  *读取weather2.json
			  *List<WeatherInfo> weatherInfos = WeatherService.getInfosFromJson(is);
			  */
            //循环读取weatherInfos中每一条数据
            list = new ArrayList<Map<String, String>>();
            for (WeatherInfo weatherInfo : weatherInfos) {
                map = new HashMap<String, String>();
                map.put("temp",weatherInfo.getTemp());
                map.put("weather",weatherInfo.getWeather());
                map.put("name",weatherInfo.getName());
                map.put("pm",weatherInfo.getPm());
                map.put("wind",weatherInfo.getWind());
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"解析信息失败",Toast.LENGTH_SHORT).show();
        }
        //自定义getMap方法，显示天气信息到文本控件中，默认显示深圳的天气
        getMap(1, R.drawable.sun);
    }
    private void initView() {
        tvCity = (TextView) findViewById(R.id.tv_city);
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        tvTemp = (TextView) findViewById(R.id.tv_temp);
        tvWind = (TextView) findViewById(R.id.tv_wind);
        tvPm = (TextView) findViewById(R.id.tv_pm);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        findViewById(R.id.btn_sz).setOnClickListener(this);
        findViewById(R.id.btn_sh).setOnClickListener(this);
        findViewById(R.id.btn_gz).setOnClickListener(this);
    }
    //按钮的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sz:
                getMap(0, R.drawable.cloud_sun);
                break;
            case R.id.btn_sh:
                getMap(1, R.drawable.sun);
                break;
            case R.id.btn_gz:
                getMap(2, R.drawable.cloud);
                break;
        }
    }
    //将城市天气信息展示
    private void getMap(int number, int iconNumber) {
        Map<String, String> cityMap = list.get(number);
        temp = cityMap.get("temp");
        weather = cityMap.get("weather");
        name = cityMap.get("name");
        pm = cityMap.get("pm");
        wind = cityMap.get("wind");
        tvCity.setText(name);
        tvWeather.setText(weather);
        tvWind.setText("风力："+wind);
        tvTemp.setText(""+temp);
        tvPm.setText("PM："+pm);
        ivIcon.setImageResource(iconNumber);
    }
}
