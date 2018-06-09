package shift.night.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shift.night.R;
import shift.night.base.BaseFragment;
import shift.night.model.bean.MovieBean;
import shift.night.model.bean.TitleMenu;
import shift.night.presenter.IClassificationDetailsPresenter;
import shift.night.presenter.impl.ClassificationDetailsPresenterImpl;
import shift.night.view.adapter.MovieTypeAdapter;
import shift.night.view.contract.ClassificationDetailsContract;
import shift.night.view.widget.GlideImageLoader;

/**
 * @name 分类列表
 * @class name：shift.night.view.fragment
 * @anthor 家佑
 */
public class ClassificationDetailsFragment extends BaseFragment implements ClassificationDetailsContract {
    @BindView(R.id.month_ranking)
    Banner monthRanking;
    @BindView(R.id.type_str)
    TextView typeStr;
    @BindView(R.id.typle_list)
    RecyclerView typleList;

    private TitleMenu titleMenu;

    private IClassificationDetailsPresenter presenter;

    private MovieTypeAdapter adapter;
    private List<MovieBean> movies = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.classification_details_fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        //初始化  轮播图
        monthRanking.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        monthRanking.setIndicatorGravity(BannerConfig.CENTER);
        monthRanking.setDelayTime(5000);
        monthRanking.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        monthRanking.isAutoPlay(true);

        typeStr.setText(titleMenu.getName());
        presenter = new ClassificationDetailsPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        adapter = new MovieTypeAdapter(movies);
        typleList.setLayoutManager(new LinearLayoutManager(getActivity()));
        typleList.setAdapter(adapter);
        presenter.getData(titleMenu.getActionurl());

    }

    /**
     * 改变类型
     */
    public void setTypeStr(TitleMenu titleMenu) {
        this.titleMenu = titleMenu;
    }

    @Override
    public void setAdapter(List<MovieBean> movies) {
        adapter.changeData(movies);
    }


    @Override
    public void setBanaImage(List<MovieBean> movies) {
        List<String> paths = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (MovieBean bean:movies){
            paths.add(bean.getIamgeurl());
            titles.add(bean.getName());
        }
        //设置图片集合
        monthRanking.setImages(paths);
        //设置标题集合（当banner样式有显示title时）
        monthRanking.setBannerTitles(titles);
        //banner设置方法全部调用完毕时最后调用
        monthRanking.start();
    }
}
