package shift.night.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shift.night.R;
import shift.night.app.App;
import shift.night.model.bean.MovieBean;
import shift.night.view.widget.GlideRoundTransform;


/**
 * @name NightShift
 * @class name：shift.night.view.adapter
 * @anthor 李永胜 E-mail:liys@wangang.cc
 * @time 2018/6/8
 */
public class MovieTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieBean> movies;
    private MovieTypeListener listener;

    public MovieTypeAdapter(List<MovieBean> movies) {
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_movie_itme, parent, false);
        return new MovieTypeHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieTypeHolder) {
            MovieTypeHolder menuHolder = (MovieTypeHolder) holder;
            menuHolder.bindItem(movies.get(position));
            Log.e("监听", "position: " + position + "  itemList.size()  :" + movies.size());
            if (position == movies.size() - 1) {
//                movies.onLoad(data);
            }
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieTypeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_img)
        ImageView movie_img;
        @BindView(R.id.date_time)
        TextView date_time;
        @BindView(R.id.region)
        TextView region;
        @BindView(R.id.protagonist)
        TextView protagonist;
        @BindView(R.id.name)
        TextView name;

        public MovieTypeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(final MovieBean item) {
            Glide.with(App.mContext).load(item.getIamgeurl()).centerCrop().into(movie_img);
            date_time.setText(item.getName());
            region.setText(item.getRegion());
            protagonist.setText(item.getProtagonist());
            name.setText(item.getName());
        }
    }

    public interface MovieTypeListener {
        /**
         * itme点击
         *
         * @param item
         */
        void itmeClick(MovieBean item);

        /**
         * 加载更多
         *
         * @param data
         */
        void onLoad(long data);
    }

    public void setRMovieTypeListener(MovieTypeListener listener2) {
        listener = listener2;
    }

    public void changeData(List<MovieBean> itemList2) {
        for (MovieBean itemListBean : itemList2) {
            movies.add(itemListBean);
        }
        notifyDataSetChanged();
    }
}
