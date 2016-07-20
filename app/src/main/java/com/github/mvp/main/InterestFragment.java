package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mvp.R;
import com.github.mvp.constants.TagStatic;
import com.github.mvp.data.StoriesEntity;

import java.util.List;

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
    public int getFragmentTag() {
        return TagStatic.TAG_FRAGMENT_INTEREST;
    }

    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("不许无聊");
        }
    }
}
