package edu.tjrac.swant.douyin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by wpc on 2018/3/2 0002.
 */

public class DouYinTabLayout extends ViewGroup {


    public DouYinTabLayout(Context context) {
        super(context);
    }

    public DouYinTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouYinTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    int position;
    float present;
    int childsize = -1;

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            present = positionOffset;
            invalidate();
        }

        @Override
        public void onPageSelected(int position) {
            position = mViewPager.getCurrentItem();
            if (childsize == -1) {
                childsize = mViewPager.getChildCount();
                indicater_width = width / childsize;
            }
            invalidate();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    ViewPager mViewPager;

   public void setUpWithViewPager(ViewPager vp) {
        mViewPager = vp;
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
    }

    int indicate_color = 0xface15;

    int width, height, indicater_width;
    Paint indicaterPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();
        if (indicater_width != 0) {
            indicaterPaint = new Paint();
            indicaterPaint.setColor(indicate_color);
            canvas.drawOval((float) indicater_width * position + present * indicater_width,
                    0,
                    (float) indicater_width * (position + 1) + present * indicater_width,
                    height, indicaterPaint);
        }

        super.onDraw(canvas);
    }


}
