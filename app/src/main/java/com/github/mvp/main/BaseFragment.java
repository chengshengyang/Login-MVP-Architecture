package com.github.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mvp.data.RootEntity;

import rx.Observable;


/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class BaseFragment extends Fragment implements MainContract.View {

    private String baseUrl="http://news-at.zhihu.com";//baseUrl一定要设为这个

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void loadData(Observable<RootEntity> rootEntityObservable, final ListView listView) {

    }

    @Override
    public void clearFragment(FragmentManager fragmentManager) {

    }

    @Override
    public void setSelection(int tag) {

    }

    @Override
    public void showFragment(FragmentManager fragmentManager, BaseFragment fragment) {

    }

    @Override
    public void hideFragment(FragmentManager fragmentManager, BaseFragment fragment) {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}
