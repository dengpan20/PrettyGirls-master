package coder.prettygirls.http;

import coder.prettygirls.http.jsoup.ParsHtml;
import coder.prettygirls.http.jsoup.PicHtml;

/**
 * Created by Administrator on 2017/6/5.
 */

public class PicJsoup {

    private static PicHtml picHtml;

    public static PicHtml getParsHtml() {
        if (picHtml == null) synchronized (GirlsJsoup.class) {
            if (picHtml == null) {
                picHtml = new PicHtml();
                return picHtml;
            }
        }
        return picHtml;
    }
}
