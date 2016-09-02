package com.github.mvp.HorizontalVerticalViewPager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.mvp.R;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class HorizontalVerticalViewPager extends ViewPager {

    private boolean isVertical = false;

    public HorizontalVerticalViewPager(Context context) {
        super(context);
        init();
    }

    public HorizontalVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs, 0);
        init();
    }

    public HorizontalVerticalViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initAttrs(attrs, defStyle);
        init();
    }

    private void initAttrs(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalVerticalViewPager, defStyle, 0);
        isVertical = a.getBoolean(R.styleable.HorizontalVerticalViewPager_isVertical, false);
        a.recycle();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new HorizontalVerticalPageTransformer());
        // The easiest way to get rid of the over scroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }


    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
        init();
    }

    private class HorizontalVerticalPageTransformer implements PageTransformer {

        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View page, float position) {
            if (isVertical) {

                if (position < -1) {
                    page.setAlpha(0);
                } else if (position <= 1) {
                    page.setAlpha(1);

                    // Counteract the default slide transition
                    float xPosition = page.getWidth() * -position;
                    page.setTranslationX(xPosition);

                    //set Y position to swipe in from top
                    float yPosition = position * page.getHeight();
                    page.setTranslationY(yPosition);
                } else {
                    page.setAlpha(0);
                }
            } else {
                int pageWidth = page.getWidth();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setAlpha(0);

                } else if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    page.setAlpha(1);
                    page.setTranslationX(0);
                    page.setScaleX(1);
                    page.setScaleY(1);

                } else if (position <= 1) { // (0,1]
                    // Fade the page out.
                    page.setAlpha(1 - position);

                    // Counteract the default slide transition
                    page.setTranslationX(pageWidth * -position);
                    page.setTranslationY(0);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.setAlpha(0);
                }
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isVertical) {
            boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
            swapXY(ev); // return touch coordinates to original reference frame for any child views
            return intercepted;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isVertical) {
            return super.onTouchEvent(swapXY(ev));
        } else {
            return super.onTouchEvent(ev);
        }
    }
}
