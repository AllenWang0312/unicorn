package edu.tjrac.swant.opengl;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/4/20.
 */

@SuppressLint("ValidFragment")
public class ColorPicker extends DialogFragment implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.tv_color) TextView tv_color;
    @BindView(R.id.sb_r) SeekBar mSbR;

    @BindView(R.id.sb_g) SeekBar mSbG;
    @BindView(R.id.sb_b) SeekBar mSbB;
    @BindView(R.id.sb_a) SeekBar mSbA;



    Unbinder unbinder;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("颜色选择器");

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.colorpicker, null);
        unbinder = ButterKnife.bind(this, v);

        mSbR.setProgress(255);
        mSbG.setProgress(255);
        mSbB.setProgress(255);
        mSbA.setProgress(255);

        mSbR.setOnSeekBarChangeListener(this);
        mSbG.setOnSeekBarChangeListener(this);
        mSbB.setOnSeekBarChangeListener(this);
        mSbA.setOnSeekBarChangeListener(this);
        builder.setView(v);
        builder.setPositiveButton("确定",mPositive);

        return builder.create();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    int color;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        color = caculateColor();
        tv_color.setBackgroundColor(color);

        tv_color.setText(color + "");
        if (Integer.MAX_VALUE / 2 > color) {
            tv_color.setTextColor(color - Integer.MAX_VALUE / 2);
        } else {
            tv_color.setTextColor(color + Integer.MAX_VALUE / 2);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    int caculateColor() {
        return mSbA.getProgress() << 12 +
                mSbR.getProgress() << 8 +
                mSbG.getProgress() << 4 +
                mSbB.getProgress();
    }

    public int getColor() {
        return color;
    }

    DialogInterface.OnClickListener mPositive;

    public void setPositive(DialogInterface.OnClickListener positive) {
        mPositive = positive;
    }
}
