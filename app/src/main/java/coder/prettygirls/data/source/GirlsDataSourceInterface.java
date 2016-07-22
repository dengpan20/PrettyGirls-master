package coder.prettygirls.data.source;

import coder.prettygirls.data.bean.PicBean;


/**
 * Created by dengpan616@qq.com on 2016/7/17.
 */
public interface GirlsDataSourceInterface {
    public interface LoadInfoDetail{
        void getList(PicBean picBean);
        void getFailed();
    }
   void getGirlDetail(String url,LoadInfoDetail responceInfoDetail);
}
