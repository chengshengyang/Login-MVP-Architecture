package com.github.mvp.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mvp.R;
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

    private FragmentManager mFragmentManager;
    private TodayFragment mTodayFragment;
    private InterestFragment mInterestFragment;
    private SportFragment mSportFragment;
    private SafetyFragment mSafetyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabItemMain0.performClick();
        mFragmentManager = getSupportFragmentManager();
    }

    @OnClick({R.id.tab_item_main_0, R.id.tab_item_main_1, R.id.tab_item_main_2,
            R.id.tab_item_main_3, R.id.tab_item_main_4})
    public void onClick(View view) {
        clearChecked();
        switch (view.getId()) {
            case R.id.tab_item_main_0:
                tabItemMain0.setChecked(true);
                break;

            case R.id.tab_item_main_1:
                tabItemMain1.setChecked(true);
                break;

            case R.id.tab_item_main_2:
                tabItemMain2.setChecked(true);
                break;

            case R.id.tab_item_main_3:
                tabItemMain3.setChecked(true);
                break;

            case R.id.tab_item_main_4:
                tabItemMain4.setChecked(true);
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
