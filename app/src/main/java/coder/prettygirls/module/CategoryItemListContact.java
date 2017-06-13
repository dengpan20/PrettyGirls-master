package coder.prettygirls.module;

import java.util.List;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;

/**
 * Created by dengpan on 17/6/13.
 */

public interface CategoryItemListContact {
    interface View extends BaseView{
        void refresh(List<Prod> prods);
        void loadMore(List<Prod> prods);
        void showNomal();
        void showError();
    }
    interface Presenter extends BasePresenter{
        void getProds(Category category,int page,boolean isRefresh);
    }
}
