package edu.tjrac.swant.bluetooth.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseDecoration;
import com.yckj.baselib.common.base.BaseFragment;
import com.yckj.baselib.util.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.BlueToothHelper;
import edu.tjrac.swant.bluetooth.adapter.ScanResultRecycAdapter;
import edu.tjrac.swant.bluetooth.bean.ScanInfo;

import static android.content.Context.MODE_PRIVATE;
import static edu.tjrac.swant.unicorn.view.MainActivity.REQUEST_COARSE_LOCATION;

/**
 * Created by wpc on 2018/1/31 0031.
 */
@SuppressLint("ValidFragment")
public class FoundDevicesFragment extends BaseFragment {


    boolean auto_scanning = true;

    SharedPreferences sp;
    private BLEFragment parent;
    ScanResultRecycAdapter found_adapter;
    @BindView(R.id.swiper) SwipeRefreshLayout found_swiper;
    //    CardView found_head;
    @BindView(R.id.cb_filter) CheckBox cb_found_head;
    @BindView(R.id.recycler) RecyclerView recycler_found;

    BluetoothAdapter adapter;

    ArrayList<String> found_flags = new ArrayList<>();
    ArrayList<ScanResult> found_devices = new ArrayList<>();
    HashMap<String, ArrayList<ScanInfo>> scaninfo = new HashMap<>();

    Handler handler = new Handler();
    Unbinder mUnbinder;

    private ScanCallback callback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            String address = result.getDevice().getAddress();
//            String name = result.getDevice().getName();
            L.i("onScanResult:address",
//                    name + ":" +
                    address);
            L.i("onScanResult:detial", result.toString());
            if (found_flags.contains(address)) {
                ArrayList<ScanInfo> infos = scaninfo.get(address);
                infos.add(0, new ScanInfo(System.currentTimeMillis(), result.getRssi(), infos.get(infos.size() - 1).getTagTime()));
                found_devices.set(found_flags.indexOf(address), result);
                found_adapter.notifyItemChanged(found_flags.indexOf(address));
            } else {
                scaninfo.put(address, new ArrayList<>());
                scaninfo.get(address).add(
                        new ScanInfo(System.currentTimeMillis(), result.getRssi(), 0l));
                found_flags.add(result.getDevice().getAddress());
                found_devices.add(result);
                found_adapter.notifyItemInserted(found_devices.size() - 1);
            }

            super.onScanResult(callbackType, result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            L.i("onBatchScanResults");

            found_adapter.scanFinish(results);
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            L.i("onScanFailed");
            super.onScanFailed(errorCode);
        }
    };

    public FoundDevicesFragment(BLEFragment parent, BluetoothAdapter adapter, String title) {
        this.parent = parent;
        this.adapter = adapter;
        scanner = adapter.getBluetoothLeScanner();
        this.title = title;
    }


    private BroadcastReceiver BLEReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED == action) {
                found_swiper.setRefreshing(true);
                parent.scan.setTitle("stop scanning");
            }
//            else if (BluetoothDevice.ACTION_ACL_CONNECTED == action) {
//                //从Intent得到blueDevice对象
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                L.i("RSSI:"+device.getAddress(), intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI) + "");
//                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
//                    for (int i = 0; i < found_devices.size(); i++) {
//                        if (found_devices.get(i).getAddress().equals(device.getAddress())) {
//
////                            found_devices.get(i).setRssi(intent.getExtras().getShort(
////                                    BluetoothDevice.EXTRA_RSSI));
//                            found_adapter.notifyItemChanged(i);
//                        }
//                    }
//                }
//            }
            else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED == action) {
                found_adapter.notifyItemChanged(found_flags.indexOf(device.getAddress()));
            } else if (BluetoothDevice.ACTION_FOUND == action) {

//                if (!found_devices.contains(device)) {
//                    found_devices.add(device);
//                    found_adapter.notifyItemInserted(found_devices.size() - 1);
//                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                parent.scan.setTitle("scan");
                found_swiper.setRefreshing(false);
            }
        }
    };
    PopupWindow filterView;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.found_swiper_recycler, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("bluetooth", MODE_PRIVATE);

        initRecycView();
        initFilterView();


        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(BLEReceiver, filter);

        return view;
    }

    private void initRecycView() {
        recycler_found.setLayoutManager(new LinearLayoutManager(getActivity()));
        found_adapter = new ScanResultRecycAdapter(sp, found_devices);
        recycler_found.addItemDecoration(new BaseDecoration(getActivity(), BaseDecoration.VERTICAL_LIST));

        found_adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (found_adapter.getExpendIndex() == position) {
                    found_adapter.setExpendItem(-1);
                } else {
                    found_adapter.setExpendItem(position);
                }

            }
        });
        found_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter ad, View view, int position) {
                BluetoothDevice device = found_devices.get(position).getDevice();
                String address = device.getAddress();
                if (view.getId() == R.id.iv_options) {
                    PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                    //将指定的菜单布局进行加载
                    getActivity().getMenuInflater().inflate(R.menu.connect, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.connect_phy) {

                            } else if (item.getItemId() == R.id.connect_bond) {
//                                found_devices.get(position).getDevice().createBond();
                                BlueToothHelper.createBond(found_devices.get(position).getDevice());
                            }
                            return false;
                        }
                    });//给菜单绑定监听
                    //展示菜单
                    popupMenu.show();
                } else if (view.getId() == R.id.bt_raw) {
                    new RawDataDialog(found_devices.get(position)).show(getFragmentManager(), "RawData");
                } else if (view.getId() == R.id.iv_icon) {
//                    Map<String, ?> configs = sp.getAll();
//                    L.i("keysets",configs.keySet().toString());

                    if (sp.getBoolean(address, false) == true) {
                        sp.edit().putBoolean(address, false).commit();
                    } else {
                        sp.edit().putBoolean(address, true).commit();
                    }

                    found_adapter.notifyItemChanged(position);
                    L.i("favourite", String.valueOf(sp.getBoolean(address, false)));
                } else if (view.getId() == R.id.bt_more) {
                    stopScanning();
                    DevicesMoreInfoActivity.start(getActivity(), found_devices.get(position), scaninfo.get(address));
                } else if (view.getId() == R.id.tv_connect) {
                    parent.connect(device);
                }
            }
        });
        found_swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scanDevices();
            }
        });
        found_adapter.bindToRecyclerView(recycler_found);
        recycler_found.setAdapter(found_adapter);
    }

    private void initFilterView() {
        View filter_view = LayoutInflater.from(getActivity()).inflate(R.layout.found_filter_view, null);
        filter_view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        filterView = new PopupWindow(getActivity());
        filterView.setFocusable(true);
        filterView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        filterView.setWindowLayoutType(ViewGroup.LayoutParams.MATCH_PARENT);
        filterView.setOutsideTouchable(true);
        filterView.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.card));

        filterView.setElevation(4);
        filterView.setContentView(filter_view);
        filterView.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cb_found_head.setChecked(false);
            }
        });
        cb_found_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterView.showAsDropDown(cb_found_head);
                    changeWindowAlfa(0.8f);

                } else {
                    filterView.dismiss();
                    changeWindowAlfa(1f);
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        scanDevices();
        super.onViewCreated(view, savedInstanceState);
    }

    /*
                更改屏幕窗口透明度
             */
    void changeWindowAlfa(float alfa) {
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = alfa;
        getActivity().getWindow().setAttributes(params);
    }

    int scan_timeout = 12;
    BluetoothLeScanner scanner;
    List<ScanFilter> filter = null;
    ScanSettings setting = null;

    public void scanDevices() {
        if (adapter.isEnabled()) {
            if (!found_swiper.isRefreshing()) {
                found_swiper.setRefreshing(true);
            }
            L.i("start Scan");
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_COARSE_LOCATION);
            } else {
                //发现设备 方法一
//                adapter.startDiscovery();
//方法二
                scanner = adapter.getBluetoothLeScanner();
                parent.scan.setTitle("stop scanning");
                if (filter == null) {
                    scanner.startScan(callback);
                } else {
                    scanner.startScan(filter, setting, callback);
                }
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        found_swiper.setRefreshing(false);
//                        parent.scan.setTitle("scan");
//                        scanner.stopScan(callback);
//                    }
//                }, 1000 * scan_timeout);

                //方法三
//                        adapter.startLeScan(callback);
            }
        } else {
            L.i("adapter is unable");
//                    T.show(getActivity(), "请打开蓝牙");
            adapter.enable();
        }
    }

    public void stopScanning() {
        found_swiper.setRefreshing(false);
        scanner.stopScan(callback);
        parent.scan.setTitle("scan");
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        getActivity().unregisterReceiver(BLEReceiver);
        super.onDestroy();
    }

    @Override
    public void onBack() {

    }

    @Override
    public boolean backable() {
        return false;
    }


}
