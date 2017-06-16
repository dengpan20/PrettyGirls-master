package coder.prettygirls.module.shop;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import coder.prettygirls.data.bean.picbean.Shop;

/**
 * Created by dengpan on 17/6/15.
 */

public class ShopListAdapter extends RecyclerArrayAdapter<Shop> {
    public ShopListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopListViewHolder(parent);
    }

}
