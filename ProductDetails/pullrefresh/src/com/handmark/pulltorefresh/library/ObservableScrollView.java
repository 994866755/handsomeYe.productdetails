package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {

	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		if (mCallbacks != null) {
			mCallbacks.onScrollChanged(t);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (mCallbacks != null) {
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				mCallbacks.onDownMotionEvent();
				break;

			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_CANCEL:
				mCallbacks.onUpOrCancelMotionEvent();
				break;
			}
		}
		return super.onTouchEvent(ev);
	}

	
	@Override
	protected int computeVerticalScrollRange() {
		// TODO Auto-generated method stub
		return super.computeVerticalScrollRange();
	}

	public interface Callbacks {
		void onScrollChanged(int scrollY);

		void onDownMotionEvent();

		void onUpOrCancelMotionEvent();
	}

	private Callbacks mCallbacks;

	public void setCallbacks(Callbacks listener) {
		mCallbacks = listener;
	}

}
