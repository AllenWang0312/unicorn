package edu.tjrac.swant.bluetooth.adapter;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.SharedPreferences;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yckj.baselib.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.BlueToothHelper;

/**
 * Created by wpc on 2018/1/26 0026.
 */

public class ScanResultRecycAdapter extends BaseQuickAdapter<ScanResult, BaseViewHolder> {

    int expand_item_index = -1, last_index = -1;
    SharedPreferences sp;

    public ScanResultRecycAdapter(SharedPreferences sp, @Nullable List<ScanResult> data) {
        super(R.layout.item_scan_result, data);
        this.sp = sp;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScanResult item) {
        BluetoothDevice device = item.getDevice();

//        item.getScanRecord().getAdvertiseFlags()
        boolean[] flags = BlueToothHelper.getFlags(item.getScanRecord().getAdvertiseFlags());

        helper.setText(R.id.tv_flags, BlueToothHelper.getFlags(flags));

        if (sp.getBoolean(device.getAddress(), false) == true) {
            helper.setChecked(R.id.cb_favourite, true);
        } else {
            helper.setChecked(R.id.cb_favourite, false);
        }
        helper.addOnClickListener(R.id.iv_icon);

        if (StringUtils.isEmpty(device.getName())) {
            helper.setText(R.id.tv_name, "N/A");
        } else {
            helper.setText(R.id.tv_name, device.getName());
        }
        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
            helper.setText(R.id.tv_bound_state, "BONDED");
        } else if (device.getBondState() == BluetoothDevice.BOND_BONDING) {
            helper.setText(R.id.tv_bound_state, "BONDING");
        } else {
            helper.setText(R.id.tv_bound_state, "NOT BONDED");
        }
        helper.setText(R.id.tv_address, device.getAddress());

        helper.setText(R.id.tv_dbm, String.valueOf(item.getRssi()) + "dBm");
        if (finishScanDevices != null && finishScanDevices.size() > 0 && finishScanDevices.contains(device.getAddress())) {
            helper.getView(R.id.tv_dbm).setAlpha(0.5f);
//            helper.setTextColor(R.id.tv_dbm, mContext.getResources().getColor(R.color.gray));
        } else {
            helper.getView(R.id.tv_dbm).setAlpha(1f);
//            helper.setTextColor(R.id.tv_dbm, mContext.getResources().getColor(R.color.black));
        }

        helper.addOnClickListener(R.id.tv_connect);
        helper.addOnClickListener(R.id.iv_options);
        LinearLayout expand = helper.getView(R.id.ll_expand);
        if (helper.getPosition() == expand_item_index) {
            expand.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_device_type, BlueToothHelper.getType(device.getType()));
//            List<ParcelUuid> mServiceUuids = item.getScanRecord().getServiceUuids();
            ParcelUuid[] mServiceUuids = item.getDevice().getUuids();
            if (mServiceUuids != null) {
                helper.setText(R.id.tv_uuid, mServiceUuids.toString());
            } else {
                helper.setText(R.id.tv_uuid, "null");
            }

            helper.setText(R.id.tv_local_name, item.getDevice().getName());
            helper.addOnClickListener(R.id.bt_raw);
            helper.addOnClickListener(R.id.bt_more);
        } else {
            expand.setVisibility(View.GONE);
        }
    }


//    static class BluetoothDeviceInfo {
//        BluetoothDevice device;
//        short rssi;
//
//        public BluetoothDeviceInfo(BluetoothDevice device) {
//            this.device = device;
//        }
//
//        public BluetoothDevice getDevices() {
//            return device;
//        }
//
//        public void setDevices(BluetoothDevice device) {
//            this.device = device;
//        }
//
//        public short getRssi() {
//            return rssi;
//        }
//
//        public void setRssi(short rssi) {
//            this.rssi = rssi;
//        }
//    }

    public void setExpendItem(int index) {
        this.last_index = expand_item_index;
        this.expand_item_index = index;
        if (last_index > -1) {
            notifyItemChanged(last_index);
        }
        if (expand_item_index > -1) {
            notifyItemChanged(expand_item_index);
        }
    }

    public int getExpendIndex() {
        return expand_item_index;
    }


    List<String> finishScanDevices;

    public void scanFinish(List<ScanResult> results) {
        finishScanDevices = new ArrayList<>();
        for (ScanResult item : results) {
            finishScanDevices.add(item.getDevice().getAddress());
        }
        notifyDataSetChanged();
    }
}
