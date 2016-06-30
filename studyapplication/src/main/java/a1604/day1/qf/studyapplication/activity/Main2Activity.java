package a1604.day1.qf.studyapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import a1604.day1.qf.studyapplication.R;
import a1604.day1.qf.studyapplication.adapter.MyAdapter;
import a1604.day1.qf.studyapplication.entity.WorkNews;
import a1604.day1.qf.studyapplication.utils.MyDbManager;

public class Main2Activity extends AppCompatActivity {

    private PullToRefreshListView pvshow;
    private List<WorkNews> listnews;
    private MyAdapter adapter;
    private MyDbManager mMyDbManager;

    //创建handler对象，用于接收并更新唤醒Adapter。
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    adapter.setListnews1(listnews);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        setData();
    }

    //重点：创建Adapter，先将Adapter数据填充为null，因为图片填充是一个耗时操作，以防空指针异常

    private void setData() {
        adapter = new MyAdapter(this);
        adapter.setListnews1(null);
        pvshow.setAdapter(adapter);
//这个是用来设置PullToRefreshBase的模式的，下一行为方法。
        pvshow.setMode(PullToRefreshBase.Mode.BOTH);
        initRefreshListView();

//起一个线程，执行数据库查询的耗时操作，并发送空消息给当前，以便更新Adapter
        new Thread() {
            @Override
            public void run() {
                mMyDbManager = new MyDbManager(getApplicationContext());
                listnews = mMyDbManager.getList();
                mHandler.sendEmptyMessage(1);
            }
        }.start();
    }

    private void initView() {
        pvshow = (PullToRefreshListView) findViewById(R.id.lv);

    }

    //PullToRefreshListView的参数和类型设定的方法
    public void initRefreshListView() {
        ILoadingLayout startLabels = pvshow.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在拉");
        startLabels.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = pvshow.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");

    }
}
