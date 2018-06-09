package shift.night.view.contract;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import shift.night.base.BaseView;
import shift.night.model.bean.MovieBean;

/**
 * @name NightShift
 * @class name：shift.night.view.contract
 * @anthor 李永胜 E-mail:liys@wangang.cc
 */
public interface ClassificationDetailsContract extends BaseView {
    /**
     * 进度条样式弹窗
     */
    void progressDialog(String str);

    /**
     * 展示提示弹窗
     *
     * @param content  展示内容
     * @param callback 回调
     */
    void showDialog(String content, MaterialDialog.SingleButtonCallback callback);

    /**
     * 关闭进度条样式弹窗
     */
    void cancelDialog();

    /**
     * 添加数据源
     *
     * @param movies
     */
    void setAdapter(List<MovieBean> movies);

    /**
     * 设置轮播图
     *
     * @param movies
     */
    void setBanaImage(List<MovieBean> movies);
}
