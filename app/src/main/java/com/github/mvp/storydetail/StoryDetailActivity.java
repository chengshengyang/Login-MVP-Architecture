package com.github.mvp.storydetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.mvp.R;

public class StoryDetailActivity extends AppCompatActivity implements
        StoryDetailFragment.OnFragmentInteractionListener {

    private StoryDetailContract.Presenter mPresenter;
    private StoryDetailFragment mDetailFragment;

    private FragmentManager mFragmentManager;

    private int mStoryId;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        getExtra();

        mFragmentManager = getSupportFragmentManager();
        mDetailFragment = (StoryDetailFragment) mFragmentManager.findFragmentById(R.id.webView_fragment_content);
        if (null == mDetailFragment) {
            mDetailFragment = StoryDetailFragment.newInstance(mStoryId, mTitle);
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.webView_fragment_content, mDetailFragment);
        transaction.commitAllowingStateLoss();

        mPresenter = new StoryDetailPresenter(mDetailFragment);
    }

    private void getExtra() {
        if (getIntent().hasExtra(StoryDetailFragment.STORY_ID)) {
            mStoryId = getIntent().getIntExtra(StoryDetailFragment.STORY_ID, 0);
        }

        if (getIntent().hasExtra(StoryDetailFragment.STORY_TITLE)) {
            mTitle = getIntent().getStringExtra(StoryDetailFragment.STORY_TITLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
