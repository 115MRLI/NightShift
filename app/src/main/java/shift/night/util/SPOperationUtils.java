package shift.night.util;

import shift.night.app.App;

/**
 * @name NightShift
 * @class name：shift.night.util
 * @anthor 李永胜 E-mail:liys@wangang.cc
 * @time 2018/6/11
 */
public class SPOperationUtils {
    /**
     * 存储登陆月份
     */
    public static void saveTime() {
        SPUtils.put(App.mContext, "time", NightShiftUtils.getTime());
    }

    /**
     * 获取登陆月份
     *
     * @return
     */
    public static String getTime() {
        return  SPUtils.get(App.mContext, "time", "").toString();
    }
}
