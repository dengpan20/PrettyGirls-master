package coder.prettygirls.http;

import coder.prettygirls.http.jsoup.ParsHtml;

/**
 * Created by dengpan616@qq.com on 2016/7/17.
 */
public class GirlsJsoup {

    private static ParsHtml parsHtml;

    public static ParsHtml getParsHtml() {
        if (parsHtml == null) synchronized (GirlsJsoup.class) {
            if (parsHtml == null) {
                parsHtml = new ParsHtml();
                return parsHtml;
            }
        }
        return parsHtml;
    }

}
