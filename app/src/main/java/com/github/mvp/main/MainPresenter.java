package com.github.mvp.main;

import android.content.Context;

import com.github.mvp.data.RootEntity;
import com.github.mvp.data.StoryDetailsEntity;
import com.github.mvp.data.source.UserRepository;

import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class MainPresenter implements MainContract.Presenter {

    //private final UserRepository mUserRepository;
    private MainContract.View mMainView;
    private Context mContext;

    public MainPresenter(Context context) {
        this.mContext = context;
    }

    public void setView(MainContract.View view) {
        this.mMainView = view;
        mMainView.setPresenter(this);
    }

    @Override
    public RootEntity getLatestNews() {
        return null;
    }

    @Override
    public RootEntity getSafety() {
        return null;
    }

    @Override
    public RootEntity getInterest() {
        return null;
    }

    @Override
    public RootEntity getSport() {
        return null;
    }

    @Override
    public StoryDetailsEntity getNewsDetail(@Path("id") int id) {
        return null;
    }

    @Override
    public void start() {

    }
}
