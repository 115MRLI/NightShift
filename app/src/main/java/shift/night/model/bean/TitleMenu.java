package shift.night.model.bean;

/**
 * @name 标题栏菜单
 * @class name：shift.night.model.bean
 * @anthor 家佑
 */
public class TitleMenu {
    private String name;
    private String actionurl;

    public TitleMenu(String name, String actionurl) {
        this.name = name;
        this.actionurl = actionurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionurl() {
        return actionurl;
    }

    public void setActionurl(String actionurl) {
        this.actionurl = actionurl;
    }

    @Override
    public String toString() {
        return "TitleMenu{" +
                "name='" + name + '\'' +
                ", actionurl='" + actionurl + '\'' +
                '}';
    }
}
