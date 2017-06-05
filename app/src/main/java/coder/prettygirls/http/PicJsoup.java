package coder.prettygirls.http;

import coder.prettygirls.http.jsoup.PicHtml;

/**
 * Created by Administrator on 2017/6/5.
 */

public class PicJsoup {
    private static PicJsoup picJsoup = null;
    private PicJsoup (){

    }
     public static PicJsoup getInstance() {
         if(picJsoup == null){
             picJsoup = new PicJsoup();
         }
        return picJsoup;
    }
    private PicHtml getPicHtml(){
        return  new PicHtml();
    }
}
