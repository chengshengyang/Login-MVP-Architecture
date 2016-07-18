package com.github.mvp.main;

import android.support.v4.app.FragmentManager;

import com.github.mvp.BasePresenter;
import com.github.mvp.BaseView;
import com.github.mvp.data.RootEntity;
import com.github.mvp.data.StoryDetailsEntity;

import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public interface MainContract {

    interface Presenter extends BasePresenter {
        RootEntity getLatestNews();
        RootEntity getSafety();
        RootEntity getInterest();
        RootEntity getSport();
        StoryDetailsEntity getNewsDetail(@Path("id") int id);
    }

    interface View extends BaseView<Presenter> {
        void clearFragment(FragmentManager fragmentManager);
        void setSelection(int tag);

        void showFragment(FragmentManager fragmentManager, BaseFragment fragment);
        void hideFragment(FragmentManager fragmentManager, BaseFragment fragment);
    }
}
