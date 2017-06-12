package coder.prettygirls.module;

import java.util.List;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;
import coder.prettygirls.data.bean.picbean.Category;

/**
 * Created by Administrator on 2017/6/12.
 */

public interface CategoryContact {
    interface View extends BaseView{
        void refresh(List<Category> categories);
        void loadMore(List<Category> categories);
        void showNomal();
        void showError();
    }
    interface Presenter extends BasePresenter{
        void getPic(int page,boolean isRefresh);
    }
}
