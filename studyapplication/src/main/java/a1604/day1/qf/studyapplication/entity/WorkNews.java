package a1604.day1.qf.studyapplication.entity;

/**
 * Created by Administrator on 2016/6/24.
 */
public class WorkNews {

    public String shorttitle;
    public String litpic;
    public String arcurl;

    public WorkNews(String shorttitle, String litpic, String arcurl) {
        this.shorttitle = shorttitle;
        this.litpic = litpic;
        this.arcurl = arcurl;
    }

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }
}
