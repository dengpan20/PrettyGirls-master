package coder.prettygirls.girl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.app.Constants;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.bean.PicBean;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Pic;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsDataSourceInterface;
import coder.prettygirls.data.source.GirlsResponsitory;
import coder.prettygirls.data.source.PicDataSource;
import coder.prettygirls.data.source.PicResponsitory;
import coder.prettygirls.util.BitmapUtil;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.widget.PinchImageView;

/**
 * Created by oracleen on 2016/7/4.
 */
public class GirlFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.rootView)
    LinearLayout mRootView;
//    private GirlAdapter mAdapter;
    private ProdsAdapter prodsAdapter;

    private ArrayList<GirlsBean.ResultsEntity> datas;
    private ArrayList<PicBean.ImagesBean> data;
    private ArrayList<String> pics;
    private PicBean picBean;
    private int current;
//    private FGirlAdapter mDAdapter;

    private Unbinder unbinder;

    private OnGirlChange mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public interface OnGirlChange {
        void change(int color);
    }

//    public static GirlFragment newInstance(ArrayList<Parcelable> datas, int current) {
//        Bundle bundle = new Bundle();
//        GirlFragment fragment = new GirlFragment();
//        bundle.putParcelableArrayList("girls", datas);
//        bundle.getString("url");
////        bundle.putInt("current", current);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
    public static GirlFragment newInstance(Prod prod) {
        Bundle bundle = new Bundle();
        GirlFragment fragment = new GirlFragment();
//        bundle.putParcelableArrayList("girls", datas);
//        bundle.getString("url");
//        bundle.putInt("current", current);
//        bundle.putString("url",url);
        bundle.putSerializable("prod",prod);
//        bundle.putParcelable("prod",prod);
//        LogUtil.d(GirlFragment.class.getSimpleName(),url);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnGirlChange) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        data=new ArrayList<>();
        pics= new ArrayList<>();
        prodsAdapter = new ProdsAdapter(mActivity,pics);
//        mDAdapter=new FGirlAdapter(mActivity,data);
//        mAdapter = new GirlAdapter(mActivity, datas);
        mViewPager.setAdapter(prodsAdapter);
//        mViewPager.setCurrentItem(current);
        mViewPager.setOnPageChangeListener(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
//            datas = bundle.getParcelableArrayList("girls");
//            current = bundle.getInt("current");
            String url=bundle.getString("url");
            Prod prod = (Prod) bundle.getSerializable("prod");
//            PicBean picBean=null;
            new PicResponsitory().getProd(prod, 1, new PicDataSource.LoadPicProd() {
                @Override
                public void onSussess(Prod prod) {
                    pics.addAll(prod.getPiclist());
                    prodsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail() {

                }
            });
//            new GirlsResponsitory().getGirlDetail(url, new GirlsDataSourceInterface.LoadInfoDetail() {
//                @Override
//                public void getList(PicBean picBean) {
////                    data= (ArrayList<PicBean.ImagesBean>) picBean.getImages();
//                    data.addAll((ArrayList<PicBean.ImagesBean>) picBean.getImages());
//                    mDAdapter.notifyDataSetChanged();
//
//                }
//
//                @Override
//                public void getFailed() {
//                    data=new ArrayList<>();
//                }
//            });
        }else{
//            datas=new ArrayList<>();
//            data=new ArrayList<>();
//            datas= new  GirlsDataSource().getGirlDetail();
            //获取
        }
//
//        mDAdapter=new FGirlAdapter(mActivity,data);
////        mAdapter = new GirlAdapter(mActivity, datas);
//        mViewPager.setAdapter(mDAdapter);
////        mViewPager.setCurrentItem(current);
//        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getColor();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 根据图片获得主题色
     */
    private void getColor() {
        PinchImageView imageView = getCurrentImageView();
        if(imageView == null){
            return;
        }
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
//                Palette.Swatch vir = palette.getLightMutedSwatch();
                Palette.Swatch vir = palette.getVibrantSwatch();
                if (vir == null)
                    return;
                mListener.change(vir.getRgb());
            }
        });
    }

    public void saveGirl() {
        String imgUrl = data.get(mViewPager.getCurrentItem()).getImage_url();
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        boolean isSuccess = BitmapUtil.saveBitmap(bitmap, Constants.dir, imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length()), true);
        if (isSuccess) {
            Snackbar.make(mRootView, "大爷，下载好了呢~", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(mRootView, "大爷，下载出错了哦~", Snackbar.LENGTH_LONG).show();
        }
    }

    public void shareGirl() {
        PinchImageView imageView = getCurrentImageView();
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
            boolean isSuccess = BitmapUtil.saveBitmap(bitmap, Constants.dir, "share.jpg", false);
            if (isSuccess) {
                //由文件得到uri
                Uri imageUri = Uri.fromFile(new File(Constants.dir + "/share.jpg"));
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "分享MeiZhi到"));
            } else {
                Snackbar.make(mRootView, "大爷，分享出错了哦~", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private PinchImageView getCurrentImageView() {
        View currentItem = prodsAdapter.getPrimaryItem();
        if (currentItem == null) {
            return null;
        }
        PinchImageView imageView = (PinchImageView) currentItem.findViewById(R.id.img);
        if (imageView == null) {
            return null;
        }
        return imageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
