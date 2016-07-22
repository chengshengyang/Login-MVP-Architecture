package com.github.mvp.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mvp.R;
import com.github.mvp.constants.TagStatic;
import com.github.mvp.widgets.TabItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_item_main_0)
    TabItem tabItemMain0;

    @BindView(R.id.tab_item_main_1)
    TabItem tabItemMain1;

    @BindView(R.id.tab_item_main_2)
    TabItem tabItemMain2;

    @BindView(R.id.tab_item_main_3)
    TabItem tabItemMain3;

    @BindView(R.id.tab_item_main_4)
    TabItem tabItemMain4;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingBar;

    private FragmentManager mFragmentManager;
    private TodayFragment mTodayFragment;
    private InterestFragment mInterestFragment;
    private SafetyFragment mSafetyFragment;
    private SportFragment mSportFragment;
    private OtherFragment mOtherFragment;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);

        mPresenter = new MainPresenter(getApplicationContext());

        tabItemMain0.performClick();
    }

    public void showFragment(int tag) {
        if (mFragmentManager != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            hideFragments();
            switch (tag) {
                case TagStatic.TAG_FRAGMENT_TODAY:
                    mTodayFragment = (TodayFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_TODAY + "");
                    if (mTodayFragment == null) {
                        mTodayFragment = new TodayFragment();
                        transaction.add(R.id.fragment_content, mTodayFragment, tag + "");
                    } else {
                        transaction.show(mTodayFragment);
                    }
                    mPresenter.setView(mTodayFragment);
                    break;

                case TagStatic.TAG_FRAGMENT_INTEREST:
                    mInterestFragment = (InterestFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_INTEREST + "");
                    if (mInterestFragment == null) {
                        mInterestFragment = new InterestFragment();
                        transaction.add(R.id.fragment_content, mInterestFragment, tag + "");
                    } else {
                        transaction.show(mInterestFragment);
                    }
                    mPresenter.setView(mInterestFragment);
                    break;

                case TagStatic.TAG_FRAGMENT_SAFETY:
                    mSafetyFragment = (SafetyFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SAFETY + "");
                    if (mSafetyFragment == null) {
                        mSafetyFragment = new SafetyFragment();
                        transaction.add(R.id.fragment_content, mSafetyFragment, tag + "");
                    } else {
                        transaction.show(mSafetyFragment);
                    }
                    mPresenter.setView(mSafetyFragment);
                    break;

                case TagStatic.TAG_FRAGMENT_SPORT:
                    mSportFragment = (SportFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SPORT + "");
                    if (mSportFragment == null) {
                        mSportFragment = new SportFragment();
                        transaction.add(R.id.fragment_content, mSportFragment, tag + "");
                    } else {
                        transaction.show(mSportFragment);
                    }
                    mPresenter.setView(mSportFragment);
                    break;

                case TagStatic.TAG_FRAGMENT_OTHER:
                    mOtherFragment = (OtherFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_OTHER + "");
                    if (mOtherFragment == null) {
                        mOtherFragment = new OtherFragment();
                        transaction.add(R.id.fragment_content, mOtherFragment, tag + "");
                    } else {
                        transaction.show(mOtherFragment);
                    }
                    mPresenter.setView(mOtherFragment);
                    break;
            }

            transaction.commitAllowingStateLoss();
        }
    }

    private void hideFragments() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mTodayFragment = (TodayFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_TODAY + "");
        if (mTodayFragment != null) {
            transaction.hide(mTodayFragment);
        }

        mInterestFragment = (InterestFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_INTEREST + "");
        if (mInterestFragment != null) {
            transaction.hide(mInterestFragment);
        }

        mSafetyFragment = (SafetyFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SAFETY + "");
        if (mSafetyFragment != null) {
            transaction.hide(mSafetyFragment);
        }

        mSportFragment = (SportFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SPORT + "");
        if (mSportFragment != null) {
            transaction.hide(mSportFragment);
        }

        mOtherFragment = (OtherFragment) mFragmentManager.findFragmentByTag(TagStatic.TAG_FRAGMENT_OTHER + "");
        if (mOtherFragment != null) {
            transaction.hide(mOtherFragment);
        }

        transaction.commitAllowingStateLoss();
    }

    @OnClick({R.id.tab_item_main_0, R.id.tab_item_main_1, R.id.tab_item_main_2,
            R.id.tab_item_main_3, R.id.tab_item_main_4, R.id.fab})
    public void onClick(View view) {
        clearChecked();
        switch (view.getId()) {
            case R.id.tab_item_main_0:
                tabItemMain0.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_TODAY);
                break;

            case R.id.tab_item_main_1:
                tabItemMain1.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_INTEREST);
                break;

            case R.id.tab_item_main_2:
                tabItemMain2.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_SAFETY);
                break;

            case R.id.tab_item_main_3:
                tabItemMain3.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_SPORT);
                break;

            case R.id.tab_item_main_4:
                tabItemMain4.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_OTHER);
                break;

            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                break;
        }
    }

    private void clearChecked() {
        tabItemMain0.setChecked(false);
        tabItemMain1.setChecked(false);
        tabItemMain2.setChecked(false);
        tabItemMain3.setChecked(false);
        tabItemMain4.setChecked(false);
    }
}
