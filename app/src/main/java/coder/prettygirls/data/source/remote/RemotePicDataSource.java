package coder.prettygirls.data.source.remote;

import java.util.List;

import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.bean.picbean.ResponseData;
import coder.prettygirls.data.bean.picbean.Shop;
import coder.prettygirls.data.source.PicDataSource;
import coder.prettygirls.http.GirlsRetrofit;
import coder.prettygirls.http.GirlsService;
import coder.prettygirls.http.YupooRetrofit;
import coder.prettygirls.http.YupooService;
import coder.prettygirls.http.jsoup.GetInfoFromNet;
import coder.prettygirls.util.YupooPicUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dengpan on 17/6/5.
 */

public class RemotePicDataSource implements PicDataSource {

    @Override
    public void getCate(String url, int size, GetInfoFromNet.OnGetInfoComplete complete) {

    }

    @Override
    public void getItem(Category category, int size, GetInfoFromNet.OnGetItemComplete complete) {

    }

    @Override
    public void getProd(Prod prod, int size, GetInfoFromNet.OnGetProdComplete complete) {

    }

    @Override
    public void getAllShop(int page, int pageSize, final LoadShopCallBack callBack) {
        YupooRetrofit.getRetrofit()
                .create(YupooService.class)
                .getAllShop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Shop>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail();
                    }

                    @Override
                    public void onNext(List<Shop> list) {
                        callBack.onSuccess(list);
                    }
                });
    }

    @Override
    public void getShopBy(int page, int pageSize, final LoadShopCallBack callBack) {
        YupooRetrofit.getRetrofit()
                .create(YupooService.class)
                .getShopBy(page+"",pageSize+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail();
                    }

                    @Override
                    public void onNext(ResponseData responseData) {
                        if(responseData.getCode()!= 0000){
                            callBack.onFail();
                            return;
                        }
                        callBack.onSuccess(responseData.getData().getShopList());
                    }


                });
    }
}
