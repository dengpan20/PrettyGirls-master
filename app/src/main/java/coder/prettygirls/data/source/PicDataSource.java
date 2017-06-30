package coder.prettygirls.data.source;

import java.util.List;

import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.bean.picbean.Shop;
import coder.prettygirls.http.jsoup.GetInfoFromNet;

/**
 * Created by dengpan on 17/6/5.
 */

public interface PicDataSource {
    /**
     * 获取分类
     * @param size
     * @param complete
     */
    void getCate(String url ,int size, GetInfoFromNet.OnGetInfoComplete complete);

    /**
     * get item
     * @param size
     * @param complete
     */
    void getItem(Category category,int size, GetInfoFromNet.OnGetItemComplete complete);

    /**
     * get prod
     * @param size
     * @param complete
     */
    void getProd(Prod prod,int size, GetInfoFromNet.OnGetProdComplete complete);
    void getAllShop(int page,int pageSize,LoadShopCallBack callBack);
    void getShopBy(int page,int pageSize,LoadShopCallBack callBack);

    public interface LoadPicCate{
        void onSussess(List<Category> categories);
        void onFail();
    }
    public interface LoadPicProds{
        void onSussess(List<Prod> prods);
        void onFail();
    }
    public interface LoadPicProd{
        void onSussess(Prod prod);
        void onFail();
    }
    public interface LoadShopCallBack{
        void onSuccess(List<Shop> shops);
        void onFail();
    }
}
