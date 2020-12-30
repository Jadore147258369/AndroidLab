package edu.hzuapps.applicationmarket;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    //需要适配的数据
    private String[] names = {"京东商城", "QQ", "QQ斗地主", "新浪微博", "天猫", "UC浏览器", "微信"};
    //图片集合
    private int[] icons = {R.drawable.jd, R.drawable.qq, R.drawable.qqddz, R.drawable.xl, R.drawable.tm, R.drawable.uc, R.drawable.wx};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ListView控件
        listView = (ListView) findViewById(R.id.lv);
        //创建一个Adapter实例
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        //设置Adapter
        listView.setAdapter(myBaseAdapter);
    }
    class MyBaseAdapter extends BaseAdapter {
        //得到item的总数
        @Override
        public int getCount() {
            //返回ListView Item条目的总数
            return names.length;
        }
        //得到Item代表的对象
        @Override
        public Object getItem(int position) {
            //返回ListView Item代表的对象
            return names[position];
        }
        //得到Item的id
        @Override
        public long getItemId(int position) {
            return position;
        }
        //得到Item的View视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*//将list_item.xml文件找出来并转换成View对象
            View view = View.inflate(MainActivity.this, R.layout.list_item, null);
            TextView textView = (TextView) view.findViewById(R.id.tv);
            textView.setText(names[position]);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setBackgroundResource(icons[position]);
            return view;*/
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textView.setText(names[position]);
                viewHolder.imageView.setBackgroundResource(icons[position]);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(names[position]);
            viewHolder.imageView.setBackgroundResource(icons[position]);
            return convertView;
        }
        class ViewHolder {
            TextView textView;
            ImageView imageView;
        }
    }
}