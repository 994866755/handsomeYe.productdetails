package com.example.kylinarm.productdetails.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.kylinarm.productdetails.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by kylinARM on 2017/11/13.
 */

public class KylinScrollView extends PullToRefreshBase <NestedScrollView>{

    private NestedScrollView mScrollView;
    private FrameLayout flContent;

    public KylinScrollView(Context context) {
        super(context);
    }

    public KylinScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KylinScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected NestedScrollView createRefreshableView(Context context, AttributeSet attrs) {
        mScrollView = (NestedScrollView) LayoutInflater.from(context).inflate(R.layout.layout_kylin_scrollview,null);
        flContent = (FrameLayout) mScrollView.findViewById(R.id.fl_content);
        return mScrollView;
    }

    public void addView(View view){
        flContent.addView(view);
    }

    public NestedScrollView getScrollView() {
        return mScrollView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return mScrollView.getScrollY() <= 0;
    }

}
