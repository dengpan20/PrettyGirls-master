package coder.prettygirls.module;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import coder.prettygirls.data.bean.picbean.Category;

/**
 * Created by dengpan on 17/6/13.
 */

public class CategoryAdapter extends RecyclerArrayAdapter<Category> {
    public CategoryAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(parent);
    }
}
