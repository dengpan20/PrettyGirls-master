package coder.prettygirls.data.bean;

/**
 * Created by dengpan616@qq.com on 2016/7/23.
 */
public class PicCategory {
    private String id;
    private String name;

    public PicCategory(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
