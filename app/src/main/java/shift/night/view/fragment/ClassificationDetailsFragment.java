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
    @BindView(R.id.typle_list)
    RecyclerView typleList;

    private TitleMenu titleMenu;

    private IClassificationDetailsPresenter presenter;

    private MovieTypeAdapter adapter;
    private List<MovieBean> movies = new ArrayList<>();
    private int n = 1;
    @Override
    protected int getLayout() {
        return R.layout.classification_details_fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
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
        presenter.getData(titleMenu.getActionurl(), titleMenu.getName());
        //解决recycleview与滚动布局冲突
        typleList.setNestedScrollingEnabled(false);
        adapter.setRMovieTypeListener(new MovieTypeAdapter.MovieTypeListener() {
            @Override
            public void itmeClick(MovieBean item) {

            }

            @Override
            public void onLoad() {
                switch (titleMenu.getName()) {
                    case "动作片":
                        break;
                    case "喜剧片":
                        break;
                    case "爱情片":
                        break;
                    case "恐怖片":
                        break;
                    case "科幻片":
                        break;
                    case "动画片":
                        break;
                    case "剧情片":
                        break;
                    case "战争片":
                        break;

                }
            }
        });

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


}
