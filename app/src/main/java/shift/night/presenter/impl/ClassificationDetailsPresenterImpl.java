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
    public void getData(String url) {
        if (baseView != null){
            model.getMenu(url, new TypeModel.TypeMovieCallback() {
                @Override
                public void typeDate(List<MovieBean> movies) {
//                    for (MovieBean movieBean : movies) {
//                        Log.e("详情", movieBean.toString());
//                    }
                    baseView.setAdapter(movies);
                }

                @Override
                public void setBanner(List<MovieBean> movies) {
                    baseView.setBanaImage(movies);
                }
            });
        }
    }
}
