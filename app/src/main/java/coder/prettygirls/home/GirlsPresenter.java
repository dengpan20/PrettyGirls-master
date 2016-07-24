package coder.prettygirls.home;

import android.util.Log;

import java.util.List;

import coder.prettygirls.app.Constants;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.bean.PicCategory;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsResponsitory;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsPresenter implements GirlsContract.Presenter {

    public static final String TAG = "GirlsPresenter";

    private GirlsContract.View mView;
    private GirlsResponsitory mGirlsResponsitory;

    public GirlsPresenter(GirlsContract.View view) {
        mView = view;
        mGirlsResponsitory = new GirlsResponsitory();
    }

    @Override
    public void start() {
//        getGirls(1, 20, true);
        getGrils(1,true, Constants.getCateGory().get(0));
    }

    @Override
    public void getGirls(int page, int size, final boolean isRefresh) {
        Log.d(TAG, "getGirls");
        mGirlsResponsitory.getGirls(page, size, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                if (isRefresh) {
                    mView.refresh(girlsBean.getResults());
                } else {
                    mView.load(girlsBean.getResults());
                }
                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                if (isRefresh) {
                    mView.showError();
                }
            }
        });
    }

    @Override
    public void getGrils(int page, final boolean isRefresh,PicCategory category) {
        mGirlsResponsitory.getGirl(page,category,new GirlsDataSource.LoadHtmlGirlCallBack(){

            @Override
            public void onGirlsLoaded(List<FPicBean> girlBeanlist) {
                if(isRefresh){
                    mView.refreshFpic(girlBeanlist);
                }else{
                    mView.loadFpic(girlBeanlist);
                }
                mView.showNormal();
            }

            @Override
            public void onDataNoteAva() {
                if (isRefresh) {
                    mView.showError();
                }
            }
        });
    }


}
