package a1604.day1.qf.studyapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建并维护数据库，继承两个方法，一个构造！
 */
public class MyHelper extends SQLiteOpenHelper{

    public MyHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table if not exists news(newsId integer primary key autoincrement," +
               "shorttitle varchar(100),litpic varchar(100),arcurl varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
