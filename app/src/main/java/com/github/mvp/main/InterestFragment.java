package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class InterestFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getInterest();
    }

    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("不许无聊");
        }
    }
}
