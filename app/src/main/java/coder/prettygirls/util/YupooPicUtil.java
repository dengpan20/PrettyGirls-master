package coder.prettygirls.util;

/**
 * Created by dengpan on 17/6/12.
 */

public class YupooPicUtil {
    public static String getBigPic(String pic){
        if(StringUtil.isNotEmpty(pic)){
            return pic.substring(0, pic.lastIndexOf("/"))+"/big.jpg";
        }
        return null;
    }
}
