package edu.hzuapps.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText path;
    private Intent intent;
    private myConn conn;
    MusicService.MyBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path = (EditText) findViewById(R.id.et_inputpath);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_stop).setOnClickListener(this);
        conn = new myConn();
        intent = new Intent(this, MusicService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
    private class myConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (MusicService.MyBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }
    @Override
    public void onClick(View view) {
        String pathway = path.getText().toString().trim();
        File SDpath = Environment.getExternalStorageDirectory();
        File file = new File(SDpath, pathway);
        String path = file.getAbsolutePath();
        switch (view.getId()) {
            case R.id.tv_play:
                if (file.exists() && file.length()>0) {
                    binder.play(path);
                } else {
                    Toast.makeText(this, "找不到音乐文件！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_stop:
                binder.pause();
                break;
            default:
                break;
        }
    }
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
