package edu.tjrac.swant.douyin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by wpc on 2018/3/2 0002.
 */

public class AnimateBottomNav extends ViewGroup {


    public AnimateBottomNav(Context context) {
        super(context);
    }

    public AnimateBottomNav(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimateBottomNav(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    interface  BottomNavItemType {

    }
}
