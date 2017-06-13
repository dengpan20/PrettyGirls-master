package coder.prettygirls.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Prod;
import coder.prettygirls.girl.GirlActivity;
import coder.prettygirls.util.ToastUtil;

/**
 * Created by dengpan on 17/6/13.
 */

public class CategoryItemListFragment extends BaseFragment implements CategoryItemListContact.View ,SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener{
    private Unbinder unbinder;
    @BindView(R.id.category_recycler_view)
    EasyRecyclerView mCategoryRecyclerView;
    @BindView(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;

    private ArrayList<Prod> prods ;
    private CategoryItemListAdapter mAdapter;
    
    private CategoryItemListPresenter mPresenter;

    private View networkErrorView;
    private Category category;

    private int page =1;
    public static CategoryItemListFragment getInstant(Category category){
        Bundle bundle = new Bundle();
        CategoryItemListFragment categoryItemListFragment = new CategoryItemListFragment();
        bundle.putSerializable("category",category);
        categoryItemListFragment.setArguments(bundle);
        return categoryItemListFragment;
    }
    @Override
    public void refresh(List<Prod> prods) {
        this.prods.clear();
        mAdapter.clear();
        this.prods.addAll(prods);
        mAdapter.addAll(prods);
    }

    @Override
    public void loadMore(List<Prod> prods) {
        this.prods.addAll(prods);
        mAdapter.addAll(prods);
    }

    @Override
    public void showNomal() {
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
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
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new CategoryItemListPresenter(this);
        initRecyclerView();
        // TODO 接收category 参数  设置 category
        Bundle bundle = getArguments();
        category = (Category) bundle.getSerializable("category");
        mPresenter.setCategory(category);
        mPresenter.start();
    }

    private void initRecyclerView() {
        prods = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mCategoryRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new CategoryItemListAdapter(getContext());
        mCategoryRecyclerView.setAdapterWithProgress(mAdapter);
        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mActivity, GirlActivity.class);
//                Intent intent = new Intent(mActivity, ShowActivity.class);
                intent.putExtra("prod", (Serializable) prods.get(position));
                mActivity.startActivity(intent);
                ToastUtil.showLong(getContext(),"12");
            }
        });

        mCategoryRecyclerView.setRefreshListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_list;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mPresenter.start();
        page =1;
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getProds(category,page,false);
    }
}
