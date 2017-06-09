package coder.prettygirls.home;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import coder.prettygirls.R;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.util.LogUtil;

/**
 * Created by dengpan616@qq.com on 2016/7/17.
 */
public class PicViewHolder extends BaseViewHolder<Prod> {
    private ImageView image;
    public PicViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_girl);
        image = $(R.id.girl_img);
    }

    @Override
    public void setData(Prod data) {
        super.setData(data);
        LogUtil.d("===Girl" + data.getProdImag_small());
        LogUtil.d("==="+image);
        Glide.with(getContext())
                .load(data.getProdImag_small())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);

    }
}
