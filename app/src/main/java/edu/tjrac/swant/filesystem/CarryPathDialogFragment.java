package edu.tjrac.swant.filesystem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.yckj.baselib.common.base.BaseApplication;
import com.yckj.baselib.util.StringUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

import static edu.tjrac.swant.filesystem.Config.SP.CARRAY_JSON;

/**
 * Created by wpc on 2018-08-24.
 */

@SuppressLint("ValidFragment")
public class CarryPathDialogFragment extends DialogFragment {

    @BindView(R.id.et_from) TextInputEditText mEtFrom;
    @BindView(R.id.et_to) TextInputEditText mEtTo;

    Unbinder unbinder;
    String from, to;
    public static CarrySetting setting;

    public static SharedPreferences sp;

    public CarryPathDialogFragment(String from, String to) {
        this.from = from;
        this.to = to;
        initCarrySetting();
    }

    public static void initCarrySetting() {
        if (sp == null) {
            sp = BaseApplication.getInstance().getSharedPreferences(Config.SP.setting, Context.MODE_PRIVATE);
        }
        String carry = sp.getString(CARRAY_JSON, "");
        Log.i(CARRAY_JSON, carry);
        setting = new Gson().fromJson(carry, CarrySetting.class);
    }

    public class CarrySetting {
        ArrayList<CarryInfo> maps;

        public boolean fromContain(String absolutePath) {
            if (maps != null && maps.size() > 0) {
                for (CarryInfo item : maps) {
                    if (item.from.equals(absolutePath)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean toContain(String absolutePath) {
            if (maps != null && maps.size() > 0) {
                for (CarryInfo item : maps) {
                    if (item.to.equals(absolutePath)) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    class CarryInfo {
        String from;
        String to;
        int options;//0 剪切

        public CarryInfo(String from, String to) {
            this.from = from;
            this.to = to;
        }
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


                            if (setting.maps == null) {
                                setting.maps = new ArrayList<>();
                            } else {
//                                if (setting.fromContain(f_from.getAbsolutePath())) {
//                                    T.show
//                                }
                                setting.toContain(from);
                            }
                            setting.maps.add(new CarryInfo(f_from.getAbsolutePath(), f_to.getAbsolutePath()));
                            sp.edit().putString(CARRAY_JSON, new Gson().toJson(setting)).commit();

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
