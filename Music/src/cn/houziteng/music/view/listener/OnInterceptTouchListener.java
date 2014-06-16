package cn.houziteng.music.view.listener;

import android.view.MotionEvent;
import android.view.View;

public interface OnInterceptTouchListener {

	public boolean onInterceptTouchEvent(View view, MotionEvent event);
}
