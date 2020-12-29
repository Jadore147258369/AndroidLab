package edu.hzuapps.directory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    MyHelper myHelper;
    private EditText editName;
    private EditText editPhone;
    private TextView tvShow;
    private Button btnAdd;
    private Button btnQuery;
    private Button btnUpdate;
    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        init();     //初始化控件
    }
    private void init() {
        editName = (EditText)findViewById(R.id.et_name);
        editPhone = (EditText)findViewById(R.id.et_phone);
        tvShow = (TextView)findViewById(R.id.tv_show);
        btnAdd = (Button)findViewById(R.id.btn_add);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnQuery = (Button)findViewById(R.id.btn_query);
        btnUpdate = (Button)findViewById(R.id.btn_update);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String name;
        String phone;
        SQLiteDatabase db;
        ContentValues values;
        switch (v.getId()) {
            case R.id.btn_add:  //添加数据
                name = editName.getText().toString();
                phone = editPhone.getText().toString();
                db = myHelper.getWritableDatabase();    //获取可读写SQLiteDatabase对象
                values = new ContentValues();
                values.put("name", name);
                values.put("phone",phone);
                db.insert("information", null, values);
                Toast.makeText(this,"信息已添加", Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_query:    //查询数据
                db = myHelper.getReadableDatabase();
                Cursor cursor = db.query("information", null, null, null, null, null, null);
                if(cursor.getCount() == 0) {
                    tvShow.setText("");
                    Toast.makeText(this, "未找到该联系人", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    tvShow.setText("Name:"+cursor.getString(1)+"  Tel:"+cursor.getString(2));
                }
                while (cursor.moveToNext()) {
                    tvShow.append("\n"+"Name:"+cursor.getString(1)+"  Tel:"+cursor.getString(2));
                }
                cursor.close();
                db.close();
                break;
            case R.id.btn_update:   //修改数据
                db = myHelper.getWritableDatabase();
                values = new ContentValues();   //要修改的数据
                values.put("phone", phone = editPhone.getText().toString());
                db.update("information", values, "name=?", new String[]{editName.getText().toString()});    //更新并得到行数
                Toast.makeText(this, "联系人信息已修改", Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_delete:   //删除数据
                db = myHelper.getWritableDatabase();
                db.delete("information", "name=?", new String[]{editName.getText().toString()});
                Toast.makeText(this, "联系人信息已删除", Toast.LENGTH_SHORT).show();
                tvShow.setText("");
                db.close();
                break;
        }
    }
}
class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "hzuapps.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), phone VARCHAR(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}