package edu.tjrac.swant.todo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

import com.yckj.baselib.util.StringUtils

import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.dialog_add_favourite.*

/**
 * Created by wpc on 2018/4/27.
 */

@SuppressLint("ValidFragment")
class AddFavouriteDialog @SuppressLint("ValidFragment")
constructor(internal var content: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        //        R.array.favourite_type;
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("添加")
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_favourite, null)
        tv_style.setText(content)

        //http://f.amap.com/1RdOb_02A2Vyc gaode
        //https://j.map.baidu.com/8VeMP
        if (content.contains("map")) {
            sp_types.setSelection(0)

        } else if (StringUtils.isMobileNO(content)) {
            sp_types.setSelection(1)

        } else if (content.contains("http://")) {
            sp_types.setSelection(2)

        } else if (StringUtils.isEmail(content)) {
            sp_types.setSelection(3)

        } else if (content.contains("")) {

        }

        et_tags.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (sp_types.getSelectedItemPosition() === 1) {
                    //                    tv_stype.setCompoundDrawables(getResources().getDrawable(R.drawable.location));
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        builder.setView(view)
        builder.setPositiveButton("添加") { dialog, which -> }
        return super.onCreateDialog(savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}
