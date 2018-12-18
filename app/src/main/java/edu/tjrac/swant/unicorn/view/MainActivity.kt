package edu.tjrac.swant.unicorn.view

import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yckj.baselib.common.base.BaseFragment
import com.yckj.baselib.util.*
import com.yckj.baselib.util.uncom.CameraUtil
import edu.tjrac.swant.arcore.augmentedimage.AugmentedImageActivity
import edu.tjrac.swant.arcore.cloudanchor.CloudAnchorActivity
import edu.tjrac.swant.arcore.solar.SolarActivity
import edu.tjrac.swant.download.DownloadActivity
import edu.tjrac.swant.filesystem.view.GalleryFragment
import edu.tjrac.swant.filesystem.view.ImageEditorActivity
import edu.tjrac.swant.fingerprint.FingerPrintBaseActivity
import edu.tjrac.swant.netimage.view.NetImageActivity
import edu.tjrac.swant.todo.view.ClipboardInfoDialog
import edu.tjrac.swant.todo.view.ToDoMainActivity
import edu.tjrac.swant.trafficmonitor.NetDataWatcherActivity
import edu.tjrac.swant.unicorn.AlipayZeroSdk
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.unicorn.SharedStartActivity
import edu.tjrac.swant.unicorn.bean.User
import edu.tjrac.swant.wifi.FileWifiShareServer
import edu.tjrac.swant.wifi.WifiChartActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : SharedStartActivity(), NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

//    @BindView(R.id.toolbar) internal var toolbar: Toolbar? = null
//    @BindView(R.id.drawer_layout) internal var drawer: DrawerLayout? = null
//    @BindView(R.id.nav_view) internal var navigationView: NavigationView? = null
//    @BindView(R.id.fl_main) internal var frame: FrameLayout? = null

    internal var contentFragment: BaseFragment?=null

    internal var mGalleryFragment: GalleryFragment? = null


    //    private UserBean loged;

    internal var poartait: ImageView?=null
    internal var name: TextView?=null
    internal var sub: TextView?=null
    internal var tv_ip: TextView?=null
    internal var tv_mac: TextView?=null
    internal var tv_location: TextView?=null
    internal var tv_fx: TextView? = null
    internal var sw_wifi: Switch?=null
    internal var sw_ble: Switch?=null
    internal var sw_location: Switch?=null
    internal var sw_fx: Switch?=null
    private var rotVecValues: FloatArray? = null
    private val rotvecR = FloatArray(9)
    private val rotQ = FloatArray(4)
    private val rotvecOrientValues = FloatArray(3)

    internal var loc: LocationUtils?=null
    internal var bleAdapter: BluetoothAdapter? = null

    override fun onResume() {
        super.onResume()

        verificticonClipBoard()

        //        if (App.Companion.getLoged() == null) {
        //            RoomDatabase database = Room.databaseBuilder(getApplicationContext(),
        //                    RoomDatabase.class, "database_name")
        //                    .allowMainThreadQueries()
        //                    .build();
        //            List<User> users=database.userDao().query();
        //            if(users.size()>0){
        //                User user=users.get(0);
        //                if(user!=null){
        //                    App.loged=user;
        //                    insertUserInfo(user);
        //                }
        //            }

        //        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout!!.addDrawerListener(toggle)
        toggle.syncState()
        nav_view!!.setNavigationItemSelectedListener(this)

        bleAdapter = BluetoothAdapter.getDefaultAdapter()

        val headview = nav_view!!.getHeaderView(0)
        tv_ip = headview.findViewById<TextView>(R.id.tv_ip)
        tv_mac = headview.findViewById<TextView>(R.id.tv_mac)
        tv_location = headview.findViewById<TextView>(R.id.sw_location)
        name = headview.findViewById<TextView>(R.id.tv_username)
        poartait = headview.findViewById<ImageView>(R.id.iv_portait)


        sub = headview.findViewById<TextView>(R.id.tv_sub)

        sw_wifi = headview.findViewById<Switch>(R.id.sw_wifi)
        sw_ble = headview.findViewById<Switch>(R.id.sw_ble)
        sw_location = headview.findViewById<Switch>(R.id.sw_location)
        sw_fx = headview.findViewById<Switch>(R.id.sw_fx)

        sw_wifi!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                startService(Intent(
                        this@MainActivity, FileWifiShareServer::class.java))

            } else {
                stopService(Intent(
                        this@MainActivity, FileWifiShareServer::class.java))
            }
        }
        sw_ble!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (bleAdapter != null) {
                if (isChecked) {

                    if (!bleAdapter!!.isEnabled) {
                        bleAdapter!!.enable()
                        tv_mac!!.text = bleAdapter!!.address
                    }
                } else {
                    if (bleAdapter!!.isEnabled) {
                        bleAdapter!!.disable()
                    }
                }
            }
        }
        sw_location!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                loc = LocationUtils.getInstance(this@MainActivity)
                val location = loc!!.init()
                if (location != null) {
                    tv_location!!.text = LocationUtils.formatLocation(location)
                    //                        App.Companion.getLoged().setLocation(location);
                    //                        NetRequire.requireJson("1003", App.Companion.getLoged(), new Callback() {
                    //                            @Override
                    //                            public void onFailure(Call call, IOException e) {
                    //
                    //                            }
                    //
                    //                            @Override
                    //                            public void onResponse(Call call, Response response) throws IOException {
                    //                                String result = response.body().string();
                    //                                if (!StringUtils.isEmpty(result)) {
                    //                                    Log.i("1003", result);
                    //                                    UpdataLocationResult1003 res = new Gson().fromJson(result, UpdataLocationResult1003.class);
                    //                                    if (res.getCode() == 200) {
                    //                                        T.show(MainActivity.this, "updata location success");
                    //                                    } else {
                    //                                        T.show(MainActivity.this, "updata location faild");
                    //
                    //                                    }
                    //                                }
                    //                            }
                    //                        });
                }
                loc!!.addLocationListener(object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        location.accuracy
                        //                            loc.setLocation(location);
                        tv_location!!.text = LocationUtils.formatLocation(location)
                        T.show(this@MainActivity, LocationUtils.formatLocation(location))
                    }

                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

                    }

                    override fun onProviderEnabled(provider: String) {

                    }

                    override fun onProviderDisabled(provider: String) {

                    }
                })

                sw_fx!!.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {

                    }
                }
            } else {

            }
        }
        sw_fx!!.setOnCheckedChangeListener { buttonView, isChecked ->
            val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        }
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_NETWORK_STATE),
                    REQUEST_TAKE_PHOTO_PERMISSION)

        } else {
            tv_ip!!.text = IP.getHostIp()!! + ":54321"
        }
        poartait = headview.findViewById<ImageView>(R.id.iv_portait)
        //        Glide.with(MainActivity.this).load("http://192.168.43.80:8082/userinfo/1/avarat.png")
        //                .into(poartait);
        poartait!!.setOnClickListener {
            //                if (App.Companion.getLoged() == null) {
            //                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), ACTIVITY_REQUEST_LOGIN_IN);
            //                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            //                } else {
            //                    startActivity(new Intent(MainActivity.this, UserCenterActivity.class));
            //                }
        }
        if (mGalleryFragment == null) {
            mGalleryFragment = GalleryFragment()
        }
        contentFragment = mGalleryFragment
        supportFragmentManager.beginTransaction().replace(R.id.fl_main, mGalleryFragment).commit()

        //        MusicPlayerActivity.testStart(mContext);
        //        WebWorkSpaceActivity.debugStart(MainActivity.this);


        //        testGraphGL();
    }

    //    private void testGraphGL() {

    //        Net.Companion.initGraphQL();
    //        FeedQuery
    //    }

    private fun verificticonClipBoard() {

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var text = ""
        try {
            if (clipboard != null && clipboard.hasText()) {

                val tmpText = clipboard.text
                if (tmpText != null && tmpText.length > 0) {
                    val builder = AlertDialog.Builder(mContext)
                    builder.setTitle("clipboard input:" + tmpText.toString())
                    text = tmpText.toString().trim { it <= ' ' }

                    if (StringUtils.isMobileNO(text)) {
                        builder.setItems(arrayOf("call", "新建联系人", "添加到联系人信息")) { dialog, which ->
                            if (which == 0) {
                                startActivity(IntentUtil.getCallPhoneIntent(tmpText.toString()))
                            } else if (which == 1) {

                            } else if (which == 2) {

                            }
                        }
                    } else if (StringUtils.isEmail(text)) {
                        builder.setItems(arrayOf("新建联系人", "添加到联系人信息")) { dialog, which -> }
                    } else {
                        ClipboardInfoDialog(clipboard.text.toString()).show(fragmentManager, "clipinfo")
                    }


                    // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
                    val clipData = ClipData.newPlainText(null, "")

                    // 把数据集设置（复制）到剪贴板
                    clipboard.primaryClip = clipData
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            //            text = "";
        }

    }


    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (contentFragment!!.backable) {
                contentFragment!!.onBack()
            } else {
                super.onBackPressed()

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        //        if (id == R.id.nav_search) {
        //            SearchActivity.start(mContext, 0);
        //        } else
        //        if (id == R.id.nav_openGL) {
        //            startActivity(new Intent(mContext, OpenGLActivity.class));
        //        } else
        if (id == R.id.nav_camera) {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        ACTIVITY_REQUEST_TAKE_PHOTO)

            } else {
                //有权限，直接拍照
                //                takePhoto();
                // Create an image file name
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "JPEG_" + timeStamp + "_"
                val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)//由getExternalFilesDir(),以及getFilesDir()创建的目录，应用卸载后会被删除！
                //
                var image: File? = null
                try {
                    image = File.createTempFile(
                            imageFileName, /* prefix */
                            ".jpg", /* suffix */
                            storageDir      /* directory */
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                //                // Save a file: path for use with ACTION_VIEW intents
                //                mCurrentPhotoPath = "file:" + image.getAbsolutePath();
                startActivityForResult(IntentUtil.TakePhotoIntent(image!!.absolutePath), 1)

                //                startActivity(new Intent(MainActivity.this, Camera3Activity.class));

                //                startActivityForResult(new Intent(MainActivity.this, Camera1Activity.class),1);

            }
            // Handle the camera action
        } else if (id == R.id.nav_fingerprint) {
            startActivity(Intent(mContext, FingerPrintBaseActivity::class.java))

        } else if (id == R.id.nav_augmented_image) {
            startActivity(Intent(mContext, AugmentedImageActivity::class.java))
        } else if (id == R.id.nav_solar) {
            startActivity(Intent(mContext, SolarActivity::class.java))
        } else if (id == R.id.nav_cloudanchor) {
            startActivity(Intent(mContext, CloudAnchorActivity::class.java))
        } else if (id == R.id.nav_net_image) {

            startActivity(Intent(this@MainActivity, NetImageActivity::class.java))
        } else if (id == R.id.nav_gallery) {
            if (mGalleryFragment == null) {
                mGalleryFragment = GalleryFragment()
            }
            contentFragment = mGalleryFragment
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, mGalleryFragment).commit()

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_todo) {
            ToDoMainActivity.start(mContext)
        } else if (id == R.id.nav_download) {
            startActivity(Intent(this@MainActivity, DownloadActivity::class.java))
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            startActivity(Intent(this@MainActivity, WifiChartActivity::class.java))
        } else if (id == R.id.nav_netdata) {
            //            if (ContextCompat.checkSelfPermission(
            //                    MainActivity.this,
            //                    Manifest.permission.PACKAGE_USAGE_STATS)
            //                    != PackageManager.PERMISSION_GRANTED) {
            //                //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
            //                ActivityCompat.requestPermissions(this,
            //                        new String[]{
            //                                Manifest.permission.PACKAGE_USAGE_STATS},
            //                        REQUEST_PACKAGE_MANAGER);
            //
            //            } else {
            //                startActivity(new Intent(MainActivity.this, NetDataWatcherActivity.class));
            //            }
            startActivity(Intent(this@MainActivity, NetDataWatcherActivity::class.java))

        } else if (id == R.id.nav_dashang) {
            // HTTPS://QR.ALIPAY.COM/FKX04844O7GX5QDURDKJ09      --------2快\
            //HTTPS://QR.ALIPAY.COM/FKX089840DT0DYAC0370B6       ---------自定义金额
            if (AlipayZeroSdk.hasInstalledAlipayClient(this@MainActivity)) {
                AlipayZeroSdk.startAlipayClient(this@MainActivity, "FKX08978KRAPHCYHDXIQ10")
            } else {
                //                SnackbarUtils.showSnackbar(MainActivity.this,"谢谢，您没有安装支付宝客户端", true);
            }
        }//        else if (id == R.id.nav_zhihu) {
        //            startActivity(new Intent(MainActivity.this, ZhihuActivity.class));
        //        }
        //        else if (id == R.id.nav_ble) {
        //            startActivity(new Intent(MainActivity.this, BluetoothActivity.class));
        //        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_TAKE_PHOTO_PERMISSION) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
                //                takePhoto();
                CameraUtil.takePhotoWithSystemApp(this@MainActivity, null)
            } else {
                //                Toast.makeText(this, "CAMERA PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
            return
        } else if (requestCode == REQUEST_ACCESS_NET_PERMISSION) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tv_ip!!.text = IP.getHostIp()!! + ":54321"
            } else {
            }
        } else if (requestCode == REQUEST_PACKAGE_MANAGER) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this@MainActivity, NetDataWatcherActivity::class.java))
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    //    String mCurrentPhotoPath;

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            1 -> if (resultCode == -1) {

                val path = data.getStringExtra("url")

                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val file = File(path)
                val uri = Uri.fromFile(file)
                intent.data = uri
                sendBroadcast(intent)

                //                String path = data.getStringExtra("url");
                startActivity(
                        Intent(this@MainActivity, ImageEditorActivity::class.java)
                                .putExtra("url", path))

            }
            ACTIVITY_REQUEST_LOGIN_IN -> if (resultCode == 1) {
                val user = data.getParcelableExtra<User>("user")

                insertUserInfo(user)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun insertUserInfo(user: User) {

        name!!.text = user.name
        sub!!.text = user.tel

        runOnUiThread { Glide.with(this@MainActivity).load(user.avarat).into(poartait!!) }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
            if (rotVecValues == null) {
                rotVecValues = FloatArray(event.values.size)
            }
            for (i in rotVecValues!!.indices) {
                rotVecValues!![i] = event.values[i]
            }

            if (rotVecValues != null) {
                SensorManager.getQuaternionFromVector(rotQ, rotVecValues)
                SensorManager.getRotationMatrixFromVector(rotvecR, rotVecValues)
                SensorManager.getOrientation(rotvecR, rotvecOrientValues)
            }


            if (rotvecOrientValues != null) {
                msg += String.format("Rotation Vector Sensor:\n" +
                        "azimuth %7.3f\n" +
                        "pitch %7.3f\n" +
                        "roll %7.3f\n" +
                        "w,x,y,z %6.2f,%6.2f,%6.2f,%6.2f\n",
                        Math.toDegrees(rotvecOrientValues[0].toDouble()),
                        Math.toDegrees(rotvecOrientValues[1].toDouble()),
                        Math.toDegrees(rotvecOrientValues[2].toDouble()),
                        rotQ[0], rotQ[1], rotQ[2], rotQ[3])
            }//                    && mRotation == Surface.ROTATION_0
            tv_fx!!.text = msg
        }
    }

    internal var msg: String?=null

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }

    companion object {

        val ACTIVITY_REQUEST_TAKE_PHOTO = 1
        val ACTIVITY_REQUEST_LOGIN_IN = 2


        val REQUEST_TAKE_PHOTO_PERMISSION = 1
        val REQUEST_ACCESS_NET_PERMISSION = 2
        val REQUEST_COARSE_LOCATION = 3
        val REQUEST_PACKAGE_MANAGER = 4
    }
}
