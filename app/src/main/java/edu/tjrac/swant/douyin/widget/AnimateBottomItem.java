package edu.tjrac.swant.douyin.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wpc on 2018/3/2 0002.
 */

@SuppressLint("AppCompatCustomView")
public class AnimateBottomItem extends TextView implements Selectedable{

    public AnimateBottomItem(Context context) {
        super(context);
    }
    public AnimateBottomItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public AnimateBottomItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    boolean selected;

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void select() {
        selected=true;
//        (AnimateBottomNav)getParent()
    }

    @Override
    public void unSelected() {

    }

    @Override
    public void selectAnimate() {

    }

    @Override
    public void unselectAnimate() {

    }

//    defaultBrageDrawable

    int bradge_color =0xff0000;
    int bradge_r =10;

    public void popupBradge(){

    }

    public void dismissBradge(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
