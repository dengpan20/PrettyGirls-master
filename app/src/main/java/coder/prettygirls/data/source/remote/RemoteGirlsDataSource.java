package coder.prettygirls.data.source.remote;

import java.net.URL;
import java.util.List;

import coder.prettygirls.app.Constants;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.bean.PicBean;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsDataSourceInterface;
import coder.prettygirls.http.GirlsJsoup;
import coder.prettygirls.http.GirlsRetrofit;
import coder.prettygirls.http.GirlsService;
import coder.prettygirls.http.jsoup.ParsHtml;
import coder.prettygirls.util.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oracleen on 2016/6/29.
 */
public class RemoteGirlsDataSource implements GirlsDataSource ,GirlsDataSourceInterface{

    @Override
    public void getGirl(int page, LoadHtmlGirlCallBack callBack) {
        getGirls(page, callBack);
    }

    @Override
    public void getGirls(int page, int size, final LoadGirlsCallback callback) {
        GirlsRetrofit.getRetrofit()
                .create(GirlsService.class)
                .getGirls("福利", size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(GirlsBean girlsBean) {
                        callback.onGirlsLoaded(girlsBean);
                    }
                });
    }

    @Override
    public void getGirls(int page, final LoadHtmlGirlCallBack callBack) {
        String url;
        if(page==1){
            url= Constants.baseUrl;
        }else{
            url=Constants.baseUrl+page;
        }
        LogUtil.d("===="+url);
        ParsHtml parsHtml=GirlsJsoup.getParsHtml();
        parsHtml.getFpicAndUrl(url, new ParsHtml.ResponceInfo() {
            @Override
            public void getList(List<FPicBean> list) {
                //这里没有判断是否请求失败等
                LogUtil.d("===" + list.size());
                if(callBack!=null)
                callBack.onGirlsLoaded(list);
            }

            @Override
            public void getListFair() {

            }
        });

    }


    @Override
    public void getGirl(final LoadGirlsCallback callback) {
        getGirls(1, 1, callback);
    }


    @Override
    public void getGirlDetail(String url, final LoadInfoDetail loadcallback) {
        ParsHtml parsHtml=GirlsJsoup.getParsHtml();
        parsHtml.getGirlsDetail(url, new ParsHtml.ResponceInfoDetail() {
            @Override
            public void getList(PicBean picBean) {
                if(loadcallback!=null)
                {
                    loadcallback.getList(picBean);
                }
            }

            @Override
            public void getFailed() {
                if(loadcallback!=null){
                    loadcallback.getFailed();
                }
            }
        });
    }

}
