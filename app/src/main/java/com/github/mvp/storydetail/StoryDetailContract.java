package com.github.mvp.storydetail;

import com.github.mvp.BasePresenter;
import com.github.mvp.BaseView;
import com.github.mvp.data.StoryDetailsEntity;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface StoryDetailContract {

    interface Presenter extends BasePresenter {
        StoryDetailsEntity getNewsDetail(int id);
    }

    interface View extends BaseView<Presenter> {
        void loadWebView(String s);
    }
}
