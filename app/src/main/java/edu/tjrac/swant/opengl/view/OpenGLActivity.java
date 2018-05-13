package edu.tjrac.swant.opengl.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yckj.baselib.util.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tjrac.swant.opengl.ColorPicker;
import edu.tjrac.swant.opengl.bean.GLRenderer;
import edu.tjrac.swant.unicorn.R;

public class OpenGLActivity extends AppCompatActivity {


    public static String res_type = ".stl";

    String filePath;


    boolean supportsEs2;
    @BindView(R.id.ambient) TextView mTvR;
    @BindView(R.id.diffuse) TextView mTvG;
    @BindView(R.id.specular) TextView mTvB;
    @BindView(R.id.light) TextView mTvA;

    @BindView(R.id.gl) GLSurfaceView mGLSurfaceView;


    private float rotateDegreen = 0;
    private GLRenderer mGLRenderer;

    float x, y, startX, startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filePath = getIntent().getStringExtra("filePath");
        setContentView(R.layout.activity_open_gl);
        ButterKnife.bind(this);

        checkSupported();
        if (supportsEs2) {
            mGLRenderer = new GLRenderer(filePath);
            mGLSurfaceView.setRenderer(mGLRenderer);
        } else {
            Toast.makeText(this, "当前设备不支持OpenGL ES 2.0!", Toast.LENGTH_SHORT).show();
        }


        mGLSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                L.i("onTouch:" + event.getAction(), event.getX() + "_" + event.getY());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startX = event.getX();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mGLRenderer.rotate((event.getX() - startX) * 5);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                return false;
            }
        });
    }


    @OnClick({R.id.ambient, R.id.diffuse, R.id.specular, R.id.light})
    public void onClick(View v) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setPositive(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                v.setBackgroundColor(colorPicker.getColor());
                switch (v.getId()) {
                    case R.id.ambient:
                        mGLRenderer.setColors(0, colorPicker.getColor());
                        break;
                    case R.id.diffuse:
                        mGLRenderer.setColors(1, colorPicker.getColor());

                        break;
                    case R.id.specular:
                        mGLRenderer.setColors(2, colorPicker.getColor());

                        break;
                    case R.id.light:
                        mGLRenderer.setColors(3, colorPicker.getColor());

                        break;

                }
            }
        });

        colorPicker.show(getFragmentManager(), "colorPicker");
    }

    public void rotate(float degree) {
        mGLRenderer.rotate(degree);
        mGLSurfaceView.invalidate();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            rotate(rotateDegreen);
        }
    };


    public void checkSupported() {

        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo info = manager.getDeviceConfigurationInfo();
        supportsEs2 = info.reqGlEsVersion >= 0x2000;

        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));
        supportsEs2 = supportsEs2 || isEmulator;
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mGLSurfaceView != null) {
            mGLSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGLSurfaceView != null) {
            mGLSurfaceView.onResume();
//            new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//                    while (true) {
//                        try {
//                            sleep(100);
//                            rotateDegreen += 5;
//                            mHandler.sendEmptyMessage(0x001);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
        }
    }

    public static void start(Context activity, String absolutePath) {
        activity.startActivity(new Intent(activity, OpenGLActivity.class)
                .putExtra("filePath", absolutePath));
    }
}
