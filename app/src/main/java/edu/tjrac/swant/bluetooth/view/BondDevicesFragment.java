package edu.tjrac.swant.bluetooth.view;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseFragment;
import com.yckj.baselib.util.uncom.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.adapter.BoundedDevicesAdapter;

/**
 * Created by wpc on 2018/1/31 0031.
 */

@SuppressLint("ValidFragment")
public class BondDevicesFragment extends BaseFragment {

    private BluetoothAdapter adapter;
    private BLEFragment parent;

    public BondDevicesFragment(BLEFragment parent, BluetoothAdapter adapter, String title) {
        this.parent = parent;
        this.adapter = adapter;
        this.title = title;
    }

    BoundedDevicesAdapter bound_adapter;
    @BindView(R.id.swiper)
    SwipeRefreshLayout bound_swiper;
    @BindView(R.id.recycler)
    RecyclerView recycler_bound;

    Set<BluetoothDevice> bounded_devices;
    List<BluetoothDevice> devices=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bond_swiper_recycler, container, false);
        ButterKnife.bind(this, view);

//        L.i("bounded devices", bounded_devices.toString());
        bound_swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        recycler_bound.setLayoutManager(new LinearLayoutManager(getActivity()));

        bound_adapter = new BoundedDevicesAdapter(devices);
        bound_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.iv_options){

                }else {

                }
            }
        });
        bound_adapter.bindToRecyclerView(recycler_bound);
        bound_adapter.setEmptyView(R.layout.empty);
        recycler_bound.setAdapter(bound_adapter);
        initData();
        return view;
    }

    void initData() {
        bounded_devices = adapter.getBondedDevices();
        devices=  new ArrayUtil<BluetoothDevice> ().asArray(bounded_devices);
        bound_adapter.notifyDataSetChanged();
//        if(bound_adapter.getEmptyViewCount()>0){

//        }
        bound_swiper.setRefreshing(false);
    }

    @Override
    public void onBack() {

    }
}
