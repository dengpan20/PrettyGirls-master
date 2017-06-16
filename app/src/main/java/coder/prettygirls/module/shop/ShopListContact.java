package coder.prettygirls.module.shop;

import java.util.List;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;
import coder.prettygirls.data.bean.picbean.Shop;
import coder.prettygirls.data.source.PicDataSource;

/**
 * Created by dengpan on 17/6/15.
 */

public interface ShopListContact {
    interface View extends BaseView<Shop>{
        void refresh(List<Shop> list);
        void loadMore(List<Shop> list);
        void showNomal();
        void showError();
    }
    interface Presenter extends BasePresenter{
       void getShopList(int page, int pageSize);
    }
}
