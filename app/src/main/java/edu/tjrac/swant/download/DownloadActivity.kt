package edu.tjrac.swant.download

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.yckj.baselib.common.base.BaseToolbarActivity
import com.yckj.baselib.common.dialog.EditTextDialog
import com.yckj.baselib.util.FileUtils
import com.yckj.baselib.util.L
import edu.tjrac.swant.download.bean.DownloadFileInfo
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_download.*


@RequiresApi(api = Build.VERSION_CODES.O)
class DownloadActivity : BaseToolbarActivity() {

    internal var NOTIFICATION_ID = 1

    //    @BindView(R.id.toolbar) Toolbar mToolbar;
    //    @BindView(R.id.recycler) RecyclerView mRecycler;
    //    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    internal var download: DownLoadService? = null

    private val threadCallback = object : DownloadThreadCallback {

        override fun onThreadProgress(tag: String, info: DownloadFileInfo) {
            L.i("threadId:" + tag, info.url + "_" + info.getDownloadSize())
        }

        override fun onThreadSuccess(tag: String, info: DownloadFileInfo) {
            L.i("threadId:" + tag, info.url + "_" + info.getDownloadSize())
        }

        override fun onThreadFaild(tag: String, info: DownloadFileInfo, err: String) {

        }
    }
    internal var binder: DownLoadService.DownLoadBinder?=null


    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            binder = service as DownLoadService.DownLoadBinder
            download = binder!!.service
            if (download != null) {
                download!!.startForeground(NOTIFICATION_ID, notification)
                download!!.setThreadcallback(threadCallback)
                //                download.bindToRecycler(mRecycler);
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {

            L.i("onServiceDisconnected", "ServiceConnection:onServiceDisconnected")
        }
    }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        setSupportActionBar(toolbar)

        val intent = Intent(this@DownloadActivity, DownLoadService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        //        startForegroundService(new Intent(DownloadActivity.this,DownLoadService.class));
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this@DownloadActivity).inflate(R.menu.download, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) {
            val dialog = EditTextDialog(mContext, "新建", "请输入路径(url)")
            dialog.positive = DialogInterface.OnClickListener { d, which ->
                val url = dialog.editText.text.toString().trim { it <= ' ' }
                val name = FileUtils.getName(url)
                //                    Intent intent = new Intent(DownloadActivity.this, DownLoadService.class)
                //                            .putExtra("downloadInfo",
                //                                    new DownloadFileInfo(name, url));
                binder!!.addThread(DownloadFileInfo(name, url))
                //                    sendBroadcast(intent);
                //                    bindService(intent,mConnection, BIND_AUTO_CREATE);
                if (download == null) {

                } else {
                }
            }
            dialog.show(supportFragmentManager, "urls")
        }
        return super.onOptionsItemSelected(item)
    }

    internal var CHANNEL_ONE_ID = "edu.tjrac.swant"
    internal var CHANNEL_ONE_NAME = "unicorn download"

    private //        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    val notification: Notification
        get() {

            var notificationChannel: NotificationChannel? = null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(CHANNEL_ONE_ID,
                        CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.setShowBadge(true)
                notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(notificationChannel)
            }

            val mBuilder = Notification.Builder(this@DownloadActivity)

            mBuilder.setChannelId(CHANNEL_ONE_ID)
            mBuilder.setShowWhen(false)
            mBuilder.setAutoCancel(false)
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
            mBuilder.setLargeIcon((resources.getDrawable(R.mipmap.ic_launcher) as BitmapDrawable).bitmap)
            mBuilder.setContentText("this is content")
            mBuilder.setContentTitle("this is title")
            return mBuilder.build()
        }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }
}
