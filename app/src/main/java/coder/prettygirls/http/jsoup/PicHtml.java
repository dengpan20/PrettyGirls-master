package coder.prettygirls.http.jsoup;

import android.os.Handler;
import android.os.Message;

import java.util.List;

import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.PicBean;
import coder.prettygirls.data.bean.picbean.Category;

/**
 * Created by Administrator on 2017/6/5.
 */

public class PicHtml {
    private ResponceInfo responceInfo;

    public void setResponceInfo(ResponceInfo responceInfo) {
        this.responceInfo = responceInfo;
    }

    public interface ResponceInfo{
        void getList(List<Category> list);
        void getListFair();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(responceInfo!=null)
                        responceInfo.getList((List<Category>) msg.obj);
                    break;
                case 2:

                    break;
                case 3:

                    break;
            }

        }
    };
}
