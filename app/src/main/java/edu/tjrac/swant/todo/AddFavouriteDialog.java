package edu.tjrac.swant.todo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.yckj.baselib.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/4/27.
 */

@SuppressLint("ValidFragment")
public class AddFavouriteDialog extends DialogFragment {

    @BindView(R.id.tv_style) TextView style;

    @BindView(R.id.sp_types) Spinner mSpTypes;
    @BindView(R.id.et_tags) EditText mEtTags;
    Unbinder unbinder;

    String content;

    @SuppressLint("ValidFragment")
    public AddFavouriteDialog(String content) {
        this.content = content;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        R.array.favourite_type;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_favourite, null);
        unbinder = ButterKnife.bind(this, view);
        style.setText(content);

        //http://f.amap.com/1RdOb_02A2Vyc gaode
        //https://j.map.baidu.com/8VeMP
        if (content.contains("map")) {
            mSpTypes.setSelection(0);

        } else if (StringUtils.isMobileNO(content)) {
            mSpTypes.setSelection(1);

        } else if (content.contains("http://")) {
            mSpTypes.setSelection(2);

        } else if (StringUtils.isEmail(content)) {
            mSpTypes.setSelection(3);

        } else if (content.contains("")) {

        }

        mEtTags.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (mSpTypes.getSelectedItemPosition() == 1) {
//                    style.setCompoundDrawables(getResources().getDrawable(R.drawable.location));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setView(view);
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
