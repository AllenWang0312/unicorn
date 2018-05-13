package edu.tjrac.swant.trafficmonitor;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.yckj.baselib.common.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

@SuppressLint("NewApi")
public class NetDataWatcherActivity extends BaseToolbarActivity {

    @BindView(R.id.recycle) RecyclerView mRecycle;
    NetDataLiatAdapter adapter;

    long tx_total, rx_total;


    NotificationManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_data_watcher);
        ButterKnife.bind(this);
        bindToolbar(R.id.toolbar);
        mManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        adapter = new NetDataLiatAdapter(list);
        mRecycle.setLayoutManager(new LinearLayoutManager(NetDataWatcherActivity.this));
        mRecycle.setAdapter(adapter);

        getAppTrafficList();
//        list.sort(new Comparator<AppNetDataInfo>() {
//            @Override
//            public int compare(AppNetDataInfo o1, AppNetDataInfo o2) {
//                return o1.total > o2.total ? -1 : 1;
//            }
//        });
        adapter.notifyDataSetChanged();
    }

    private ArrayList<AppNetDataInfo> list = new ArrayList<>();

    public void getAppTrafficList() {
        if (list.size() > 0) {
            list.clear();
        }
        //获取所有的安装在手机上的应用软件的信息，并且获取这些软件里面的权限信息
        PackageManager pm = getPackageManager();//获取系统应用包管理

        //获取每个包内的androidmanifest.xml信息，它的权限等等
        List<PackageInfo> pinfos = pm.getInstalledPackages
                (PackageManager.GET_UNINSTALLED_PACKAGES
                        | PackageManager.GET_PERMISSIONS
                );
        //遍历每个应用包信息
        for (PackageInfo info : pinfos) {
            //请求每个程序包对应的androidManifest.xml里面的权限
            String[] premissions = info.requestedPermissions;
            if (premissions != null && premissions.length > 0) {
                //找出需要网络服务的应用程序
                for (String premission : premissions) {
                    if ("android.permission.INTERNET".equals(premission)) {
                        //获取每个应用程序在操作系统内的进程id
                        int uId = info.applicationInfo.uid;
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        long rx = TrafficStats.getUidRxBytes(uId);
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        long tx = TrafficStats.getUidTxBytes(uId);
                        if (rx < 0 || tx < 0) {
                            continue;
                        } else {
                            tx_total += tx;
                            rx_total += rx;

                            list.add(new AppNetDataInfo(rx, tx,
                                    info.versionName, info.versionCode,
                                    info.packageName,
                                    info.applicationInfo.loadLabel(getPackageManager()).toString(),
                                    info.applicationInfo.loadIcon(pm)));
//                            Log.i("netdata", info.applicationInfo.loadLabel(pm) + "消耗的流量--" +
//                                    Formatter.formatFileSize(this, rx + tx));
//                            Toast.makeText(this, info.applicationInfo.loadLabel(pm) + "消耗的流量--" +
//                                    Formatter.formatFileSize(this, rx + tx), Toast.LENGTH_SHORT);
                        }
                    }
                }
//                list.sort(new Comparator<AppNetDataInfo>() {
//                    @Override
//                    public int compare(AppNetDataInfo o1, AppNetDataInfo o2) {
//                        return o1.getTx()>o2.getTx()? 1:-1;
//                    }
//                });
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(mContext).inflate(R.menu.net_data,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.options_start){
            Intent mainIntent = new Intent(this, NetDataWatcherActivity.class);
            PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder builder= new  Notification.Builder(mContext)
                   .setSmallIcon(R.mipmap.ufo_ed)
                   .setContentTitle("网络监视器")
                   .setContentText("正在运行")
                    .setContentIntent(mainPendingIntent);

            mManager.notify(100,builder.build());
        }
        return super.onOptionsItemSelected(item);
    }

    class AppNetDataInfo {

        String time; //2018-03-12

        long rx;
        long tx;
        long total;
        String version;
        int versionCode;
        String packageName;
        String appName;
        Drawable appIcon;

        public AppNetDataInfo(long rx, long tx, String version, int versionCode, String packageName, String appName, Drawable appIcon) {
            this.rx = rx;
            this.tx = tx;
            total = rx + tx;
            this.version = version;
            this.versionCode = versionCode;
            this.packageName = packageName;
            this.appName = appName;
            this.appIcon = appIcon;
        }

        public long getTotal() {
            return total;
        }

        public long getRx() {
            return rx;
        }

        public void setRx(long rx) {
            this.rx = rx;
        }

        public long getTx() {
            return tx;
        }

        public void setTx(long tx) {
            this.tx = tx;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public Drawable getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(Drawable appIcon) {
            this.appIcon = appIcon;
        }
    }
}
