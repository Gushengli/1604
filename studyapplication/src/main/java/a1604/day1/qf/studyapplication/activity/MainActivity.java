package a1604.day1.qf.studyapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;

import a1604.day1.qf.studyapplication.R;
import a1604.day1.qf.studyapplication.url.JsonUrl;
import a1604.day1.qf.studyapplication.utils.MyDbManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnmain,btnmap;
    private Intent intent;
    private MyDbManager mMyDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void setListener() {
        btnmain.setOnClickListener(this);
        btnmap.setOnClickListener(this);
    }

    private void initView() {
        btnmain = (Button) findViewById(R.id.btn_main);
        btnmap = (Button) findViewById(R.id.btn_map);
        //创建表管理对象，每次Oncreat就执行删除之前的表
        mMyDbManager = new MyDbManager(this);
        mMyDbManager.deletedb();
    }

    @Override
    public void onClick(View v) {
  //点击事件，开启服务，并把url传递，使用startService。
        switch (v.getId()){
            case R.id.btn_main:
                intent = new Intent();
                intent.putExtra("jsonUrl", JsonUrl.URL);
                intent.setAction("com.qf.1604");
                startService(intent);
                break;
            case R.id.btn_map:
                intent = new Intent(this,LocationDemo.class);
                startActivity(intent);
                break;
        }
    }
}
