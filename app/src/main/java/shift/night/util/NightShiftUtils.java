package shift.night.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import shift.night.app.App;


/**
 * @name Onlyou
 * @class name：only.you.utils
 * @anthor 家佑
 * @time 2018/4/20
 */
public class NightShiftUtils {
    private static Toast toast = null;

    /**
     * 吐丝方法
     *
     * @param message 吐丝内容
     */
    public static void showToast(final String message) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                    toast = null;
                } else {
                    toast = Toast.makeText(App.mContext, message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    /**
     * 获取挡前日期精确至月份
     */
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String newtime = format.format(new Date());
        return newtime;
    }
}
