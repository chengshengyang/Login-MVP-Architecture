package com.github.mvp.main;

import com.github.mvp.BasePresenter;
import com.github.mvp.BaseView;
import com.github.mvp.data.RootEntity;
import com.github.mvp.data.StoriesEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public interface MainContract {

    interface Presenter extends BasePresenter {
        RootEntity getLatestNews();
        RootEntity getSafety();
        RootEntity getInterest();
        RootEntity getSport();
    }

    interface View extends BaseView<Presenter> {

        void setTitle();

        void refresh(List<StoriesEntity> list);
    }
}
