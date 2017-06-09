package coder.prettygirls.data.source;

import java.util.List;

import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.http.PicJsoup;
import coder.prettygirls.http.jsoup.GetInfoFromNet;
import coder.prettygirls.http.jsoup.ParsHtml;
import coder.prettygirls.http.jsoup.PicHtml;

/**
 * Created by dengpan on 17/6/7.
 */

public class PicResponsitory {

    public PicResponsitory(){

    }
    public void getCate(String url, int size, final PicDataSource.LoadPicCate callback) {
        PicJsoup.getParsHtml().getCate(url, size, new PicHtml.ResponceInfo() {
            @Override
            public void getList(List<Category> list) {
                callback.onSussess(list);
            }

            @Override
            public void getListFair() {

            }
        });
    }

    public void getItem(Category category, int size, final PicDataSource.LoadPicProds callback) {
        PicJsoup.getParsHtml().getItem(category, size, new PicHtml.ResponceItem() {
            @Override
            public void getList(List<Prod> listProd) {
                callback.onSussess(listProd);
            }

            @Override
            public void getListFair() {

            }
        });
    }

    public void getProd(Prod prod, int size, final PicDataSource.LoadPicProd callback) {
        PicJsoup.getParsHtml().getProd(prod, size, new PicHtml.ResponceProd() {
            @Override
            public void getList(Prod prod) {
                callback.onSussess(prod);
            }

            @Override
            public void getListFair() {

            }
        });
    }
}
