package coder.prettygirls.http;

import java.util.List;

import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.bean.picbean.ResponseData;
import coder.prettygirls.data.bean.picbean.Shop;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dengpan on 17/6/16.
 */

public interface YupooService {
    @GET("ServletGetShop")
    Observable<List<Shop>> getAllShop();
    @GET("ServletGetShop")
    Observable<ResponseData> getShopBy(@Query("page") String page, @Query("pageSize") String pageSize);
}
