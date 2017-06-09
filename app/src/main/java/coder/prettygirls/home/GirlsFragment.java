package coder.prettygirls.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

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
import coder.prettygirls.data.bean.PicCategory;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.girl.GirlActivity;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.util.ToastUtil;

/**
 * Created by oracleen on 2016/6/21.
 */
public class GirlsFragment extends BaseFragment implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    public static final String TAG = "GirlsFragment";

    @BindView(R.id.girls_recycler_view)
    EasyRecyclerView mGirlsRecyclerView;
    @BindView(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;

    private View networkErrorView;

    private ArrayList<GirlsBean.ResultsEntity> data;
    private ArrayList<FPicBean > datas;
    private ArrayList<Prod> prods;
    private GirlsAdapter mAdapter;

    private GirlsPresenter mPresenter;
    private int page = 1;
    private int size = 20;

    private Unbinder unbinder;
    private PicCategory  category= Constants.getCateGory().get(0);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static GirlsFragment getInstance() {
        GirlsFragment mainFragment = new GirlsFragment();
        return mainFragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        mPresenter = new GirlsPresenter(this);

        initRecyclerView();

        //初始化数据
        mPresenter.start();
    }

    private void initRecyclerView() {
        data = new ArrayList<>();
        datas=new ArrayList<>();
        prods = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGirlsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new GirlsAdapter(getContext());

        mGirlsRecyclerView.setAdapterWithProgress(mAdapter);

        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(mActivity, GirlActivity.class);
//                intent.putParcelableArrayListExtra("girls", datas);
//                intent.putExtra("current", position);
//                startActivity(intent);
                ToastUtil.showLong(getContext(),"12");
            }
        });

        mGirlsRecyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
//        mPresenter.getGirls(1, size, true);
//        mPresenter.getGrils(1, true,category);
        mPresenter.getPic(1,true);
        page = 1;
    }
    public void onRefresh(PicCategory category) {
//        mPresenter.getGirls(1, size, true);
//        this.category=category;
//        mPresenter.getGrils(1,true,category);
        mPresenter.getPic(1,true);
        page = 1;
    }

    @Override
    public void onLoadMore() {
//        if (datas.size() % 39 == 0) {
//            LogUtil.d(TAG, "onloadmore");
//            page++;
////            mPresenter.getGirls(page, size, false);
//            mPresenter.getGrils(page,false,category);
//        }
        mPresenter.getPic(1,true);
    }

    @Override
    public void refresh(List<GirlsBean.ResultsEntity> datas) {
//        data.clear();
//        data.addAll(datas);
//        mAdapter.clear();
//        mAdapter.addAll(datas);
        LogUtil.d("===refresh"+datas.size());
    }

    @Override
    public void load(List<GirlsBean.ResultsEntity> datas) {
//        data.addAll(datas);
//        mAdapter.addAll(datas);
        LogUtil.d("===load"+datas.size());
    }

    @Override
    public void showError() {
        mGirlsRecyclerView.showError();

        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.VISIBLE);
            return;
        }

        networkErrorView = mNetworkErrorLayout.inflate();
    }

    public void showNormal() {
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void refreshFpic(List<FPicBean> girlBeanlist) {
        datas.clear();
        datas.addAll(girlBeanlist);
        mAdapter.clear();
//        mAdapter.addAll(girlBeanlist);
    }

    @Override
    public void loadFpic(List<FPicBean> girlBeanlist) {
        datas.addAll(girlBeanlist);
//        mAdapter.addAll(girlBeanlist);
    }

    @Override
    public void loadPic(List<Prod> prods) {
//        prods.addAll(prods);
        mAdapter.addAll(prods);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
