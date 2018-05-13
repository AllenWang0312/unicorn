package edu.tjrac.swant.bluetooth.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yckj.baselib.util.StringUtils;

import edu.tjrac.swant.bluetooth.BlueToothHelper;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/2 0002.
 */

public class BlueToothFlagsView extends View {


    public BlueToothFlagsView(Context context) {
        super(context);
    }

    public BlueToothFlagsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BlueToothFlagsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int mFlag;
    private boolean[] mFlags;
    String[] values;
    Paint textPaint;

    int padding = 32;
    int textSize = 32;

    @Override
    protected void onDraw(Canvas canvas) {
        values = getContext().getResources().getStringArray(R.array.bluetooth_service_type_flags);
        textPaint = new Paint();
        textPaint.setTextSize(textSize);

        canvas.drawText("00"
                        + Integer.toBinaryString(mFlag)
                        + " = 0x"
                        + StringUtils.getHexString(mFlag, 2)
                , padding, textSize + padding,
                textPaint
        );

        mFlags = BlueToothHelper.getFlags(mFlag);
        for (int i = 0; i < mFlags.length; i++) {

            if (mFlags[5 - i]) {
                textPaint.setColor(getContext().getResources().getColor(R.color.black));
                textPaint.setFakeBoldText(true);
            } else {
                textPaint.setColor(getContext().getResources().getColor(R.color.gray));
                textPaint.setFakeBoldText(false);
            }
            canvas.drawText(values[i], 100, 86 + i * 40, textPaint);

        }
        super.onDraw(canvas);
    }

    public void setFlag(int flag) {
        mFlag = flag;
        invalidate();
    }
}
