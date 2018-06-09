package shift.night.view.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @name NightShift
 * @class name：shift.night.view.widget
 * @anthor 李永胜 E-mail:liys@wangang.cc
 * @time 2018/6/9
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
        Glide.with(context).load(path).centerCrop().into(imageView);
    }
}
