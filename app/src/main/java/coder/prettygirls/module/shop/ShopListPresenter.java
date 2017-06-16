package coder.prettygirls.module.shop;

import java.util.List;

import coder.prettygirls.data.bean.picbean.Shop;
import coder.prettygirls.data.source.PicDataSource;
import coder.prettygirls.data.source.PicResponsitory;

/**
 * Created by dengpan on 17/6/15.
 */

public class ShopListPresenter implements ShopListContact.Presenter {
    ShopListContact.View view;
    private PicResponsitory picResponsitory;
    public ShopListPresenter(ShopListContact.View view){
        this.view =view;
        picResponsitory= new PicResponsitory();
    }


    @Override
    public void start() {
        getShopList(1,1);
    }

    @Override
    public void getShopList(final int page, int pageSize) {
        picResponsitory.getAllShop(page, pageSize, new PicDataSource.LoadShopCallBack() {
            @Override
            public void onSuccess(List<Shop> shops) {
                if(page==1){
                 view.refresh(shops);
                }else {
                    view.loadMore(shops);
                }
                view.showNomal();
            }

            @Override
            public void onFail() {
                view.showNomal();
            }
        });
    }
}
