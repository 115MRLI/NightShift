package shift.night.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import shift.night.R;
import shift.night.app.App;
import shift.night.util.ThreadUtils;

/**
 * @name VideoDome
 * @class name：video.videodome.base
 * @anthor 家佑
 * @time 2018/4/10
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Activity mContext;
    private Unbinder mUnBinder;
    //弹窗
    private MaterialDialog.Builder mBuilder;
    private MaterialDialog mMaterialDialog;
    private static final int MSG_DISMISS_MATERIAL_PROGRESS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(this.getClass() + "", this.getClass().getName() + "------>onCreate");
        setContentView(getLayout());
        App.getInstance().registerActivity(this);
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        initView();
        initEvent();
        getIntentData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(this.getClass() + "", this.getClass().getName() + "------>onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(this.getClass() + "", this.getClass().getName() + "------>onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(this.getClass() + "", this.getClass().getName() + "------>onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(this.getClass() + "", this.getClass().getName() + "------>onDestroy");
        if (mUnBinder != null)
            mUnBinder.unbind();
        App.getInstance().unregisterActivity(this);
    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    protected void setWindowFullScreen() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 19) {
            // 虚拟导航栏透明
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 展示弹窗
     *
     * @param content  展示主题内容
     * @param callback 监听
     */
    public void showDialog(String content, MaterialDialog.SingleButtonCallback callback) {
        if (!this.isFinishing()) {
            mBuilder = new MaterialDialog.Builder(this);
            mBuilder.title("提示");
            mBuilder.titleColor(Color.parseColor("#000000"));
            mBuilder.content(content);
            mBuilder.contentColor(Color.parseColor("#000000"));
            mBuilder.positiveText("确定");
            mBuilder.titleGravity(GravityEnum.CENTER);
            mBuilder.buttonsGravity(GravityEnum.START);
            mBuilder.negativeText("取消");
            mBuilder.cancelable(false);
            mMaterialDialog = mBuilder.build();
            mMaterialDialog.show();
            mBuilder.onAny(callback);
//                new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                if (which == DialogAction.POSITIVE) {
//                    mMaterialDialog.dismiss();
//                } else if (which == DialogAction.NEGATIVE) {
//                    mMaterialDialog.dismiss();
//                }
//            }
//        });
        }
    }

    /**
     * 关闭弹窗
     */
    public void dissDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

    private MaterialDialog dialog;


    /**
     * Material样式进度条 Progress dialog.
     *
     * @param title the title
     */
    public void progressDialog(final String title) {
        if (!this.isFinishing()) {
            ThreadUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog == null) {
                        dialog = new MaterialDialog.Builder(mContext)
                                .title(title)
                                .canceledOnTouchOutside(false).cancelable(false)
                                .customView(R.layout.custom_view, true)
                                .show();
                        // 发送延时消息,在handler里面操作,隐藏progressview
                        mHandler.sendEmptyMessageDelayed(MSG_DISMISS_MATERIAL_PROGRESS, Integer.valueOf(6000));
                    } else {
                        dialog.setTitle(title);
                        if (!dialog.isShowing()) {
                            dialog.show();
                        }
                    }
                }
            });
        }
    }

    /**
     * 关闭进度弹窗
     */
    public void cancelDialog() {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }

    /**
     * 创建一个handler处理隐藏
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DISMISS_MATERIAL_PROGRESS:
                    cancelDialog();
                    break;
                default:
                    break;
            }
        }

    };

    //
    protected abstract int getLayout();

    /**
     * 初始化页面
     */
    protected void initView() {
    }

    /**
     * 设置监听
     */
    protected void initEvent() {
    }

    /**
     * 设置数据
     */
    protected void getIntentData() {
    }
}
