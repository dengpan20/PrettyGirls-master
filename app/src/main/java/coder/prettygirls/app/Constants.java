package coder.prettygirls.app;

import java.util.ArrayList;
import java.util.List;

import coder.prettygirls.data.bean.PicCategory;
import coder.prettygirls.util.FileUtil;

/**
 * Created by oracleen on 2016/7/11.
 * 保存项目中用到的常量
 */
public class Constants {

    public static final String GANHUO_API = "http://gank.io/";
//    public static final String SHOP_ADDRESS ="http://v.yupoo.com/photos/9188/collections/";
    public static final String SHOP_ADDRESS ="http://v.yupoo.com/photos/shisuyundongpifa/collections/";

    public static final String dir = FileUtil.getDiskCacheDir(MyApplication.getIntstance()) + "/girls";
    public static String baseUrl="http://www.fydzv.com/tags/%E8%AF%B1%E6%83%91/";
    public static String category_baseUrl="http://www.fydzv.com/category/";
    public static String baseDetailUrl="http://www.fydzv.com/api.php?op=get_imgs&id=";
    public static List<PicCategory> getCateGory(){
        List<PicCategory> categories=new ArrayList<>();
        categories.add(new PicCategory("清纯","qingchun"));
        categories.add(new PicCategory("性感","xinggan"));
        categories.add(new PicCategory("明星","mingxing"));
        categories.add(new PicCategory("网络","wangluo"));
        categories.add(new PicCategory("模特","mote"));
        categories.add(new PicCategory("丝袜","siwa"));
        return categories;
    }
}
