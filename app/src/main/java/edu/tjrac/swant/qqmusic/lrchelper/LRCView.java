package edu.tjrac.swant.qqmusic.lrchelper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wpc on 2018/4/23.
 */

public class LRCView extends View {

    public LRCView(Context context) {
        this(context, null);
    }

    public LRCView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LRCView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        centTextPaint = new Paint();
        textPaint = new Paint();

        centTextPaint.setTextSize(accentTextSize);
        centTextPaint.setColor(accentTextColor);

        textPaint.setTextSize(defaultTextSize);
        textPaint.setColor(defaultTextColor);
    }

    public void setLrcInfo(LrcInfo lrcInfo) {
        this.lrcInfo = lrcInfo;
        invalidate();
    }

    //  高     宽    选择的文本大小 颜色            未选择的文本大小 颜色             文字间距
    int height, width,
            accentTextSize = 36, accentTextColor = 0xffffffff,
            defaultTextSize = 30, defaultTextColor = 0xff888888,
            textMagin = 20, centY, centX;
    int maxShowLines;

    Paint textPaint, centTextPaint;

    String currentLine;
    float currentLineLength;

    int index, focusIndex;
    long duration;
    float touchY, pre_offset,offset, d;

    LrcInfo lrcInfo;

    boolean showCentLine = false, scrolling;


    @Override
    protected void onDraw(Canvas canvas) {

        height = canvas.getHeight();
        width = canvas.getWidth();
        centY = height / 2;
        centX = width / 2;

        maxShowLines = (centY - accentTextSize / 2) / (defaultTextSize + textMagin) * 2 + 1;

        if (lrcInfo == null) {
            currentLine = "暂无歌词";
            currentLineLength = centTextPaint.measureText(currentLine);
            canvas.drawText(currentLine, centX - currentLineLength / 2,
                    centY + accentTextSize / 2, centTextPaint);
        } else {
            drawCentText(canvas);
            drawUpText(canvas);
            drawDownText(canvas);
            if (showCentLine) {
                drawCentLine(canvas);
            }
        }
        super.onDraw(canvas);
    }


    private void drawCentLine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0x88888888);
        paint.setStrokeWidth(1);

        canvas.drawLine(0, centY, width, centY, paint);
    }


    private Thread returnThread = new Thread() {
        @Override
        public void run() {
            super.run();
            try {
                sleep(1000);
                offset = 0;
                showCentLine = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("onTouch",event.getY()+"");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            pre_offset=offset;
            showCentLine = true;
//            if (touchY == 0f) {
                touchY = event.getY();
//            }
//            if (returnThread.isAlive()) {
//                returnThread.destroy();
//            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            offset =pre_offset+ event.getY() - touchY;

            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

//                returnThread.run();

        }

        return true;

    }

    private void drawDownText(Canvas canvas) {
        for (int i = 1; i < lrcInfo.getContents().size() - index; i++) {
            if (index + i < lrcInfo.getContents().size()) {
                float y = centY + (accentTextSize / 2)
                        + i * textMagin
                        + i * defaultTextSize
                        + offset;
                if (y > 0 && y < height) {
                    String content = lrcInfo.getContents().get(index + i);
                    canvas.drawText(content
                            , centX - textPaint.measureText(content) / 2,
                            y,
                            textPaint);
                }

            }

        }
    }

    private void drawUpText(Canvas canvas) {

        for (int i = 1; i < index; i++) {
            if (index - i >= 0) {
                float y = centY - (accentTextSize / 2) - i * textMagin - (i - 1) * defaultTextSize + offset;
                if (y > 0 && y < height) {
                    String content = lrcInfo.getContents().get(index - i);
                    canvas.drawText(content
                            , centX - textPaint.measureText(content) / 2,
                            y,
                            textPaint);
                }

            }
        }
    }

    private void drawCentText(Canvas canvas) {
        float y = centY + accentTextSize / 2 + offset;
        if (0 < y && y < height) {
            String content = lrcInfo.getContents().get(index);
            canvas.drawText(content, centX - centTextPaint.measureText(content) / 2,
                    y, centTextPaint);
        }
    }


    public void scrollTo(long time) {
        if(lrcInfo!=null){
            for (int i = 0; i < lrcInfo.getTimes().size() - 1; i++) {
                if (lrcInfo.getTimes().get(i) < time && lrcInfo.getTimes().get(i + 1) > time) {
                    index = i + 1;
                    scrollTo(index);
                }
            }
        }

    }

    public void scrollTo(int index) {
        this.index = index;
//        offset = 0;
        invalidate();
    }


    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }
    //    Timer mTimer=new Timer();

    public void setupWithMediaPlayer(MediaPlayer player) {
        duration = player.getDuration();
    }
}
