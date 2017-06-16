package coder.prettygirls.module;

import android.content.Context;

import java.util.List;

import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseActivity;
import coder.prettygirls.app.Constants;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.source.PicDataSource;
import coder.prettygirls.data.source.PicResponsitory;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.util.SPUtil;

/**
 * Created by Administrator on 2017/6/12.
 */

public class CategoryPresenter implements CategoryContact.Presenter{
    CategoryContact.View view ;
    private BaseActivity context;

    public CategoryPresenter(CategoryContact.View view, BaseActivity appActivity){
        this.view = view;
        context = appActivity;
    }
    @Override
    public void getCate(int page, final boolean isRefresh) {
        String url = (String) SPUtil.get(context,"url",Constants.SHOP_ADDRESS);
        new PicResponsitory().getCate(url, page, new PicDataSource.LoadPicCate() {
            @Override
            public void onSussess(List<Category> categories) {
                if(categories!= null && categories.size()>0){
                    if(isRefresh){
                        view.refresh(categories);
                    }else{
                        view.loadMore(categories);
                    }

                }
                view.showNomal();

            }

            @Override
            public void onFail() {
                view.showNomal();

            }
        });
    }

    @Override
    public void start() {
        getCate(1,true);
    }
}
