package coder.prettygirls.data.bean.picbean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/22.
 */
public class Pic implements Serializable {
    private int id;
    private String prod_id;
    private String pic_url;
    private String pic_local_url;

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPic_local_url() {
        return pic_local_url;
    }

    public void setPic_local_url(String pic_local_url) {
        this.pic_local_url = pic_local_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pic{" +
                "prod_id='" + prod_id + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", pic_local_url='" + pic_local_url + '\'' +
                '}';
    }

}
