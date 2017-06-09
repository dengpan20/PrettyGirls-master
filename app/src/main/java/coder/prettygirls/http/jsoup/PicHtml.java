package coder.prettygirls.http.jsoup;

import android.os.Handler;
import android.os.Message;

import java.util.List;

import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.PicBean;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.source.PicDataSource;

/**
 * Created by Administrator on 2017/6/5.
 */

public class PicHtml {
    private ResponceInfo responceInfo;
    private ResponceItem responceItem;
    private ResponceProd responceProd;

    public void setResponceInfo(ResponceInfo responceInfo) {
        this.responceInfo = responceInfo;
    }

    public void setResponceProd(ResponceProd responceProd) {
        this.responceProd = responceProd;
    }

    public void setResponceItem(ResponceItem responceItem) {
        this.responceItem = responceItem;
    }

    public void getCate(final String url, final int size, ResponceInfo responceInfo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetInfoFromNet.startCate(url, size, new GetInfoFromNet.OnGetInfoComplete() {
                    @Override
                    public void onComplete(List<Category> categories) {
                        Message msg = Message.obtain();
                        msg.obj = categories;
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                });
            }
        });
    }

    public void getItem(final Category category, final int size, ResponceItem responceItem) {
        new Thread(new Runnable() {
            @Override
            public void run() {
              GetInfoFromNet.startItem(category, size, new GetInfoFromNet.OnGetItemComplete() {
                  @Override
                  public void Complete(List<Prod> prods) {
                      Message msg = Message.obtain();
                      msg.obj = prods;
                      msg.what = 2;
                      handler.sendMessage(msg);
                  }
              });
            }
        });
    }

    public void getProd(final Prod prod, final int size, ResponceProd responceProd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               GetInfoFromNet.startProd(prod,new GetInfoFromNet.OnGetProdComplete(){

                   @Override
                   public void Complete(Prod prod) {
                       Message msg = Message.obtain();
                       msg.obj = prod;
                       msg.what = 3;
                       handler.sendMessage(msg);
                   }
               });
            }
        });
    }


    public interface ResponceInfo{
        void getList(List<Category> list);
        void getListFair();
    }
    public interface ResponceItem{
        void getList(List<Prod> listProd);
        void getListFair();
    }
    public interface ResponceProd{
        void getList(Prod prod);
        void getListFair();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(responceInfo!=null)
                        responceInfo.getList((List<Category>) msg.obj);
                    break;
                case 2:
                    if(responceItem!= null){
                        responceItem.getList((List<Prod>) msg.obj);
                    }
                    break;
                case 3:
                    if(responceProd!=null){
                        responceProd.getList((Prod) msg.obj);
                    }
                    break;
            }

        }
    };
}
