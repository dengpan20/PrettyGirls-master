package coder.prettygirls.app;

import coder.prettygirls.util.FileUtil;

/**
 * Created by oracleen on 2016/7/11.
 * 保存项目中用到的常量
 */
public class Constants {

    public static final String GANHUO_API = "http://gank.io/";

    public static final String dir = FileUtil.getDiskCacheDir(MyApplication.getIntstance()) + "/girls";
    public static String baseUrl="http://www.fydzv.com/tags/%E8%AF%B1%E6%83%91/";
    public static String baseDetailUrl="http://www.fydzv.com/api.php?op=get_imgs&id=";
}
