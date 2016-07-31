package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mvp.constants.TagStatic;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class OtherFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getLatestNews();
    }

    @Override
    public int getFragmentTag() {
        return TagStatic.TAG_FRAGMENT_OTHER;
    }

    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("其他");
        }
    }
}
