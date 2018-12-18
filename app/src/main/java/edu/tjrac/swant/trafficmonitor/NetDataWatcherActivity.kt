package edu.tjrac.swant.trafficmonitor

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.TrafficStats
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import com.yckj.baselib.common.base.BaseToolbarActivity

import java.util.ArrayList

import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_net_data_watcher.*

@SuppressLint("NewApi")
class NetDataWatcherActivity : BaseToolbarActivity() {

    internal var adapter: NetDataLiatAdapter?=null

    internal var tx_total: Long = 0
    internal var rx_total: Long = 0


    internal var mManager: NotificationManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_data_watcher)
        bindToolbar(R.id.toolbar)
        mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        adapter = NetDataLiatAdapter(list)
        recycler.setLayoutManager(LinearLayoutManager(this@NetDataWatcherActivity))
        recycler.setAdapter(adapter)

        getAppTrafficList()
        //        list.sort(new Comparator<AppNetDataInfo>() {
        //            @Override
        //            public int compare(AppNetDataInfo o1, AppNetDataInfo o2) {
        //                return o1.total > o2.total ? -1 : 1;
        //            }
        //        });
        adapter!!.notifyDataSetChanged()
    }

    private val list = ArrayList<AppNetDataInfo>()

    fun getAppTrafficList() {
        if (list.size > 0) {
            list.clear()
        }
        //获取所有的安装在手机上的应用软件的信息，并且获取这些软件里面的权限信息
        val pm = packageManager//获取系统应用包管理

        //获取每个包内的androidmanifest.xml信息，它的权限等等
        val pinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES or PackageManager.GET_PERMISSIONS
        )
        //遍历每个应用包信息
        for (info in pinfos) {
            //请求每个程序包对应的androidManifest.xml里面的权限
            val premissions = info.requestedPermissions
            if (premissions != null && premissions.size > 0) {
                //找出需要网络服务的应用程序
                for (premission in premissions) {
                    if ("android.permission.INTERNET" == premission) {
                        //获取每个应用程序在操作系统内的进程id
                        val uId = info.applicationInfo.uid
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        val rx = TrafficStats.getUidRxBytes(uId)
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        val tx = TrafficStats.getUidTxBytes(uId)
                        if (rx < 0 || tx < 0) {
                            continue
                        } else {
                            tx_total += tx
                            rx_total += rx

                            list.add(AppNetDataInfo(rx, tx,
                                    info.versionName, info.versionCode,
                                    info.packageName,
                                    info.applicationInfo.loadLabel(packageManager).toString(),
                                    info.applicationInfo.loadIcon(pm)))
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(mContext).inflate(R.menu.net_data, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.options_start) {
            val mainIntent = Intent(this, NetDataWatcherActivity::class.java)
            val mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = Notification.Builder(mContext)
                    .setSmallIcon(R.mipmap.ufo_ed)
                    .setContentTitle("网络监视器")
                    .setContentText("正在运行")
                    .setContentIntent(mainPendingIntent)

            mManager!!.notify(100, builder.build())
        }
        return super.onOptionsItemSelected(item)
    }

    internal inner class AppNetDataInfo(var rx: Long, var tx: Long, var version: String, var versionCode: Int, var packageName: String, var appName: String, var appIcon: Drawable) {

        var time: String? = null //2018-03-12
        var total: Long = 0

        init {
            total = rx + tx
        }
    }
}
