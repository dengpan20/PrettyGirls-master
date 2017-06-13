package coder.prettygirls.http.jsoup;


import android.util.Log;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Pic;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.util.StringUtil;


/**
 * Created by Administrator on 2017/4/21.
 */
public class GetInfoFromNet {
    private static List<Category> categories =new ArrayList<>();
    private static List<Prod> prods = new ArrayList<>();
    private static Prod prod;

    public static void start(String url,OnGetInfoComplete complete){
        startNextPageCategory(url);
        complete.onComplete(categories);
    }
    public static void startCate(String url,int page,OnGetInfoComplete complete){
//        startNextPageCategory(url);
        categories.clear();
        getCate(url,page,complete);
        complete.onComplete(categories);
    }
    public static void startItem(Category category,int page,OnGetItemComplete complete){
        prods.clear();
        getCategoryItemWithPage(category,page);
        complete.Complete(prods);
    }
    public static void startProd(Prod prod,OnGetProdComplete complete){
        getProdAlbum(prod);
        complete.Complete(prod);
    }

    /**
     * 获取分类列表
     * @param url
     * @param page
     * @param complete
     */
    public static void getCate(String url ,int page,OnGetInfoComplete complete){
        int pageNums = getPageNums(url);
        LogUtil.d("传入的size＝"+page+"网页的page＝"+pageNums);
        if(page> pageNums){
            return;
        }else {
            startNextPageCategory(url,page);
        }
    }

    /**
     * 获取相册列表数
     * @param url
     */
    public static int getPageNums(String url){
        Document document= null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements pages = document.getElementsByClass("pages");
        Elements a = pages.select("a");
        if(a.size()==0){
            return 1;
        }
//        log(a.size()+"");
        return a.size();

//        log(categories.size());
    }
    /**
     * 获取相册列表数
     * @param document
     */
    public static int getPageNumsFromDoc(Document document){

        Elements pages = document.getElementsByClass("pages");
        Elements a = pages.select("a");
        if(a.size()==0){
            return 1;
        }
        return a.size();

//        log(categories.size());
    }
    public static void startNextPageCategory(String url){
        for (int i=0;i<getPageNums(url);i++){
            String indexUrl = url+"page"+(i+1);
            getCategory(indexUrl);
        }
    }
    public static void startNextPageCategory(String url,int page){
        if(page==1||page == 0){
            page = 0;
        }
            String indexUrl = url+"page"+(page+1);
            getCategory(indexUrl);
    }
    public static void startNextPageProd(String url){
        for (int i=0;i<getPageNums(url);i++){
            String indexUrl = url+"page"+(i+1);
            getCategory(indexUrl);
        }
    }

    /**
     * 获取分类不包含item
     * @param url
     */
    public static void getCategoryWithoutItem(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements pages = document.select("pages");
        Elements a = pages.select("a");
        Elements legend = document.select("fieldset");
        for (int i = 0; i < legend.size(); i++) {
            Elements legend1 = legend.get(i).select("legend");
            Category category = new Category();
//            List<Prod> prods = new ArrayList<>();
            Elements moreElement = legend.get(i).getElementsByClass("more-albums-btn");
            if (moreElement != null && moreElement.size() > 0) {
                category.setHasMore(true);
                Elements as = moreElement.get(0).select("a");
                String abs_href = as.get(0).attr("abs:href");
                category.setMoreUrl(abs_href);
            }

            String categoryname = legend1.get(0).select("a").get(0).text();
            String href = legend1.get(0).select("a").get(0).attr("href");
            category.setCategory_url(href);
            category.setTitle(categoryname);
//            getCategoryItem(legend.get(i), category, prods);
//            getCategoryItem(category, prods);
            categories.add(category);
        }
    }

    /**
     * 获取分类并获取分类的item
     * @param url
     */
    public static void getCategory(String url) {

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements pages = document.select("pages");
        Elements a = pages.select("a");
        Elements legend = document.select("fieldset");
        for (int i = 0; i < legend.size(); i++) {
            Elements legend1 = legend.get(i).select("legend");
            Category category = new Category();
            List<Prod> prods = new ArrayList<>();
            Elements moreElement = legend.get(i).getElementsByClass("more-albums-btn");
            if (moreElement != null && moreElement.size() > 0) {
                category.setHasMore(true);
                Elements as = moreElement.get(0).select("a");
                String abs_href = as.get(0).attr("abs:href");
                category.setMoreUrl(abs_href);
            }

            String categoryname = legend1.get(0).select("a").get(0).text();
            String href = legend1.get(0).select("a").get(0).attr("href");
            category.setCategory_url(href);
            category.setTitle(categoryname);
//            getCategoryItem(legend.get(i), category, prods);
            getCategoryItem(category, prods);
            categories.add(category);

        }
    }
    /**
     * 获取分类的item page
     * @param
     */
    private static void getCategoryItemWithPage( Category category, int page) {
        LogUtil.d("传入的size＝"+page);
        if(page==1||page == 0){
            page = 0;
        }
        String indexUrl = category.getCategory_url()+"page"+(page+1);
        Document htmlDouc = getHtmlDouc(indexUrl);
        if(page>getPageNumsFromDoc(htmlDouc))//页数
        {
            return;
        }
        if(htmlDouc==null){
            return;
        }
        if(htmlDouc!= null && htmlDouc.getElementsByClass("Sets").size()== 0){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtil.i("睡眠了一次，地址是："+category.getCategory_url());
            getHtmlDouc(category.getCategory_url());
        }
            Elements sets = htmlDouc.getElementsByClass("Sets");
//        log(sets.size()+"");
            for(int i =0; i<sets.size();i++) {
                Prod prod = new Prod();
                prod.setShop_id(category.getShop_id());
                Elements as = sets.get(i).select("a");
                String href = as.get(0).attr("href");
                prod.setProdUrl(href);
                if(!href.startsWith("http")){
                    String abs_href = as.get(0).attr("abs:href");
                    prod.setProdUrl(abs_href);
                }

                Elements imgs = sets.get(i).select("img");
                LogUtil.i(imgs.size()+"=="+imgs.get(0).outerHtml());
                String img_small = imgs.get(0).attr("src");
                if(!StringUtil.isNotEmpty(img_small)){
                    img_small = imgs.get(0).attr("src_data");
                }
                LogUtil.i("img_samll"+img_small);
                prod.setProdImag_small(img_small);
                String pro_name = imgs.get(0).attr("alt");
                prod.setProdName(pro_name);
                Elements bs = sets.get(i).select("b");
                String sizeStr = bs.get(0).text();
//            log(sizeStr);
                if (sizeStr != null && sizeStr != "" && sizeStr.length() > 0) {
                    prod.setPicSize(Integer.valueOf(sizeStr));
                }
//                getProdAlbum(prod);
                prods.add(prod);
            }

        category.setListprod(prods);

    }
    /**
     * 获取分类的item
     * @param
     */
    private static void getCategoryItem( Category category, List<Prod> prods) {
        Document htmlDouc = getHtmlDouc(category.getCategory_url());
        if(htmlDouc==null){
            return;
        }
        if(htmlDouc!= null && htmlDouc.getElementsByClass("Sets").size()== 0){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtil.i("睡眠了一次，地址是："+category.getCategory_url());
            getHtmlDouc(category.getCategory_url());
        }
        for (int j=0; j<getPageNumsFromDoc(htmlDouc);j++){
            Elements sets = htmlDouc.getElementsByClass("Sets");
//        log(sets.size()+"");
            for(int i =0; i<sets.size();i++) {
                Prod prod = new Prod();
                prod.setShop_id(category.getShop_id());
                Elements as = sets.get(i).select("a");
                String href = as.get(0).attr("href");
                prod.setProdUrl(href);
                if(!href.startsWith("http")){
                    String abs_href = as.get(0).attr("abs:href");
                    prod.setProdUrl(abs_href);
                }

                Elements imgs = sets.get(i).select("img");
                LogUtil.i(imgs.size()+"=="+imgs.get(0).outerHtml());
                String img_small = imgs.get(0).attr("src");
                if(!StringUtil.isNotEmpty(img_small)){
                    img_small = imgs.get(0).attr("src_data");
                }
                LogUtil.i("img_samll"+img_small);
                prod.setProdImag_small(img_small);
                String pro_name = imgs.get(0).attr("alt");
                prod.setProdName(pro_name);
                Elements bs = sets.get(i).select("b");
                String sizeStr = bs.get(0).text();
//            log(sizeStr);
                if (sizeStr != null && sizeStr != "" && sizeStr.length() > 0) {
                    prod.setPicSize(Integer.valueOf(sizeStr));
                }
//                getProdAlbum(prod);
                prods.add(prod);
            }
        }

        category.setListprod(prods);

    }
    /**
     * 获取单个产品的相册图片
     * @param prod
     */
    public static void getProdAlbum(Prod prod){
        List<String> piclist = new ArrayList<>();
        Document document= null;
        try {
            document = Jsoup.connect(prod.getProdUrl()).get();
        } catch (HttpStatusException e) {
            if(e.getStatusCode()==404){
                prod.setPiclist(piclist);
                return;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        Element albumView = document.getElementById("main");
        if(albumView == null){
//            String html = albumView.outerHtml();
//            log(html);
            prod.setGetHtmlTime(prod.getGetHtmlTime()+1);
            try {
                Thread.sleep(1000*prod.getGetHtmlTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(prod.getPicSize()>0){
                LogUtil.i("没获取到相册地址："+prod.getProdUrl());
                getProdAlbum(prod);
            }
            return;

        }
        Elements imgs = albumView.select("img");
        for (Element element :imgs){
            String picurl  = element.attr("src");
            piclist.add(picurl);
        }
        prod.setPiclist(piclist);
    }
    public static Document getHtmlDouc(String url){
        Document document = null;
        try {
           document= Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            if(e.getStatusCode()==404){
                LogUtil.i("此链接为空"+url);
                return null;
            }
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }

        return  document;
    }
    public interface OnGetInfoComplete{
        void onComplete(List<Category> categories);
    }
    private OnGetInfoComplete onGetInfoComplete;
    private OnGetItemComplete onGetItemComplete;
    private OnGetProdComplete onGetProdComplete;

    public static void setCategories(List<Category> categories) {
        GetInfoFromNet.categories = categories;
    }

    public void setOnGetItemComplete(OnGetItemComplete onGetItemComplete) {
        this.onGetItemComplete = onGetItemComplete;
    }

    public void setOnGetProdComplete(OnGetProdComplete onGetProdComplete) {
        this.onGetProdComplete = onGetProdComplete;
    }

    public interface OnGetItemComplete{
        void Complete(List<Prod> prods);
    }
    public interface OnGetProdComplete{
        void Complete(Prod prod);
    }
}
