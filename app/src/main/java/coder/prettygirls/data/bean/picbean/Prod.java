package coder.prettygirls.data.bean.picbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dengpan on 17/4/18.
 */
public class Prod implements Serializable,Parcelable {
    private String prod_id;
    private String prodName;//产品名字
    private String prodUrl;//产品相册链接
    private String prodImag_small;//小图链接
    private int picSize;//图片张数
    private List<String> piclist;//相册图片集合
    private int getHtmlTime;//get html time
    private String category_id;
    private String shop_id;
    public Prod(){

    }
    protected Prod(Parcel in) {
        prod_id = in.readString();
        prodName = in.readString();
        prodUrl = in.readString();
        prodImag_small = in.readString();
        picSize = in.readInt();
        piclist = in.createStringArrayList();
        getHtmlTime = in.readInt();
        category_id = in.readString();
        shop_id = in.readString();
    }

    public static final Creator<Prod> CREATOR = new Creator<Prod>() {
        @Override
        public Prod createFromParcel(Parcel in) {
            return new Prod(in);
        }

        @Override
        public Prod[] newArray(int size) {
            return new Prod[size];
        }
    };

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdUrl() {
        return prodUrl;
    }

    public void setProdUrl(String prodUrl) {
        this.prodUrl = prodUrl;
    }

    public String getProdImag_small() {
        return prodImag_small;
    }

    public void setProdImag_small(String prodImag_small) {
        this.prodImag_small = prodImag_small;
    }

    public int getPicSize() {
        return picSize;
    }

    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }

    public List<String> getPiclist() {
        return piclist;
    }

    public void setPiclist(List<String> piclist) {
        this.piclist = piclist;
    }

    @Override
    public String toString() {
        return "prod{" +
                "prodName='" + prodName + '\'' +
                ", prodUrl='" + prodUrl + '\'' +
                ", prodImag_small='" + prodImag_small + '\'' +
                ", picSize=" + picSize +
                '}';
    }

    public int getGetHtmlTime() {
        return getHtmlTime;
    }

    public void setGetHtmlTime(int getHtmlTime) {
        this.getHtmlTime = getHtmlTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prod_id);
        dest.writeString(prodName);
        dest.writeString(prodUrl);
        dest.writeString(prodImag_small);
        dest.writeInt(picSize);
        dest.writeStringList(piclist);
        dest.writeInt(getHtmlTime);
        dest.writeString(category_id);
        dest.writeString(shop_id);
    }
}
