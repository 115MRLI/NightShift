package shift.night.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observer;
import rx.Subscription;
import shift.night.R;
import shift.night.base.BaseActivity;
import shift.night.model.http.MenuModel;
import shift.night.util.NightShiftUtils;
import shift.night.view.contract.HomeContract;

public class HomeActivity extends BaseActivity implements HomeContract {
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.home_page)
    LinearLayout homePage;
    @BindView(R.id.attention)
    LinearLayout attention;
    @BindView(R.id.inform)
    LinearLayout inform;
    @BindView(R.id.myself)
    LinearLayout myself;
    @BindView(R.id.home_page_iv)
    ImageView homePageIv;
    @BindView(R.id.attention_iv)
    ImageView attentionIv;
    @BindView(R.id.inform_iv)
    ImageView informIv;
    @BindView(R.id.myself_iv)
    ImageView myselfIv;
    @BindView(R.id.home_page_tv)
    TextView homePageTv;
    @BindView(R.id.attention_tv)
    TextView attentionTv;
    @BindView(R.id.inform_tv)
    TextView informTv;
    @BindView(R.id.myself_tv)
    TextView myselfTv;
    private List<LinearLayout> linearLayouts = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private List<ImageView> imageViews = new ArrayList<>();
    private int[] image = {R.mipmap.ic_tab_strip_icon_feed, R.mipmap.ic_tab_strip_icon_follow
            , R.mipmap.ic_tab_strip_icon_category, R.mipmap.ic_tab_strip_icon_profile};
    private int[] imageselect = {R.mipmap.ic_tab_strip_icon_feed_selected, R.mipmap.ic_tab_strip_icon_follow_selected
            , R.mipmap.ic_tab_strip_icon_category_selected, R.mipmap.ic_tab_strip_icon_profile_selected};


    private Subscription mSubscription;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        super.initView();
        getLIines();
        getImages();
        getTexts();
        for (LinearLayout linearLayout : linearLayouts) {
            onClick(linearLayout);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        MenuModel menuModel = new MenuModel();
        menuModel.getMenu();
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
    }

    /**
     * 跳转到本页
     *
     * @param context
     */
    public static void junmpHere(Activity context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    /**
     * 获取菜单
     */
    private void getLIines() {
        linearLayouts.add(homePage);
        linearLayouts.add(attention);
        linearLayouts.add(inform);
        linearLayouts.add(myself);
    }

    /**
     * 获取菜单文字
     */
    private void getTexts() {
        textViews.add(homePageTv);
        textViews.add(attentionTv);
        textViews.add(informTv);
        textViews.add(myselfTv);
    }

    /**
     * 获取菜单字体
     */
    private void getImages() {
        imageViews.add(homePageIv);
        imageViews.add(attentionIv);
        imageViews.add(informIv);
        imageViews.add(myselfIv);
    }

    /**
     * 改变选中菜单样式
     *
     * @param position 要改变得菜单
     */
    private void changeStyle(int position) {
        for (int i = 0; i < imageViews.size(); i++) {
            if (i == position) {
                imageViews.get(i).setBackgroundResource(imageselect[i]);
                textViews.get(i).setTextColor(Color.parseColor("#333333"));
            } else {
                imageViews.get(i).setBackgroundResource(image[i]);
                textViews.get(i).setTextColor(Color.parseColor("#A7A7A7"));
            }
        }
    }

    /**
     * 跳转fragment
     *
     * @param fragment 要跳转的
     */
    private void jumpFragment(Fragment fragment) {
        //跳转到fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    /**
     * 防抖
     *
     * @param view
     */
    private void onClick(final View view) {
        RxView.clicks(view)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        switch (view.getId()) {
                            case R.id.home_page:
                                changeStyle(0);
//                                jumpFragment(new HomeFragment());
                                break;
                            case R.id.attention:
                                changeStyle(1);
//                                jumpFragment(new AdmireInTheHeartFragemnt());
                                break;
                            case R.id.inform:
                                changeStyle(2);
//                                jumpFragment(new DayByDayFragment());
                                break;
                            case R.id.myself:
                                changeStyle(3);
//                                jumpFragment(new MyselFragment());
                                break;
                            default:

                                break;
                        }
                    }
                });
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    NightShiftUtils.showToast("再按一次退出程序");
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void showToast(String details) {

    }

    @Override
    public void addAdapter(RecyclerView.Adapter adapter) {

    }
}
