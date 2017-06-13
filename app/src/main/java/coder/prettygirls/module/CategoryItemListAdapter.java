package coder.prettygirls.module;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;

/**
 * Created by dengpan on 17/6/13.
 */

public class CategoryItemListAdapter extends RecyclerArrayAdapter<Prod> {
    public CategoryItemListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryItemListViewHolder(parent);
    }
}
