package shift.night.model.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @name NightShift
 * @class name：shift.night.model.http
 * @anthor 家佑
 */
public class MenuModel {
    private int CODE = 1;
    private String urlstr = "";
    public void getMenu() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document doc = Jsoup.connect("http://www.qnvod.net/mlist/3.html").get();
                    Elements elements = doc.select("div.lit").select("dl");
                    Elements resultLinks = doc.select("div.cont");
                    Log.e("http  biaoqian",resultLinks.toString());
                    urlstr = elements.toString();
                    handler.sendEmptyMessage(CODE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.e("http  biaoqian",urlstr);
                    break;
            }
        }
    };
}
