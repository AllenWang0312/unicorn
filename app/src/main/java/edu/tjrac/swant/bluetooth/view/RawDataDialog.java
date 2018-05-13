package edu.tjrac.swant.bluetooth.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.yckj.baselib.util.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/1 0001.
 */

@SuppressLint("ValidFragment")
public class RawDataDialog extends DialogFragment {
String TAG="RawDataDialog";
    @BindView(R.id.et_raw_data) EditText mEtRawData;
    @BindView(R.id.tab_row_2) TableRow mTabRow2;
    @BindView(R.id.tab_row_3) TableRow mTabRow3;
    @BindView(R.id.tab_row_4) TableRow mTabRow4;
    @BindView(R.id.tv_info) TextView mTvInfo;
    Unbinder unbinder;

    private ScanResult result;
    private SparseArray<byte[]> mManufacturerSpecificData;

    public RawDataDialog(ScanResult scanResult) {
        result=scanResult;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        L.i(TAG,result.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("RAW DATA");
        View dialog = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_rawdata, null);
        ButterKnife.bind(this,dialog);
        mManufacturerSpecificData = result.getScanRecord().getManufacturerSpecificData();
        result.getScanRecord().getServiceData();

//        L.i("specificData",)
//        mEtRawData.setText();

        builder.setView(dialog);
        builder.setPositiveButton("ok", null);
        return builder.create();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
