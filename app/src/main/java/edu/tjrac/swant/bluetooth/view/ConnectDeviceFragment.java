package edu.tjrac.swant.bluetooth.view;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import com.yckj.baselib.common.base.BaseFragment;

/**
 * Created by wpc on 2018/2/1 0001.
 */
@SuppressLint("ValidFragment")
public class ConnectDeviceFragment extends BaseFragment {

    @BindView(R.id.log) TextView mLog;

    private  ParcelUuid[] mUuids;

    private ScanResult result;
    private BluetoothDevice mDevice;
    private BLEFragment parent;
    Unbinder unbinder;

    public ConnectDeviceFragment(BLEFragment parent, ScanResult result) {
        this.result = result;
        this.parent = parent;
//        result.getSecondaryPhy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connected_devices, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.mDevice = result.getDevice();
        mUuids = mDevice.getUuids();

        StringBuffer sb=new StringBuffer();
        sb.append(mUuids.toString());

        mLog.setText(sb.toString());
//        parent.refeshTitle(mDevice);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return mDevice.getName() + "\n" + mDevice.getAddress();
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public BluetoothDevice getDevice() {
        return mDevice;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
