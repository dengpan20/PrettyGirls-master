package coder.prettygirls.data.bean.picbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dengpan on 17/4/18.
 */
public class Category implements Serializable {
    private String category_id;
    private String shop_id;//商店的id
    private String title;//分类的标题
    private String category_url;//分类的链接
    private boolean hasMore;// is has more
    private String moreUrl;//more prod url;
    private List<Prod> listprod;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_url() {
        return category_url;
    }

    public void setCategory_url(String category_url) {
        this.category_url = category_url;
    }

    public List<Prod> getListprod() {
        return listprod;
    }

    public void setListprod(List<Prod> listprod) {
        this.listprod = listprod;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getMoreUrl() {
        return moreUrl;
    }

    public void setMoreUrl(String moreUrl) {
        this.moreUrl = moreUrl;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", category_url='" + category_url + '\'' +
                ", listprod=" + listprod +
                '}';
    }
}
