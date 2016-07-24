package coder.prettygirls.data.source;

import coder.prettygirls.data.bean.PicCategory;
import coder.prettygirls.data.source.local.LocalGirlsDataSource;
import coder.prettygirls.data.source.remote.RemoteGirlsDataSource;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsResponsitory implements GirlsDataSource ,GirlsDataSourceInterface {

    private LocalGirlsDataSource mLocalGirlsDataSource;
    private RemoteGirlsDataSource mRemoteGirlsDataSource;

    public GirlsResponsitory() {
        mLocalGirlsDataSource = new LocalGirlsDataSource();
        mRemoteGirlsDataSource = new RemoteGirlsDataSource();
    }

    @Override
    public void getGirl(int page, LoadHtmlGirlCallBack callBack) {
        mRemoteGirlsDataSource.getGirl(page,callBack);
    }
    @Override
    public void getGirl(int page,PicCategory category,LoadHtmlGirlCallBack callBack) {
        mRemoteGirlsDataSource.getGirl(page,category,callBack);
    }
    @Override
    public void getGirls(int page, int size, LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirls(page, size, callback);
    }

    @Override
    public void getGirls(int page, LoadHtmlGirlCallBack callBack) {
        mRemoteGirlsDataSource.getGirl(page,callBack);
    }

    @Override
    public void getGirl(LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirl(callback);
    }

    @Override
    public void getGirlDetail(String url,LoadInfoDetail callback) {
        mRemoteGirlsDataSource.getGirlDetail(url,callback);
    }
}
