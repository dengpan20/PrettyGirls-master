package coder.prettygirls.data.source.remote;

import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.source.PicDataSource;
import coder.prettygirls.http.jsoup.GetInfoFromNet;

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
}
