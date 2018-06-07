package shift.night.view.contract;

import android.support.v7.widget.RecyclerView;

import shift.night.base.BaseView;


/**
 * @name Onlyou
 * @class name：only.you.ui.contract
 * @anthor 家佑
 */
public interface HomeContract extends BaseView {
    /**
     * 吐丝展示内容
     *
     * @param details 内容
     */
    void showToast(String details);

    /**
     * 加入适配器
     *
     * @param adapter
     */
    void addAdapter(RecyclerView.Adapter adapter);
}
