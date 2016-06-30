package a1604.day1.qf.studyapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import a1604.day1.qf.studyapplication.entity.WorkNews;
import a1604.day1.qf.studyapplication.url.JsonUrl;

/**
 * 创建此类，是为了生成表管理对象。
 */
public class MyDbManager {

    private MyHelper mMyHelper;
    private WorkNews mWorkNews;

    //构造方法决定了，一旦MyDbManager被创建，数据库对象就创建了。
    public MyDbManager(Context context) {
        mMyHelper = new MyHelper(context);
    }

    //插入数据的方法
    public void insertIntoSql(WorkNews news) {
        //数据库调用可读写的方法，创建表管理对象。
        SQLiteDatabase db = mMyHelper.getReadableDatabase();
        //表管理对象开启事务，是为了获取批量添加对象的能力
        db.beginTransaction();
        try {
            //创建ContentValues对象，就像map一样的键值对，负责存储
            ContentValues values = new ContentValues();
            values.put("shorttitle", news.getShorttitle());
            values.put("litpic", JsonUrl.URLHEAD + news.getLitpic());
            values.put("arcurl", news.getArcurl());
            db.insert("news", null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    //查询表，返回list对象。
    public List<WorkNews> getList() {
        List<WorkNews> listnews = new ArrayList<>();
        SQLiteDatabase db = mMyHelper.getReadableDatabase();
        //执行sql查询语句，返回cursor对象。
        Cursor cursor = db.rawQuery("select * from news", null);
        //通过while循环，遍历cursor，最终获取一个list对象
        while (cursor.moveToNext()) {
            String shorttitle = cursor.getString(cursor.getColumnIndex("shorttitle"));
            String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
            String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
            mWorkNews = new WorkNews(shorttitle, litpic, arcurl);
            listnews.add(mWorkNews);
        }
        cursor.close();
        db.close();
        return listnews;
    }

    //通过表管理者调delete方法，删除当前表。
    public void deletedb() {
        SQLiteDatabase db = mMyHelper.getReadableDatabase();
        db.delete("news", null, null);
    }
}
