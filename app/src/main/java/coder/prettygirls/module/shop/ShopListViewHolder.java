package coder.prettygirls.module.shop;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import coder.prettygirls.R;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Shop;

/**
 * Created by dengpan on 17/6/15.
 */

public class ShopListViewHolder extends BaseViewHolder<Shop> {
    private ImageView img;
    private TextView tv_info;
    public ShopListViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.item_category_list);
        img = $(R.id.img_category);
        tv_info = $(R.id.tv_category_info);

    }

    @Override
    public void setData(Shop data) {
        super.setData(data);
        tv_info.setText(data.getShop_url());
//        Glide.with(getContext())
//                .load(data.getListprod())
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(image);
    }
}
