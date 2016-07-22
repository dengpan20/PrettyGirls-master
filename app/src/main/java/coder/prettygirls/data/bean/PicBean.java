package coder.prettygirls.data.bean;

import java.util.List;

/**
 * Created by dengpan616@qq.com on 2016/7/15.
 */
public class PicBean {

    /**
     * title : 俏皮性感猫女郎挑逗宅男们
     * createtime : 2016-02-14 14:29
     * click : http://www.fydzv.com/pic/0214913H016.html
     * like : 0
     * url : http://www.fydzv.com/pic/0214913H016.html
     */

    private SlideBean slide;
    /**
     * interface :
     * title : 推女神苏糯米Mi撩人酥胸私房照
     * url : http://www.fydzv.com/pic/021491352016.html
     * thumb_50 : http://mmzl.leanapp.cn/uploadfile/2016/0214/20160214015235485.jpg
     */

    private NextAlbumBean next_album;
    /**
     * interface :
     * title : 这是第一个图集
     * url : #no
     * thumb_50 : http://mmzl.leanapp.cn/uploadfile/2016/0214/20160214015235485.jpg
     */

    private PrevAlbumBean prev_album;
    /**
     * title :
     * intro :
     * comment :
     * width :
     * height :
     * thumb_50 : http://ac-apnbvacb.clouddn.com/1d5f882d1c3dc41526d4.jpg
     * thumb_160 : http://ac-apnbvacb.clouddn.com/1d5f882d1c3dc41526d4.jpg
     * image_url : http://ac-apnbvacb.clouddn.com/1d5f882d1c3dc41526d4.jpg
     * createtime :
     * source :
     * id : 1
     */

    private List<ImagesBean> images;

    public SlideBean getSlide() {
        return slide;
    }

    public void setSlide(SlideBean slide) {
        this.slide = slide;
    }

    public NextAlbumBean getNext_album() {
        return next_album;
    }

    public void setNext_album(NextAlbumBean next_album) {
        this.next_album = next_album;
    }

    public PrevAlbumBean getPrev_album() {
        return prev_album;
    }

    public void setPrev_album(PrevAlbumBean prev_album) {
        this.prev_album = prev_album;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class SlideBean {
        private String title;
        private String createtime;
        private String click;
        private String like;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public String getLike() {
            return like;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class NextAlbumBean {
        @com.google.gson.annotations.SerializedName("interface")
        private String interfaceX;
        private String title;
        private String url;
        private String thumb_50;

        public String getInterfaceX() {
            return interfaceX;
        }

        public void setInterfaceX(String interfaceX) {
            this.interfaceX = interfaceX;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumb_50() {
            return thumb_50;
        }

        public void setThumb_50(String thumb_50) {
            this.thumb_50 = thumb_50;
        }
    }

    public static class PrevAlbumBean {
        @com.google.gson.annotations.SerializedName("interface")
        private String interfaceX;
        private String title;
        private String url;
        private String thumb_50;

        public String getInterfaceX() {
            return interfaceX;
        }

        public void setInterfaceX(String interfaceX) {
            this.interfaceX = interfaceX;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumb_50() {
            return thumb_50;
        }

        public void setThumb_50(String thumb_50) {
            this.thumb_50 = thumb_50;
        }
    }

    public static class ImagesBean {
        private String title;
        private String intro;
        private String comment;
        private String width;
        private String height;
        private String thumb_50;
        private String thumb_160;
        private String image_url;
        private String createtime;
        private String source;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getThumb_50() {
            return thumb_50;
        }

        public void setThumb_50(String thumb_50) {
            this.thumb_50 = thumb_50;
        }

        public String getThumb_160() {
            return thumb_160;
        }

        public void setThumb_160(String thumb_160) {
            this.thumb_160 = thumb_160;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
