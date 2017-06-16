package coder.prettygirls.module.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.data.bean.picbean.Category;
import coder.prettygirls.data.bean.picbean.Shop;
import coder.prettygirls.module.CategoryItemListFragment;

public class ShopActivity extends AppActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_show;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.category_fragment;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        ShopListFragment fragment = ShopListFragment.getInstant();
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
//        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        mToolbar.setTitle("店铺集合");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finishActivity();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
