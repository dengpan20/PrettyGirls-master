package coder.prettygirls.http;

import coder.prettygirls.app.Constants;
import coder.prettygirls.app.MyApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dengpan on 17/6/16.
 */

public class YupooRetrofit {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (GirlsRetrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_ADDRESS)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(MyApplication.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
