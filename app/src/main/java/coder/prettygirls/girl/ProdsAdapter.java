package coder.prettygirls.girl;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import coder.prettygirls.R;
import coder.prettygirls.data.bean.PicBean;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.widget.PinchImageView;

/**
 * Created by dengpan on 17/6/8.
 */

public class ProdsAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Prod> mDatas;
    private LayoutInflater layoutInflater;
    private View mCurrentView;

    public ProdsAdapter(Context context, ArrayList<Prod> datas) {
        mContext = context;
        mDatas = datas;
        layoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas==null ? 0: mDatas.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final String imageUrl =mDatas.get(position).getProdImag_small();
        View view = layoutInflater.inflate(R.layout.item_girl_detail, container, false);
        PinchImageView imageView = (PinchImageView) view.findViewById(R.id.img);
        Glide.with(mContext)
                .load(imageUrl)
                .thumbnail(0.2f)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
