package edu.tjrac.swant.opengl

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.colorpicker.*

/**
 * Created by wpc on 2018/4/20.
 */

@SuppressLint("ValidFragment")
class ColorPicker : DialogFragment(), SeekBar.OnSeekBarChangeListener {

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("颜色选择器")

        val v = LayoutInflater.from(activity).inflate(R.layout.colorpicker, null)


        sb_r.setProgress(255)
        sb_g.setProgress(255)
        sb_b.setProgress(255)
        sb_a.setProgress(255)

        sb_r.setOnSeekBarChangeListener(this)
        sb_g.setOnSeekBarChangeListener(this)
        sb_b.setOnSeekBarChangeListener(this)
        sb_a.setOnSeekBarChangeListener(this)
        builder.setView(v)
        builder.setPositiveButton("确定", mPositive)

        return builder.create()
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }


    var color: Int = 0
        internal set

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        color = caculateColor()
        tv_color.setBackgroundColor(color)

        tv_color.setText(color.toString() + "")
        if (Integer.MAX_VALUE / 2 > color) {
            tv_color.setTextColor(color - Integer.MAX_VALUE / 2)
        } else {
            tv_color.setTextColor(color + Integer.MAX_VALUE / 2)
        }

    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    internal fun caculateColor(): Int {
        return sb_a.getProgress() shl 12 + sb_r.getProgress() shl 8 + sb_g.getProgress() shl 4 + sb_b.getProgress()
    }

    internal var mPositive: DialogInterface.OnClickListener? = null

    fun setPositive(positive: DialogInterface.OnClickListener) {
        mPositive = positive
    }
}
