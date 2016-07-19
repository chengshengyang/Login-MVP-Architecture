package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mvp.R;
import com.github.mvp.constants.TagStatic;
import com.github.mvp.data.StoriesEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class TodayFragment extends Fragment implements MainContract.View {

    @BindView(R.id.lv_news)
    ListView mListView;

    protected MainContract.Presenter mPresenter;
    private ZhiHuNewsAdapter mAdapter;
    private ArrayList<StoriesEntity> mStories = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_0, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new ZhiHuNewsAdapter(getContext());
        mListView.setAdapter(mAdapter);
        mPresenter.getLatestNews();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();//TODO
    }

    @Override
    public void setSelection(int tag) {

    }

    @Override
    public int getFragmentTag() {
        return TagStatic.TAG_FRAGMENT_TODAY;
    }

    @Override
    public void refresh(List<StoriesEntity> list) {
        mAdapter.setNewsList(list);
    }
}
