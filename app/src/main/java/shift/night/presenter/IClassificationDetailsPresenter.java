package shift.night.presenter;

import shift.night.base.BasePresenter;
import shift.night.base.BaseView;

/**
 * @name NightShift
 * @class name：shift.night.presenter
 * @anthor 家佑
 */
public interface IClassificationDetailsPresenter<T extends BaseView> extends BasePresenter<T> {
    /**
     * 获取数据
     *
     * @param url
     */
    void getData(String url,String name);
}
