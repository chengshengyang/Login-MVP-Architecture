package com.github.mvp.HorizontalVerticalViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class HorizontalVerticalPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    public static final int FLING_MIN_DISTANCE = 20;
    private List<Integer> list;
    private Context context;
    private OnTouchListener mOnTouchListener;
    private int mPosition = 0;

    public HorizontalVerticalPagerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(list.get(position));

        final GestureDetector detector = new GestureDetector(context, new GestureListener());
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPosition(position);
        Log.i(getClass().getName(), "position-----" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class GestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(getClass().getName(), "onDown-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(getClass().getName(), "onShowPress-----" + getActionName(e.getAction()));
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(getClass().getName(), "onSingleTapUp-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(getClass().getName(), "onScroll-----"
                    + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                    + e2.getX() + "," + e2.getY() + ")");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(getClass().getName(), "onLongPress-----" + getActionName(e.getAction()));
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(getClass().getName(),
                    "onFling-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                            + e2.getX() + "," + e2.getY() + ")");

            float detly_x = e1.getX() - e2.getX();
            float detly_y = e1.getY() - e2.getY();

            if (Math.abs(detly_x) > Math.abs(detly_y)) {// 水平方向滑动
                int offsetPosition = 0;
                if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE) {
                    offsetPosition = 1;
                    Log.i(getClass().getName(), "onFling----- 向左滑动");
                } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE) {
                    offsetPosition = -1;
                    Log.i(getClass().getName(), "onFling----- 向右滑动");
                }
                mOnTouchListener.onHorizontalFling(offsetPosition);
            } else {// 垂直方向滑动
                int offsetPosition = 0;
                if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE) {
                    offsetPosition = 1;
                    Log.i(getClass().getName(), "onFling----- 向上滑动");
                } else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE) {
                    offsetPosition = -1;
                    Log.i(getClass().getName(), "onFling----- 向下滑动");
                }
                mOnTouchListener.onVerticalFling(offsetPosition);
            }

            return false;
        }

        private String getActionName(int action) {
            String name = "";
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    name = "ACTION_DOWN";
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    name = "ACTION_MOVE";
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    name = "ACTION_UP";
                    break;
                }
                default:
                    break;
            }
            return name;
        }
    }

    /**
     * 注意，这个是自定义的OnTouchListener，不是view的
     */
    public interface OnTouchListener {
        void onVerticalFling(int offsetPosition);

        void onHorizontalFling(int offsetPosition);
    }
}
