package shift.night.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shift.night.R;
import shift.night.base.BaseFragment;
import shift.night.model.bean.TitleMenu;
import shift.night.util.NightShiftUtils;
import shift.night.view.adapter.MyPagerAdapter;

/**
 * @name 电影
 * @class name：shift.night.view.fragment
 * @anthor 家佑
 */
public class MovieFragment extends BaseFragment {
    @BindView(R.id.title_menu)
    TabLayout titleMenu;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    //当标签数目小于等于4个时，标签栏不可滑动
    public static final int MOVABLE_COUNT = 4;

    private List<TitleMenu> titleMenus;
    private List<Fragment> fragments;

    @Override
    protected int getLayout() {
        return R.layout.movie_fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        setTitleMenus();
        initViewPager();
        initTabLayout();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        viewpager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), fragments));
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 组装头部标题栏集合
     */
    private void setTitleMenus() {
        titleMenus = new ArrayList<>();
        titleMenus.add(new TitleMenu("动作片", "mlist/1.html"));
        titleMenus.add(new TitleMenu("喜剧片", "mlist/2.html"));
        titleMenus.add(new TitleMenu("爱情片", "mlist/3.html"));
        titleMenus.add(new TitleMenu("恐怖片", "mlist/4.html"));
        titleMenus.add(new TitleMenu("科幻片", "mlist/5.html"));
        titleMenus.add(new TitleMenu("动画片", "mlist/6.html"));
        titleMenus.add(new TitleMenu("剧情片", "mlist/7.html"));
        titleMenus.add(new TitleMenu("战争片", "mlist/9.html"));
        fragments = new ArrayList<>();
        for (int i = 0; i < titleMenus.size(); i++) {
            fragments.add(new MyselfFragment());
        }
    }

    /**
     * 初始化标题栏菜单
     */
    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        titleMenu.setTabMode(titleMenus.size() <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        //指示条的颜色
        titleMenu.setSelectedTabIndicatorColor(getResources().getColor(R.color.titlestr));
        titleMenu.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.indicatorHeight));
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        titleMenu.setupWithViewPager(viewpager);
        //自定义标签布局
        for (int i = 0; i < titleMenus.size(); i++) {
            TabLayout.Tab tab = titleMenu.getTabAt(i);
            if (tab != null) {
                TextView menulayout = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.title_menu_itme, titleMenu, false);
                menulayout.setText(titleMenus.get(i).getName());
                tab.setCustomView(menulayout);
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                    tabView.setOnClickListener(mTabOnClickListener);
                }
            }
        }
    }

    /**
     * tabLayout点击事件
     */
    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            NightShiftUtils.showToast(titleMenus.get(pos).getName());
        }
    };

}
