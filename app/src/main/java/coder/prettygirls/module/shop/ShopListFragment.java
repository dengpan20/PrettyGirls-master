package coder.prettygirls.module.shop;

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
import coder.prettygirls.data.bean.picbean.Shop;
import coder.prettygirls.module.CategoryAdapter;
import coder.prettygirls.module.CategoryPresenter;
import coder.prettygirls.module.ShowActivity;
import coder.prettygirls.util.SPUtil;
import coder.prettygirls.util.ToastUtil;


/**
 * Created by dengpan on 17/6/15.
 */

public class ShopListFragment extends BaseFragment implements ShopListContact.View,SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener{
    private Unbinder unbinder;
    @BindView(R.id.category_recycler_view)
    EasyRecyclerView mCategoryRecyclerView;
    @BindView(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;

    private View networkErrorView;

    private ArrayList<Shop> shops;

    private ShopListPresenter mPresenter;
    private ShopListAdapter mAdapter;
    private int page = 1;
    public static ShopListFragment getInstant(){
        ShopListFragment fragment = new ShopListFragment();
        return fragment;
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new ShopListPresenter(this);

        initRecyclerView();
        mPresenter.start();
    }
    private void initRecyclerView() {
        shops = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mCategoryRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new ShopListAdapter(getContext());
        mCategoryRecyclerView.setAdapterWithProgress(mAdapter);
        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SPUtil.put(mActivity,"url",shops.get(position).getShop_url());
                ((ShopActivity)getHoldingActivity()).finishActivity();
                ToastUtil.showLong(mActivity,"切换商店成功");
//                Intent intent = new Intent(mActivity, ShowActivity.class);
//                intent.putExtra("category",shops.get(position));
//                mActivity.startActivity(intent);
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
    public void refresh(List<Shop> list) {
        shops.clear();
        mAdapter.clear();
        shops.addAll(list);
        mAdapter.addAll(list);
    }

    @Override
    public void loadMore(List<Shop> list) {
        if(list.size()==0){
            stopMore();
        }
        shops.addAll(list);
        mAdapter.addAll(list);
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
    public void onRefresh() {
        page =1;
        mPresenter.start();
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getShopList(page,1);
    }
    public void stopMore(){
        mAdapter.stopMore();
    }
}
