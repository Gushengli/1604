package a1604.day1.qf.studyapplication.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import java.util.List;

import a1604.day1.qf.studyapplication.R;
import a1604.day1.qf.studyapplication.activity.Main2Activity;
import a1604.day1.qf.studyapplication.entity.WorkNews;
import a1604.day1.qf.studyapplication.utils.HttpdownUtil;
import a1604.day1.qf.studyapplication.utils.JsonToBean;
import a1604.day1.qf.studyapplication.utils.MyDbManager;

/**
 * 创建一个类，继承IntentSercice类，重写onHandleIntent方法
 * onHandleIntent方法本身内涵线程，可以在内部直接进行耗时操作
 * 而且onHandleIntent自身执行完毕会自动调用服务关闭的方法stopself（）；
 */
public class NewsService extends IntentService {

    public List<WorkNews> listnews;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    private MyDbManager mMyDbManager;

    public NewsService() {
        super("NewsService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        //intent接收，获取到Url
        String jsonUrl = intent.getStringExtra("jsonUrl");
        //通过工具类，获取json解析bean对象
        byte[] getdown = HttpdownUtil.getdown(jsonUrl);
        //讲bean对象转换成String类型字符串
        String strjson = new String(getdown);
        //通过json工具类解析，并返回一个集合。
        listnews = JsonToBean.getList(strjson);
        //调用的数据库添加的方法，把上述集合对象添加到数据库中。
        insertBean();

        //数据添加完成，判断list集合的长度，一方面确认是否为空，另一方面为了创建通知，并点击跳转到
        // 另一个Activity界面
        if (listnews.size() != 0) {
            //创建通知管理者，通过getSystemService（）方法。
            manager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
            //创建通知栏对象
            builder = new NotificationCompat.Builder(getApplicationContext());
            //给通知栏添加内容和动作。
            builder.setTicker("点我")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("点我会爆哦！")
                    .setContentText("呵呵！")
                    .setAutoCancel(true);
            //创建跳转意图
            Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);
            //创建 PendingIntent，起到包装作用，并延迟跳转
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 1, intent1, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(pi);
            manager.notify(100, builder.build());
        }
    }

    //方法体通过数据库工具类调方法，执行数据库操作，通过list长度，循环添加对象到表中。
    public void insertBean() {
        mMyDbManager = new MyDbManager(getApplicationContext());
        for (int i = 0; i < listnews.size(); i++) {
            mMyDbManager.insertIntoSql(listnews.get(i));
        }
    }
}
