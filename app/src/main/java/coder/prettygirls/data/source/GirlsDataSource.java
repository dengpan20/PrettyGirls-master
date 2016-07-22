package coder.prettygirls.data.source;

import java.util.List;

import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.GirlsBean;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface GirlsDataSource {

    void getGirl(int page, LoadHtmlGirlCallBack callBack);

    interface LoadGirlsCallback {

        void onGirlsLoaded(GirlsBean girlsBean);

        void onDataNotAvailable();

    }
    interface LoadHtmlGirlCallBack{
        void onGirlsLoaded(List<FPicBean> girlBeanlist);
        void onDataNoteAva();
    }

    void getGirls(int page, int size, LoadGirlsCallback callback);
    void getGirls(int page, LoadHtmlGirlCallBack callBack);
    void getGirl(LoadGirlsCallback callback);
}
