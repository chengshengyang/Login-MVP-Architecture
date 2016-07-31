package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("互联网安全");
        }
    }
}
