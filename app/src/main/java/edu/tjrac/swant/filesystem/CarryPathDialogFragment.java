package edu.tjrac.swant.filesystem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.yckj.baselib.util.StringUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018-08-24.
 */

@SuppressLint("ValidFragment")
public class CarryPathDialogFragment extends DialogFragment {

    @BindView(R.id.et_from) EditText mEtFrom;
    @BindView(R.id.et_to) EditText mEtTo;

    Unbinder unbinder;
    String from, to;

    public CarryPathDialogFragment(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置整理路径");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_carry_path, null);
        unbinder = ButterKnife.bind(this, view);
        mEtFrom.setText(from);
        mEtTo.setText(to);


        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String from = mEtFrom.getText().toString().trim();
                String to = mEtTo.getText().toString().trim();
                if (StringUtils.isEmpty(from) || StringUtils.isEmpty(to)) {

                } else {
                    if (from.equals(to)) {

                    } else {
                        File f_from = new File(from);
                        File f_to = new File(to);
                        if (f_from.exists() && f_from.isDirectory() && f_to.exists() && f_to.isDirectory()) {

                        }
                    }
                }
            }
        });
        builder.setNegativeButton("取消", null);
        return builder.create();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
