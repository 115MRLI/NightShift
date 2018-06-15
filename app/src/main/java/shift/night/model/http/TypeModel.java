package shift.night.model.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shift.night.model.bean.MovieBean;

/**
 * @name NightShift
 * @class name：shift.night.model.http
 * @anthor 家佑
 */
public class TypeModel {
    private int CODE = 1;
    private List<MovieBean> movies = new ArrayList<>();
    private TypeMovieCallback typeMovieCallback = null;
    private String name;

    public void getMenu(final String url, String name, TypeMovieCallback typeMovieCallback) {
        this.name = name;
        this.typeMovieCallback = typeMovieCallback;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document doc = Jsoup.connect("http://www.qnvod.net/" + url).get();
                    Elements elements = doc.select("div.lit").select("dl");
                    for (int i = 0; i < elements.size(); i++) {
                        MovieBean movieBean = new MovieBean();
                        Document document = Jsoup.parse(elements.get(i).html());
                        String elementsImage = document.select("img").attr("src");
                        String actionurl = document.select("a").attr("href");
                        String name = document.select("a").attr("title");
                        movieBean.setActionurl(actionurl);
                        movieBean.setIamgeurl(elementsImage);
                        movieBean.setName(name);
                        Elements elementsDD = document.select("dd");
                        for (int j = 0; j < elementsDD.size(); j++) {
                            if (elementsDD.get(j).text().contains("主演")) {
                                movieBean.setProtagonist(elementsDD.get(j).text());
                            } else if (elementsDD.get(j).text().contains("日期")) {
                                movieBean.setDate(elementsDD.get(j).text());
                            } else if (elementsDD.get(j).text().contains("地区")) {
                                movieBean.setRegion(elementsDD.get(j).text());
                            }
                        }
                        movies.add(movieBean);
                    }

                    handler.sendEmptyMessage(CODE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    typeMovieCallback.typeDate(movies);
                    break;
            }
        }
    };

    /**
     * 接口
     */
    public interface TypeMovieCallback {
        /**
         * 分类信息
         *
         * @param movies
         */
        void typeDate(List<MovieBean> movies);

    }
}
