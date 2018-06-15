package shift.night.model.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @name 电影详情
 * @class name：shift.night.model.bean
 * @anthor 李永胜 E-mail:liys@wangang.cc
 * @time 2018/6/8
 */
@Table(name = "movie")
public class MovieBean {
    @Column(name = "_id", isId = true, autoGen = false)
    private int id;
    @Column(name = "actionurl")
    private String actionurl;       //拼接地址
    @Column(name = "name")
    private String name;            //名字
    @Column(name = "region")
    private String region;          //地区
    @Column(name = "date")
    private String date;            //日期
    @Column(name = "protagonist")
    private String protagonist;     //主演
    @Column(name = "iamgeurl")
    private String iamgeurl;        //图片地址

    public String getActionurl() {
        return actionurl;
    }

    public void setActionurl(String actionurl) {
        this.actionurl = actionurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(String protagonist) {
        this.protagonist = protagonist;
    }

    public String getIamgeurl() {
        return iamgeurl;
    }

    public void setIamgeurl(String iamgeurl) {
        this.iamgeurl = iamgeurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "id=" + id +
                ", actionurl='" + actionurl + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", date='" + date + '\'' +
                ", protagonist='" + protagonist + '\'' +
                ", iamgeurl='" + iamgeurl + '\'' +
                '}';
    }
}
