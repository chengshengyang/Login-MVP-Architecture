package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mvp.data.RootEntity;
import com.github.mvp.data.StoriesEntity;
import com.github.mvp.login.LoginContract;

import java.util.List;

import rx.Observable;


/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class BaseFragment extends Fragment implements MainContract.View {

    private String baseUrl="http://news-at.zhihu.com";//baseUrl一定要设为这个
    protected MainContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    protected void loadData(Observable<RootEntity> rootEntityObservable, final ListView listView) {

    }

    @Override
    public void setSelection(int tag) {

    }

    @Override
    public int getFragmentTag() {
        return 0;
    }

    @Override
    public void refresh(List<StoriesEntity> list) {

    }
}
