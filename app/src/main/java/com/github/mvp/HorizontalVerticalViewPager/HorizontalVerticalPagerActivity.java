package com.github.mvp.HorizontalVerticalViewPager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mvp.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalVerticalPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercital_pager);

        Button button = (Button) findViewById(R.id.buttonPanel);
        final HorizontalVerticalViewPager viewPager = (HorizontalVerticalViewPager) findViewById(R.id.viewpager);
        assert viewPager != null;
        viewPager.setPageMargin(80);
        viewPager.setOffscreenPageLimit(3);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.p001);
        list.add(R.drawable.p003);
        list.add(R.drawable.p001);
        list.add(R.drawable.p003);
        list.add(R.drawable.p001);
        list.add(R.drawable.p003);
        list.add(R.drawable.p001);
        list.add(R.drawable.p003);
        list.add(R.drawable.p001);
        list.add(R.drawable.p003);
        list.add(R.drawable.p001);
        list.add(R.drawable.p003);
        list.add(R.drawable.p001);
        list.add(R.drawable.p002);
        list.add(R.drawable.p001);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        list.add(R.drawable.p006);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        list.add(R.drawable.p006);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        list.add(R.drawable.p006);

        final HorizontalVerticalPagerAdapter adapter = new HorizontalVerticalPagerAdapter(this, list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(adapter);

        adapter.setOnTouchListener(new HorizontalVerticalPagerAdapter.OnTouchListener() {
            @Override
            public void onVerticalFling(int offsetPosition) {
                viewPager.setVertical(true);
                viewPager.setCurrentItem(adapter.getPosition() + offsetPosition);
            }

            @Override
            public void onHorizontalFling(int offsetPosition) {
                viewPager.setVertical(false);
                viewPager.setCurrentItem(adapter.getPosition() + offsetPosition);
            }
        });

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setVertical(!viewPager.isVertical());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
