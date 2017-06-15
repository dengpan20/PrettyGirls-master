package coder.prettygirls.util;

import java.util.regex.Pattern;

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
    public static String getSmallPic(String pic){
        if(StringUtil.isNotEmpty(pic)){
            return pic.substring(0, pic.lastIndexOf("/"))+"/small.jpg";
        }
        return null;
    }
    public static String getSqurePic(String pic){
        if(StringUtil.isNotEmpty(pic)){
            return pic.substring(0, pic.lastIndexOf("/"))+"/square.jpg";
        }
        return null;
    }
    public static String getFileName(String str){
        Pattern FilePattern = Pattern.compile("[\\\\/:*?\"<>|]");

        return str==null?null:FilePattern.matcher(str).replaceAll("");

    }
}
