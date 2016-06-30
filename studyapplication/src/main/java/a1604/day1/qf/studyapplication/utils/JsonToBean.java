package a1604.day1.qf.studyapplication.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a1604.day1.qf.studyapplication.entity.WorkNews;

/**
 * Created by Administrator on 2016/6/24.
 */
public class JsonToBean {

    public static List<WorkNews> getList(String strJson) {

        List<WorkNews> list = new ArrayList<>();
        WorkNews mnews;

        try {
            JSONObject obj1 = new JSONObject(strJson);
                JSONObject obj2 = obj1.getJSONObject("data");
                for (int i = 0; i < obj2.length(); i++) {

                    JSONObject obj3 = obj2.getJSONObject(""+i);
                    String shorttitle = obj3.getString("shorttitle");
                    String litpic = obj3.getString("litpic");
                    String arcurl = obj3.getString("arcurl");
                    mnews = new WorkNews(shorttitle, litpic, arcurl);
                    list.add(mnews);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
