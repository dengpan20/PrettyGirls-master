package coder.prettygirls.http.jsoup;

import android.content.pm.FeatureGroupInfo;
import android.os.Handler;
import android.os.Message;
import android.widget.Switch;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import coder.prettygirls.app.Constants;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.PicBean;
import coder.prettygirls.util.LogUtil;

/**
 * Created by dengpan616@qq.com on 2016/7/17.
 */
public class ParsHtml {
//    String pic="9179";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(responceInfo!=null)
                        responceInfo.getList((List<FPicBean>) msg.obj);
                    break;
                case 2:
                    if(responceInfoDetail!=null){
                        responceInfoDetail.getList((PicBean) msg.obj);
                    }
                    break;
                case 3:
                    if(responceInfoDetail!=null){
                        responceInfoDetail.getFailed();
                    }
                    break;
            }

        }
    };
    public interface ResponceInfo{
        void getList(List<FPicBean> list);
        void getListFair();
    }
    public interface ResponceInfoDetail{
        void getList(PicBean picBean);
        void getFailed();
    }
    private  interface  ResId{
        void getId(String id);
    }
    private ResponceInfo responceInfo;
    private ResponceInfoDetail responceInfoDetail;

    public void getFpicAndUrl(final String srcurl, final ResponceInfo responceInfo){
        LogUtil.d("====ParsHtml"+srcurl);
        this.responceInfo=responceInfo;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<FPicBean> list=new ArrayList<FPicBean>();
                    Document document = Jsoup.connect(srcurl).get();
                    Elements select = document.select("div.list-box");

                    for (int i = 0; i < select.size(); i++) {
                        FPicBean picBean=new FPicBean();
                        Element element = select.get(i);//每个list-box;
                        Elements elementsATag = element.getElementsByTag("a");

                        System.out.println(elementsATag.size());
                        for (int j = 0; j < elementsATag.size(); j++) {
                            if(j==0){

                                String imgurl=elementsATag.get(j).select("img").attr("data-original")	;
                                String title=elementsATag.get(j).attr("title");
                                String url=elementsATag.get(j).attr("href");
                                String ss2=title.replaceAll("<font.*?>([\\s\\S]*)</font>", "诱惑");
//						String ss3=ss2.replaceAll("(\\<\\w+\\s*)[^\\>]*", "");
                                try {
//                            DownloadImg.download(imgurl, ss2+".jpg", "D:\\img\\诱惑");
                                    picBean.setImgUrl(imgurl);
                                    picBean.setTitle(title);
                                    picBean.setUrl(url);
                                    list.add(picBean);

//                                    responceInfo.getList(list);
                                } catch (Exception e) {
                                    System.out.println(title);
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Message message=new Message();
                    message.what=1;
                    message.obj=list;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        responceInfo.getListFair();
//        return null;

    }
    public void getGirlsDetail(final String url, final ResponceInfoDetail responceInfoDetail){
        this.responceInfoDetail=responceInfoDetail;
        getId(url, new ResId() {
            @Override
            public void getId(String id) {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(Constants.baseDetailUrl + id)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                        handler.sendEmptyMessage(3);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String res = response.body().string();
                        String json = res.split(" = ")[1];
                        LogUtil.e("ParsHtml", json);
                        Gson gson = new Gson();
                        PicBean fromJson = gson.fromJson(json, PicBean.class);
//                responceInfoDetail.getList(fromJson);
                        Message message = new Message();
                        message.what = 2;
                        message.obj = fromJson;
                        handler.sendMessage(message);
                    }
                });
            }
        });


    }

    /**
     * 获取页面的id
     */
    public void getId(final String url, final ResId resId){
        //http://www.fydzv.com/api.php?op=get_imgs&id=9137
        LogUtil.e("ParsHtml",url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document;
                try {
                    document = Jsoup.connect(url).get();
                    Elements select = document.select("script");
                    System.out.println("这里打印的是script大小="+select.size());
                    for (int i = 0; i < select.size(); i++) {
                        if(i==2){
                            String text = select.get(i).data();
                            text=text.split("=")[1];
                            text=text.split(";")[0];
                            System.out.println(text);
                            if(resId!=null){
                                resId.getId(text);
                            }
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
