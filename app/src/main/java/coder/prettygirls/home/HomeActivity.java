package coder.prettygirls.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.about.AboutActivity;
import coder.prettygirls.app.Constants;
import coder.prettygirls.data.bean.PicCategory;
import coder.prettygirls.module.CategoryFragment;
import coder.prettygirls.module.shop.ShopActivity;
import coder.prettygirls.widget.MyGridView;

public class HomeActivity extends AppActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private long exitTime = 0;
    private View view;

    MyGridView myGridView;
    private MyAdapter adapter;
    BottomDialog bottomDialog = null;
//    GirlsFragment instance;
    CategoryFragment categoryFragment;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girls_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        initBottomView();
    }

    private void initBottomView() {
        view=View.inflate(this,R.layout.layout_bottom_view,null);
//        ButterKnife.bind(this,view);
        myGridView= (MyGridView) view.findViewById(R.id.mygrid_view);
        adapter=new MyAdapter(this, Constants.getCateGory());
        myGridView.setAdapter(adapter);
        myGridView.setOnItemClickListener(this);
    }

    @OnClick(R.id.fab)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                // 必须明确使用mailto前缀来修饰邮件地址,如果使用
//                Uri uri = Uri.parse("mailto:18231195685@sina.cn");
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                startActivity(Intent.createChooser(intent, "请选择邮件类应用"));

                if(bottomDialog==null) {
                    bottomDialog = new BottomDialog.Builder(this)
                            .setTitle("选择分类")
                            .setCustomView(view)
                            .build();
                }
                bottomDialog.show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_shop_list){
            Intent intent = new Intent(HomeActivity.this, ShopActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected BaseFragment getFirstFragment() {
//        instance = GirlsFragment.getInstance();
        categoryFragment = CategoryFragment.getInstant();
        return categoryFragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(mFab, "再按一次退出程序哦~", Snackbar.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PicCategory item = (PicCategory) adapter.getItem(position);
//        instance.onRefresh(item);
        mToolbar.setTitle(item.getName());
        bottomDialog.dismiss();
    }
}
