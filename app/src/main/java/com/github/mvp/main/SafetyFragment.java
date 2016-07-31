package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mvp.R;
import com.github.mvp.constants.TagStatic;

/**
 * Created by chengshengyang on 2016/7/18.
 */
public class SafetyFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getSafety();
    }

    @Override
    public int getFragmentTag() {
        return TagStatic.TAG_FRAGMENT_SAFETY;
    }

    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("互联网安全");
        }
    }
}
