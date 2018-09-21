package edu.tjrac.swant.unicorn.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by wpc on 2018-08-03.
 */

@SuppressLint("AppCompatCustomView")
public class StateTextView extends TextView {


    public final static int STATE_TRUE = 1;
    public final static int STATE_FALSE = -1;

    public final static int STATE_BOTH = 0;

    public final static int STATE_DEFAULT = Integer.MAX_VALUE;
    public final static int STATE_DEFAULT2 = Integer.MAX_VALUE-1;

    public final static int STATE_DEFAULT_CHECKED = 1;
    public final static int STATE_DEFAULT_CHECKED2 = 2;

    public StateTextView(Context context) {
        super(context);
    }

    public StateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    HashMap<Integer, Integer> states;

    public void addState(int state, int backResId) {
        if (states == null) {
            states = new HashMap<>();
        }

        states.put(state, backResId);
    }

    public void setState(int state) {
        setBackgroundResource(states.get(state));
    }
}
