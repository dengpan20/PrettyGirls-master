package coder.prettygirls.module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

public class ShowActivity extends AppActivity {
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
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
    }

    @Override
    public void onClick(View view) {

    }

}
