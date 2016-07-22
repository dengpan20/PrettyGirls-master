package coder.prettygirls.home;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.GirlsBean;

/**
 * adapter
 */
public class GirlsAdapter extends RecyclerArrayAdapter<FPicBean> {

    public GirlsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
//          return new GirlsViewHolder(parent);
            return new GirsFHtlmViewHolder(parent);
    }

}
