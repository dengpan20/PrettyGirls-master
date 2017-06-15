package coder.prettygirls.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.girl.GirlActivity;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.util.ToastUtil;

/**
 * Created by Administrator on 2017/6/12.
 */

public class CategoryFragment extends BaseFragment implements CategoryContact.View ,SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener{
    private Unbinder unbinder;
    @BindView(R.id.category_recycler_view)
    EasyRecyclerView mCategoryRecyclerView;
    @BindView(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;

    private View networkErrorView;

    private ArrayList<Category> categories;

    private CategoryPresenter mPresenter;
    private CategoryAdapter mAdapter;
    private int page = 1;
    public static CategoryFragment getInstant(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new CategoryPresenter(this);

        initRecyclerView();
        mPresenter.start();
    }

    private void initRecyclerView() {
        categories = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mCategoryRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new CategoryAdapter(getContext());
        mCategoryRecyclerView.setAdapterWithProgress(mAdapter);
        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mActivity, ShowActivity.class);
                intent.putExtra("category",categories.get(position));
                mActivity.startActivity(intent);
//                ToastUtil.showLong(getContext(),"12");
            }
        });

        mCategoryRecyclerView.setRefreshListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_list;
    }

    @Override
    public void refresh(List<Category> categories) {
        mAdapter.clear();
        this.categories.clear();
        this.categories.addAll(categories);
        mAdapter.addAll(this.categories);
    }

    @Override
    public void loadMore(List<Category> categories) {
        this.categories.addAll(categories);
        mAdapter.addAll(categories);
    }

    @Override
    public void showNomal() {
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
        }
        if(page>1){
            stopMore();
        }
    }

    @Override
    public void showError() {
        mCategoryRecyclerView.showError();
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.VISIBLE);
            return;
        }

        networkErrorView = mNetworkErrorLayout.inflate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        page =1;
        mPresenter.start();
    }

    @Override
    public void onLoadMore() {
        page++;
        LogUtil.e("page=="+page);
        mPresenter.getCate(page,false);
    }
    public void stopMore(){
        mAdapter.stopMore();
    }
}
