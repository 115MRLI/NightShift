package shift.night.model.bean;

/**
 * @name 电影详情
 * @class name：shift.night.model.bean
 * @anthor 李永胜 E-mail:liys@wangang.cc
 * @time 2018/6/8
 */
public class MovieBean {
    private String actionurl;       //拼接地址
    private String name;            //名字
    private String region;          //地区
    private String date;            //日期
    private String protagonist;     //主演
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

    @Override
    public String toString() {
        return "MovieBean{" +
                "actionurl='" + actionurl + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", date='" + date + '\'' +
                ", protagonist='" + protagonist + '\'' +
                ", iamgeurl='" + iamgeurl + '\'' +
                '}';
    }
}
