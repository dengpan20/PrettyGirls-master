package coder.prettygirls.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;

import coder.prettygirls.R;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.util.LogUtil;

/**
 * Created by dengpan616@qq.com on 2016/7/17.
 */
public class GirsFHtlmViewHolder extends BaseViewHolder<FPicBean> {
    private ImageView image;
    public GirsFHtlmViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_girl);
        image = $(R.id.girl_img);
    }

    @Override
    public void setData(FPicBean data) {
        super.setData(data);
        LogUtil.d("===Girl" + data.getImgUrl());
        LogUtil.d("==="+image);
        Glide.with(getContext())
                .load(data.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);

    }
}
