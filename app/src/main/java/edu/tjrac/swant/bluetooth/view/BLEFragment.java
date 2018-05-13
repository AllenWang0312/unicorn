package edu.tjrac.swant.bluetooth.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yckj.baselib.common.base.BaseFragment;
import com.yckj.baselib.util.L;
import com.yckj.baselib.util.T;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.bluetooth.BlueToothHelper;
import edu.tjrac.swant.bluetooth.adapter.BLEFragmentsPagerAdapter;
import edu.tjrac.swant.unicorn.R;

import static edu.tjrac.swant.unicorn.view.MainActivity.REQUEST_COARSE_LOCATION;

/**
 * Created by wpc on 2018/1/25 0025.
 */

public class BLEFragment extends BaseFragment {

    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.vp) ViewPager mVp;
    BLEFragmentsPagerAdapter content_adapter;
    //    @BindView(R.id.rssi) RSSIView mRSSIView;
    BluetoothAdapter adapter;
    //    BluetoothLeScanner scanner;
//首先获取BluetoothManager
    BluetoothManager bluetoothManager;
    private BluetoothGatt mBluetoothGatt;


    private BroadcastReceiver connectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            L.i("BLEFragment:onReceive", action);
//            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
//                Log.i("onReceive", "ACTION_GATT_CONNECTED");
//            }else
            if (BluetoothDevice.ACTION_ACL_CONNECTED == action) {
                //从Intent得到blueDevice对象
                connectedSockets.add(mBluetoothSocket);
                connectedDevices.add(device);

                L.i("RSSI:" + device.getAddress(), intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI) + "");
                content_adapter.addFragment(new ConnectDeviceFragment(BLEFragment.this,
                        found.found_devices.get(found.found_flags.indexOf(device.getAddress())))
                );
                content_adapter.notifyDataSetChanged();
                refeshTitle(device);

            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED == action) {
                content_adapter.remove(device.getAddress());
            }
        }
    };

//    BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {
//        @Override
//        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
//            L.i("onLeScan", device.getAddress());
//            if (!scaned_devices.contains(device)) {
//                scaned_devices.add(device);
//                result_adapter.notifyItemInserted(scaned_devices.size() - 1);
//            }
//        }
//    };

    //    ScanCallback callback = new ScanCallback() {
//        @Override
//        public void onScanResult(int callbackType, ScanResult result) {
//            L.i("onScanResult", result.getDevice().getName());
//            if (result != null && result.getDevice() != null) {
//                if (!scaned_devices.contains(result.getDevice())) {
//                    scaned_devices.add(result.getDevice());
//                    result_adapter.notifyItemInserted(scaned_devices.size() - 1);
//                }
//            }
//            super.onScanResult(callbackType, result);
//        }
//
//        @Override
//        public void onBatchScanResults(List<ScanResult> results) {
//            L.i("onBatchScanResults", results.size() + "");
//            super.onBatchScanResults(results);
//        }
//
//        @Override
//        public void onScanFailed(int errorCode) {
//            scan.setTitle("scan");
//            T.show(getActivity(), "scan failed");
//            super.onScanFailed(errorCode);
//        }
//    };


//    private boolean contains(ArrayList<ScanResultRecycAdapter.BluetoothDeviceInfo> scaned_devices, BluetoothDevice device) {
//        for (ScanResultRecycAdapter.BluetoothDeviceInfo item : scaned_devices) {
//            if (item.getDevices().getAddress() == device.getAddress()) {
//                return true;
//            }
//        }
//        return false;
//    }

    Unbinder unbinder;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    FoundDevicesFragment found;
    BondDevicesFragment bond;
    AdvertiserFragment advertiser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        BlueToothHelper.initRes(getActivity());
        bluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        View view = inflater.inflate(R.layout.fragment_ble, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = bluetoothManager.getAdapter();

//        adapter = BluetoothAdapter.getDefaultAdapter();


        if (adapter == null) {
            T.show(getActivity(), "当前设备不支持蓝牙功能");
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        getActivity().registerReceiver(connectReceiver, filter);

//        found_head= (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.found_devices_filters, null);
//        cb_found_head = (CheckBox) found_head.findViewById(R.id.cb_filter);
//        found_swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });
//         = found_swiper.findViewById(R.id.recycler);


//        View connectDeviceView = LayoutInflater.from(getActivity()).inflate(R.layout.connected_device_view, null);

        content_adapter = new BLEFragmentsPagerAdapter(getActivity(), this, getChildFragmentManager(), mTabLayout);
        found = new FoundDevicesFragment(this, adapter, "found");
        content_adapter.addFragment(found);
        bond = new BondDevicesFragment(this, adapter, "bond");
        content_adapter.addFragment(bond);
        advertiser = new AdvertiserFragment("advertiser");
        content_adapter.addFragment(advertiser);
//        content_adapter.addView(found, "Found");

        if (adapter == null) {
            T.show(getActivity(), "BlueTooth is unable");
        } else {
//            View advertiser = LayoutInflater.from(getActivity()).inflate(R.layout.ble_advertiser, null);
//            content_adapter.addView(advertiser, "advertiser");
        }
//        ImageView imageView = new ImageView(getActivity());
//        imageView.setImageResource(R.drawable.__leak_canary_icon);
//        content_adapter.addView(imageView, "Image");
        mVp.setAdapter(content_adapter);
        mVp.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mVp);
        return view;
    }

    private BluetoothSocket mBluetoothSocket;

    //2.x
    public void connect(BluetoothDevice btDev, Handler handler) {
        try {
            //通过和服务器协商的uuid来进行连接
            mBluetoothSocket = btDev.createRfcommSocketToServiceRecord(BlueToothHelper.SPP_UUID);
            if (mBluetoothSocket != null)
                //全局只有一个bluetooth，所以我们可以将这个socket对象保存在appliaction中
                //通过反射得到bltSocket对象，与uuid进行连接得到的结果一样，但这里不提倡用反射的方法
                //mBluetoothSocket = (BluetoothSocket) btDev.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(btDev, 1);
                //在建立之前调用
                if (adapter.isDiscovering())
                    //停止搜索
                    adapter.cancelDiscovery();
            //如果当前socket处于非连接状态则调用连接
            if (!mBluetoothSocket.isConnected()) {
                //你应当确保在调用connect()时设备没有执行搜索设备的操作。
                // 如果搜索设备也在同时进行，那么将会显著地降低连接速率，并很大程度上会连接失败。
                mBluetoothSocket.connect();
            }
            Log.d("blueTooth", "已经链接");
            if (handler == null) return;
            //结果回调
            Message message = new Message();
            message.what = 4;
            message.obj = btDev;
            handler.sendMessage(message);
        } catch (Exception e) {
            Log.e("blueTooth", "...链接失败");
            try {
                mBluetoothSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    //4.x
    public boolean connect(String address) {
        final BluetoothDevice device = adapter.getRemoteDevice(address);
        if (device == null) {
            connect(device);
        }
        return false;
    }

    public boolean connect(BluetoothDevice device) {
        mBluetoothGatt = device.connectGatt(getActivity(), false, mGattCallback);
        return true;
    }

    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            super.onPhyUpdate(gatt, txPhy, rxPhy, status);
        }

        @Override
        public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            super.onPhyRead(gatt, txPhy, rxPhy, status);
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }
    };

    public void disconnect(BluetoothDevice device) {

        BluetoothSocket socket = connectedSockets.get(connectedDevices.indexOf(device));

        if (socket.isConnected()) {
            Log.e("mBluetoothSocket", "...正在断开");
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("mBluetoothSocket", "...断开出错");
                e.printStackTrace();
            }
        }
    }

    ArrayList<BluetoothSocket> connectedSockets = new ArrayList<>();
    ArrayList<BluetoothDevice> connectedDevices = new ArrayList<>();

    public void refeshTitle(BluetoothDevice device) {
        int position = connectedDevices.indexOf(device);
        TabLayout.Tab tab = mTabLayout.getTabAt(position + 3);
        View tab_view = LayoutInflater.from(getActivity()).inflate(R.layout.title_connected_device, null);
        ((TextView) tab_view.findViewById(R.id.name)).setText(device.getName());
        ((TextView) tab_view.findViewById(R.id.address)).setText(device.getAddress());
        tab_view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnect(device);
            }
        });
        tab.setCustomView(tab_view);
    }

    @Override
    public void onResume() {
        if (adapter.isEnabled()) {

        } else {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            enableBtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().startActivity(enableBtIntent);
//            T.show(getActivity(), "请打开蓝牙");
//            adapter.enable();
        }
        super.onResume();
    }

    MenuItem scan;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bluetooth, menu);
        scan = menu.findItem(R.id.ble_scan);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ble_scan:
                switch (scan.getTitle().toString()) {
                    case "scan":
                        found.scanDevices();
                        break;
                    case "stop scanning":
                        found.stopScanning();
//                        adapter.cancelDiscovery();
                        break;
                    case "disconnect":

                        break;
                }

                break;
            case R.id.ble_refresh:

                break;
            case R.id.ble_rssi:
//                mRSSIView.setVisibility(View.VISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBack() {
        getActivity().onBackPressed();
    }

    @Override
    public boolean backable() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                T.show(getActivity(), "现在已经获取扫描权限啦");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(connectReceiver);
        super.onDestroy();
    }
}
