package com.github.mvp.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class TabItem extends FrameLayout implements Checkable {

    @BindView(R.id.tab_item_label)
    TextView tabItemLabel;

    @BindView(R.id.tab_item_count)
    TextView tabItemMsgCount;

    private Context mContext;
    private View rootView;

    private boolean isChecked = false;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        initViews();
        initXMLParams(attrs);
    }

    private void initViews() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.view_tab_item, this, true);
        ButterKnife.bind(this, rootView);
    }

    private  void initXMLParams(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.TabItem);

        Drawable background = array.getDrawable(R.styleable.TabItem_android_src);
        if (null != background) {
            background.setBounds(0, 0, background.getMinimumWidth(), background.getMinimumHeight());
            tabItemLabel.setCompoundDrawables(null, background, null, null);
        }

        String label_Str = array.getString(R.styleable.TabItem_tab_item_label);
        tabItemLabel.setText(label_Str);

        int iCount = array.getInt(R.styleable.TabItem_tab_item_count, 0);
        if (iCount != 0) {
            tabItemMsgCount.setText(String.valueOf(iCount));
        } else {
            tabItemMsgCount.setVisibility(View.GONE);
        }
    }

    public void setMsgCount(int msgCount) {
        if (msgCount != 0) {
            tabItemMsgCount.setVisibility(View.VISIBLE);
            tabItemMsgCount.setText(String.valueOf(msgCount));
        } else {
            tabItemMsgCount.setVisibility(View.GONE);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            tabItemLabel.setSelected(isChecked);
            setSelected(isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
