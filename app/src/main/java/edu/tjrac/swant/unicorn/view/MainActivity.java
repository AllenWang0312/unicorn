package edu.tjrac.swant.unicorn.view;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yckj.baselib.common.base.BaseFragment;
import com.yckj.baselib.util.IP;
import com.yckj.baselib.util.IntentUtil;
import com.yckj.baselib.util.LocationUtils;
import com.yckj.baselib.util.StringUtils;
import com.yckj.baselib.util.T;
import com.yckj.baselib.util.uncom.CameraUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.arcore.augmentedimage.AugmentedImageActivity;
import edu.tjrac.swant.arcore.cloudanchor.CloudAnchorActivity;
import edu.tjrac.swant.arcore.solar.SolarActivity;
import edu.tjrac.swant.download.DownloadActivity;
import edu.tjrac.swant.filesystem.view.GalleryFragment;
import edu.tjrac.swant.filesystem.view.ImageEditorActivity;
import edu.tjrac.swant.fingerprint.FingerPrintBaseActivity;
import edu.tjrac.swant.netimage.view.NetImageActivity;
import edu.tjrac.swant.todo.view.ClipboardInfoDialog;
import edu.tjrac.swant.todo.view.ToDoMainActivity;
import edu.tjrac.swant.trafficmonitor.NetDataWatcherActivity;
import edu.tjrac.swant.unicorn.AlipayZeroSdk;
import edu.tjrac.swant.unicorn.App;
import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.SharedStartActivity;
import edu.tjrac.swant.unicorn.bean.User;
import edu.tjrac.swant.unicorn.bean.response.UpdataLocationResult1003;
import edu.tjrac.swant.unicorn.stale.NetRequire;
import edu.tjrac.swant.wifi.FileWifiShareServer;
import edu.tjrac.swant.wifi.WifiChartActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends SharedStartActivity
        implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.fl_main) FrameLayout frame;

    BaseFragment contentFragment;

    GalleryFragment mGalleryFragment;


//    private UserBean loged;

    ImageView poartait;
    TextView name, sub, tv_ip, tv_mac, tv_location, tv_fx;
    Switch sw_wifi, sw_ble, sw_location, sw_fx;
    private float[] rotVecValues = null;
    private float[] rotvecR = new float[9], rotQ = new float[4];
    private float[] rotvecOrientValues = new float[3];

    LocationUtils loc;
    BluetoothAdapter bleAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        verificticonClipBoard();

        if (App.Companion.getLoged() == null) {
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

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        bleAdapter = BluetoothAdapter.getDefaultAdapter();

        View headview = navigationView.getHeaderView(0);
        tv_ip = headview.findViewById(R.id.tv_ip);
        tv_mac = headview.findViewById(R.id.tv_mac);
        tv_location = headview.findViewById(R.id.sw_location);
        name = headview.findViewById(R.id.tv_username);
        poartait = headview.findViewById(R.id.iv_portait);


        sub = headview.findViewById(R.id.tv_sub);

        sw_wifi = headview.findViewById(R.id.sw_wifi);
        sw_ble = headview.findViewById(R.id.sw_ble);
        sw_location = headview.findViewById(R.id.sw_location);
        sw_fx = headview.findViewById(R.id.sw_fx);

        sw_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(new Intent(
                            MainActivity.this, FileWifiShareServer.class));

                } else {
                    stopService(new Intent(
                            MainActivity.this, FileWifiShareServer.class));
                }
            }
        });
        sw_ble.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bleAdapter != null) {
                    if (isChecked) {

                        if (!bleAdapter.isEnabled()) {
                            bleAdapter.enable();
                            tv_mac.setText(bleAdapter.getAddress());
                        }
                    } else {
                        if (bleAdapter.isEnabled()) {
                            bleAdapter.disable();
                        }
                    }
                }

            }
        });
        sw_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loc = LocationUtils.getInstance(MainActivity.this);
                    Location location = loc.init();
                    if (location != null) {
                        tv_location.setText(LocationUtils.formatLocation(location));
                        App.Companion.getLoged().setLocation(location);

                        NetRequire.requireJson("1003", App.Companion.getLoged(), new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String result = response.body().string();
                                if (!StringUtils.isEmpty(result)) {
                                    Log.i("1003", result);
                                    UpdataLocationResult1003 res = new Gson().fromJson(result, UpdataLocationResult1003.class);
                                    if (res.getCode() == 200) {
                                        T.show(MainActivity.this, "updata location success");
                                    } else {
                                        T.show(MainActivity.this, "updata location faild");

                                    }
                                }
                            }
                        });
                    }
                    loc.addLocationListener(new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            location.getAccuracy();
//                            loc.setLocation(location);
                            tv_location.setText(LocationUtils.formatLocation(location));
                            T.show(MainActivity.this, LocationUtils.formatLocation(location));
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });

                    sw_fx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {

                            }
                        }
                    });
                } else {

                }

            }
        });
        sw_fx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

            }
        });
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_NETWORK_STATE},
                    REQUEST_TAKE_PHOTO_PERMISSION);

        } else {
            tv_ip.setText(IP.getHostIp() + ":54321");
        }
        poartait = headview.findViewById(R.id.iv_portait);
//        Glide.with(MainActivity.this).load("http://192.168.43.80:8082/userinfo/1/avarat.png")
//                .into(poartait);
        poartait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.Companion.getLoged() == null) {
//                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), ACTIVITY_REQUEST_LOGIN_IN);
//                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                } else {
//                    startActivity(new Intent(MainActivity.this, UserCenterActivity.class));
                }
            }
        });
        if (mGalleryFragment == null) {
            mGalleryFragment = new GalleryFragment();
        }
        contentFragment = mGalleryFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, mGalleryFragment).commit();

//        MusicPlayerActivity.testStart(mContext);
//        WebWorkSpaceActivity.debugStart(MainActivity.this);


//        testGraphGL();
    }

    private void testGraphGL() {

        Net.Companion.initGraphQL();
//        FeedQuery
    }

    private void verificticonClipBoard() {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String text = "";
        try {
            if (clipboard != null && clipboard.hasText()) {

                CharSequence tmpText = clipboard.getText();
                if (tmpText != null && tmpText.length() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("clipboard input:" + tmpText.toString());
                    text = tmpText.toString().trim();

                    if (StringUtils.isMobileNO(text)) {
                        builder.setItems(new String[]{"call", "新建联系人", "添加到联系人信息"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    startActivity(IntentUtil.getCallPhoneIntent(tmpText.toString()));
                                } else if (which == 1) {

                                } else if (which == 2) {

                                }
                            }
                        });
                    } else if (StringUtils.isEmail(text)) {
                        builder.setItems(new String[]{"新建联系人", "添加到联系人信息"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                    } else {
                        new ClipboardInfoDialog(clipboard.getText().toString()).show(getFragmentManager(), "clipinfo");
                    }


// 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
                    ClipData clipData = ClipData.newPlainText(null, "");

// 把数据集设置（复制）到剪贴板
                    clipboard.setPrimaryClip(clipData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            text = "";
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (contentFragment.backable) {
                contentFragment.onBack();
            } else {
                super.onBackPressed();

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static final int ACTIVITY_REQUEST_TAKE_PHOTO = 1;
    public static final int ACTIVITY_REQUEST_LOGIN_IN = 2;


    public static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;
    public static final int REQUEST_ACCESS_NET_PERMISSION = 2;
    public static final int REQUEST_COARSE_LOCATION = 3;
    public static final int REQUEST_PACKAGE_MANAGER = 4;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_search) {
//            SearchActivity.start(mContext, 0);
//        } else
//        if (id == R.id.nav_openGL) {
//            startActivity(new Intent(mContext, OpenGLActivity.class));
//        } else
        if (id == R.id.nav_camera) {
            if (ContextCompat.checkSelfPermission(
                    MainActivity.this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.CAMERA},
                        ACTIVITY_REQUEST_TAKE_PHOTO);

            } else {
                //有权限，直接拍照
//                takePhoto();
                // Create an image file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//由getExternalFilesDir(),以及getFilesDir()创建的目录，应用卸载后会被删除！
//
                File image = null;
                try {
                    image = File.createTempFile(
                            imageFileName,  /* prefix */
                            ".jpg",         /* suffix */
                            storageDir      /* directory */
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                // Save a file: path for use with ACTION_VIEW intents
//                mCurrentPhotoPath = "file:" + image.getAbsolutePath();
                startActivityForResult(IntentUtil.TakePhotoIntent(image.getAbsolutePath()), 1);

//                startActivity(new Intent(MainActivity.this, Camera3Activity.class));

//                startActivityForResult(new Intent(MainActivity.this, Camera1Activity.class),1);

            }
            // Handle the camera action
        } else if (id == R.id.nav_fingerprint) {
            startActivity(new Intent(mContext, FingerPrintBaseActivity.class));

        } else if (id == R.id.nav_augmented_image) {
            startActivity(new Intent(mContext, AugmentedImageActivity.class));
        } else if (id == R.id.nav_solar) {
            startActivity(new Intent(mContext, SolarActivity.class));
        } else if (id == R.id.nav_cloudanchor) {
            startActivity(new Intent(mContext, CloudAnchorActivity.class));
        } else if (id == R.id.nav_net_image) {

            startActivity(new Intent(MainActivity.this, NetImageActivity.class));
        } else if (id == R.id.nav_gallery) {
            if (mGalleryFragment == null) {
                mGalleryFragment = new GalleryFragment();
            }
            contentFragment = mGalleryFragment;
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, mGalleryFragment).commit();

        }
//        else if (id == R.id.nav_ble) {
//            startActivity(new Intent(MainActivity.this, BluetoothActivity.class));
//        }
        else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_todo) {
            ToDoMainActivity.start(mContext);
        }
//        else if (id == R.id.nav_zhihu) {
//            startActivity(new Intent(MainActivity.this, ZhihuActivity.class));
//        }
        else if (id == R.id.nav_download) {
            startActivity(new Intent(MainActivity.this, DownloadActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this, WifiChartActivity.class));
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
            startActivity(new Intent(MainActivity.this, NetDataWatcherActivity.class));

        } else if (id == R.id.nav_dashang) {
            // HTTPS://QR.ALIPAY.COM/FKX04844O7GX5QDURDKJ09      --------2快\
            //HTTPS://QR.ALIPAY.COM/FKX089840DT0DYAC0370B6       ---------自定义金额
            if (AlipayZeroSdk.hasInstalledAlipayClient(MainActivity.this)) {
                AlipayZeroSdk.startAlipayClient(MainActivity.this, "FKX08978KRAPHCYHDXIQ10");
            } else {
//                SnackbarUtils.showSnackbar(MainActivity.this,"谢谢，您没有安装支付宝客户端", true);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_TAKE_PHOTO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
//                takePhoto();
                CameraUtil.takePhotoWithSystemApp(MainActivity.this, null);
            } else {
//                Toast.makeText(this, "CAMERA PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
            return;
        } else if (requestCode == REQUEST_ACCESS_NET_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tv_ip.setText(IP.getHostIp() + ":54321");
            } else {
            }
        } else if (requestCode == REQUEST_PACKAGE_MANAGER) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(MainActivity.this, NetDataWatcherActivity.class));
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


//    String mCurrentPhotoPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == -1) {

                    String path = data.getStringExtra("url");

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File file = new File(path);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    sendBroadcast(intent);

                    //                String path = data.getStringExtra("url");
                    startActivity(
                            new Intent(MainActivity.this, ImageEditorActivity.class)
                                    .putExtra("url", path));

                }

                break;
            case ACTIVITY_REQUEST_LOGIN_IN:
                if (resultCode == 1) {
                    User user = data.getParcelableExtra("user");

                    insertUserInfo(user);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertUserInfo(User user) {

        name.setText(user.getName());
        sub.setText(user.getTel());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Glide.with(MainActivity.this).load(user.getAvarat()).into(poartait);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            if (rotVecValues == null) {
                rotVecValues = new float[event.values.length];
            }
            for (int i = 0; i < rotVecValues.length; i++) {
                rotVecValues[i] = event.values[i];
            }

            if (rotVecValues != null) {
                SensorManager.getQuaternionFromVector(rotQ, rotVecValues);
                SensorManager.getRotationMatrixFromVector(rotvecR, rotVecValues);
                SensorManager.getOrientation(rotvecR, rotvecOrientValues);
            }


            if (rotvecOrientValues != null
//                    && mRotation == Surface.ROTATION_0
                    ) {
                msg += String.format("Rotation Vector Sensor:\n" +
                                "azimuth %7.3f\n" +
                                "pitch %7.3f\n" +
                                "roll %7.3f\n" +
                                "w,x,y,z %6.2f,%6.2f,%6.2f,%6.2f\n",
                        Math.toDegrees(rotvecOrientValues[0]),
                        Math.toDegrees(rotvecOrientValues[1]),
                        Math.toDegrees(rotvecOrientValues[2]),
                        rotQ[0], rotQ[1], rotQ[2], rotQ[3]);
            }
            tv_fx.setText(msg);
        }
    }

    String msg;

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
