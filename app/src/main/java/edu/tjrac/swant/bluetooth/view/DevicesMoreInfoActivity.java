package edu.tjrac.swant.bluetooth.view;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.bean.ScanInfo;

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter;
import com.yckj.baselib.util.StringUtils;

public class DevicesMoreInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tablayout) TabLayout mTablayout;
    @BindView(R.id.vp) ViewPager mVp;
    ScanResult scanResult;
    BluetoothDevice device;
    ArrayList<ScanInfo> infos;
    FragmentsPagerAdapter adapter;

    private ScanInfoHistoryFragment history;
    private ScanInfoFlagsAndServicesFragment flags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_more_info);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        scanResult = intent.getParcelableExtra("scanResult");
        device=scanResult.getDevice();
        infos=intent.getParcelableArrayListExtra("infos");

        mToolbar.setNavigationIcon(R.drawable.ic_close_grey_600_24dp);
        setTitleColor(getResources().getColor(R.color.black));
        if (StringUtils.isEmpty(device.getName())) {
            setTitle(device.getName());
        } else {
            setTitle("N/A");
        }

        adapter = new FragmentsPagerAdapter(getSupportFragmentManager());
        history = new ScanInfoHistoryFragment(scanResult,infos);
        flags=new ScanInfoFlagsAndServicesFragment(scanResult);
        adapter.addFragment(history, "history");
        adapter.addFragment(flags,"flags");
        mVp.setAdapter(adapter);
        mTablayout.setupWithViewPager(mVp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(DevicesMoreInfoActivity.this)
                .inflate(R.menu.devices_more_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.info) {

        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, ScanResult scanResult, ArrayList<ScanInfo> scanInfos) {
        Intent intent = new Intent(context, DevicesMoreInfoActivity.class);
        intent.putExtra("scanResult", scanResult);
        intent.putParcelableArrayListExtra("infos", scanInfos);
        context.startActivity(intent);
    }
}
