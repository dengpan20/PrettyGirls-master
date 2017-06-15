package coder.prettygirls.module;

import java.util.List;

import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.source.PicDataSource;
import coder.prettygirls.data.source.PicResponsitory;

/**
 * Created by dengpan on 17/6/13.
 */

public class CategoryItemListPresenter implements CategoryItemListContact.Presenter {
    CategoryItemListContact.View view ;
    public CategoryItemListPresenter(CategoryItemListContact.View view){
        this.view = view;
    }
    @Override
    public void getProds(Category category, int page, final boolean isRefresh) {
        new PicResponsitory().getItem(category, page, new PicDataSource.LoadPicProds() {
            @Override
            public void onSussess(List<Prod> prods) {
                if(prods.size()>0) {
                    if (isRefresh) {
                        view.refresh(prods);
                    } else {
                        view.loadMore(prods);
                    }
                    view.showNomal();
                }else{
                    view.showNomal();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void start() {
        getProds(category,1,true);
    }
    private Category category;
    public void setCategory(Category category){
        this.category = category;
    }
}
