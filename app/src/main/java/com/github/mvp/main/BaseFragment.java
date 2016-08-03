package com.github.mvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mvp.R;
import com.github.mvp.data.StoriesEntity;
import com.github.mvp.storydetail.StoryDetailActivity;
import com.github.mvp.storydetail.StoryDetailFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class BaseFragment extends Fragment implements MainContract.View {

    @BindView(R.id.lv_news)
    ListView mListView;

    protected MainContract.Presenter mPresenter;
    protected ActionBar mActionBar;
    private ZhiHuNewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_0, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new ZhiHuNewsAdapter(getContext());
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            mActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            setTitle();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void refresh(final List<StoriesEntity> list) {
        mAdapter.setNewsList(list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                intent.putExtra(StoryDetailFragment.STORY_ID, list.get(position).getId());
                intent.putExtra(StoryDetailFragment.STORY_TITLE, list.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle(R.string.app_name);
        }
    }
}
