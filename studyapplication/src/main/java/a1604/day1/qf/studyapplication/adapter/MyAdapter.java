package a1604.day1.qf.studyapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a1604.day1.qf.studyapplication.R;
import a1604.day1.qf.studyapplication.activity.Main3Activity;
import a1604.day1.qf.studyapplication.entity.WorkNews;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MyAdapter extends BaseAdapter {

    public List<WorkNews> listnews1;
    private RelativeLayout mLayoutleft, mLayoutright;
    private Context mContext;

    public MyAdapter(Context context) {
        mContext = context;
    }

    public void setListnews1(List<WorkNews> listnews1) {
        this.listnews1 = listnews1;
    }


    @Override
    public int getCount() {
        return (listnews1 == null) ? 0 : listnews1.size();
    }

    @Override
    public Object getItem(int position) {
        return listnews1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item, null);
//初始化相对布局，是为了获取点击view整体的点击事件
            mLayoutleft = (RelativeLayout) convertView.findViewById(R.id.rl1);
            mLayoutright = (RelativeLayout) convertView.findViewById(R.id.rl2);
//初始化左右两边的文字和图片
            viewHolder = new ViewHolder();
            viewHolder.leftimage = (ImageView) convertView.findViewById(R.id.iv_1);
            viewHolder.rightimage = (ImageView) convertView.findViewById(R.id.iv_2);
            viewHolder.lefttext = (TextView) convertView.findViewById(R.id.tv1);
            viewHolder.righttext = (TextView) convertView.findViewById(R.id.tv2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
/***
 * 布局相同情况下，获取左边view的对象，position是从0开始的。同样的方法获取右边,两侧的对象分别获取后，
 * 填充数值，使用了xutils3来处理图片，达到优化和三级缓存的目的。
 * **/

        WorkNews workNewsleft = listnews1.get(2 * position);
        WorkNews workNewsright = listnews1.get(2 * position + 1);
        viewHolder.lefttext.setText(workNewsleft.getShorttitle());
        viewHolder.righttext.setText(workNewsright.getShorttitle());
        Picasso.with(mContext)
                .load(workNewsleft.getLitpic())
                .config(Bitmap.Config.RGB_565)
                .into(viewHolder.leftimage);
        Picasso.with(mContext)
                .load(workNewsright.getLitpic())
                .config(Bitmap.Config.RGB_565)
                .into(viewHolder.rightimage);

        //存储web网址，下面的方法是相对布局的点击事件，通过position获取对应点击对象的web页面
        mLayoutleft.setTag(listnews1.get(2 * position).getArcurl());
        mLayoutright.setTag(listnews1.get(2 * position + 1).getArcurl());
        setONListener(mLayoutleft);
        setONListener(mLayoutright);

        return convertView;
    }

    static class ViewHolder {

        ImageView leftimage;
        ImageView rightimage;
        TextView lefttext;
        TextView righttext;
    }

    //相对布局的点击事件的方法体，一个方法，(View v)参数不同，产生左右两部分的点击功能。
    public void setONListener(View v) {

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = v.getTag().toString();
                Intent intent = new Intent(mContext, Main3Activity.class);
                intent.putExtra("url", s);
                mContext.startActivity(intent);
            }
        });
    }
}
