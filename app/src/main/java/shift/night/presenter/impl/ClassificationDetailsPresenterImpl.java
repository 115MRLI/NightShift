package shift.night.presenter.impl;

import android.util.Log;

import java.util.List;

import shift.night.model.bean.MovieBean;
import shift.night.model.http.TypeModel;
import shift.night.presenter.IClassificationDetailsPresenter;
import shift.night.view.contract.ClassificationDetailsContract;

/**
 * @name NightShift
 * @class name：shift.night.presenter.impl
 * @anthor 李永胜 E-mail:liys@wangang.cc
 * @time 2018/6/8
 */
public class ClassificationDetailsPresenterImpl<T extends ClassificationDetailsContract> implements IClassificationDetailsPresenter<T> {
    private T baseView;
    private TypeModel model;
    public ClassificationDetailsPresenterImpl() {
        model = new TypeModel();
    }

    @Override
    public void attachView(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void detachView() {
        baseView = null;
    }

    @Override
    public void getData(String url,String name) {
        if (baseView != null){
            model.getMenu(url,name ,new TypeModel.TypeMovieCallback() {
                @Override
                public void typeDate(List<MovieBean> movies) {

                    baseView.setAdapter(movies);
                }
            });
        }
    }
}
