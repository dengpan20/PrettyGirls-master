package coder.prettygirls.module;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import coder.prettygirls.R;
import coder.prettygirls.data.bean.picbean.Category;

/**
 * Created by dengpan on 17/6/13.
 */

public class CategoryViewHolder extends BaseViewHolder<Category> {
    private ImageView img;
    private TextView tv_info;
    public CategoryViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.item_category_list);
        img = $(R.id.img_category);
        tv_info = $(R.id.tv_category_info);

    }

    @Override
    public void setData(Category data) {
        super.setData(data);
        tv_info.setText(data.getTitle());
//        Glide.with(getContext())
//                .load(data.getListprod())
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(image);
    }
}
