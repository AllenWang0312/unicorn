package edu.tjrac.swant.camera.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.yckj.baselib.util.FileUtils;
import com.yckj.baselib.util.encode.BitmapUtils;

import java.io.File;
import java.io.IOException;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/1/15 0015.
 */

public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
//    private ImageView iv_show;
    private int viewWidth, viewHeight;//mSurfaceView的宽和高
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
//        iv_show = findViewById(R.id.iv_show_camera2_activity);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view_camera2_activity);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_camera_take_photo);

        mSurfaceHolder = mSurfaceView.getHolder();
        // mSurfaceView 不需要自己的缓冲区
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // mSurfaceView添加回调
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) { //SurfaceView创建
                // 初始化Camera
                initCamera();
            }
            @SuppressLint("NewApi")
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//               Canvas can =    mSurfaceHolder.getSurface().lockHardwareCanvas();
//                Paint p = new Paint();
//                p.setColor(Color.WHITE);
//                can.drawCircle(200, 200, 10, p);
//                mSurfaceHolder.getSurface().unlockCanvasAndPost(can);
//                new MyThread().run();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) { //SurfaceView销毁
                // 释放Camera资源
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.release();
                }
            }
        });
        //设置点击监听
        mSurfaceView.setOnClickListener(this);
        mFloatingActionButton.setOnClickListener(this);
    }

//    @SuppressLint("NewApi")
//    //内部类的内部类
//    class MyThread implements Runnable{
//        @Override
//        public void run() {
//           Canvas canvas =mSurfaceHolder.getSurface().lockHardwareCanvas();//获取画布
//            Paint mPaint = new Paint();
//            mPaint.setColor(Color.BLUE);
//            canvas.drawCircle(200, 200, 10, mPaint);
//            mSurfaceHolder.unlockCanvasAndPost(canvas);//解锁画布，提交画好的图像
//        }
//    }

    /**
     * SurfaceHolder 回调接口方法
     */

    private void initCamera() {
        mCamera = Camera.open();//默认开启后置
        mCamera.setDisplayOrientation(90);//摄像头进行旋转90°
        if (mCamera != null) {
            try {
                Camera.Parameters parameters = mCamera.getParameters();
                //设置预览照片的大小
                parameters.setPreviewFpsRange(viewWidth, viewHeight);
                //设置相机预览照片帧数
                parameters.setPreviewFpsRange(4, 10);
                //设置图片格式
                parameters.setPictureFormat(ImageFormat.JPEG);
                //设置图片的质量
                parameters.set("jpeg-quality", 90);
                //设置照片的大小
                parameters.setPictureSize(viewWidth, viewHeight);
                //通过SurfaceView显示预览
                mCamera.setPreviewDisplay(mSurfaceHolder);
                //开始预览
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 自动对焦 对焦成功后 就进行拍照
     */
    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {//对焦成功
//                T.show(CameraActivity.this, "对焦成功");
            }
        }
    };

    /**
     * 点击回调方法
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_camera_take_photo:
                mCamera.takePicture(new Camera.ShutterCallback() {//按下快门
                    @Override
                    public void onShutter() {
                        //按下快门瞬间的操作
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {//是否保存原始图片的信息

                    }
                }, pictureCallback);
                break;
            case R.id.surface_view_camera2_activity:
                if (mCamera == null) return;
                //自动对焦后拍照
                mCamera.autoFocus(autoFocusCallback);
                break;
        }
    }

    /**
     * 获取图片
     */
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            final Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);

            File image=new File(FileUtils.getAppImageDir(),System.currentTimeMillis()+".jpg");

            try {
                BitmapUtils.saveBitmapToJPG(resource,image);
                setResult(1,new Intent().putExtra("url",image.getAbsolutePath()));
            } catch (IOException e) {
                setResult(0);
                e.printStackTrace();
            }

            if (resource == null) {
                Toast.makeText(TakePhotoActivity.this, "拍照失败", Toast.LENGTH_SHORT).show();
            }else {
                final Matrix matrix = new Matrix();
                matrix.setRotate(90);
                final Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
                if (bitmap != null
//                    && iv_show != null && iv_show.getVisibility() == View.GONE
                        ) {
                    mCamera.stopPreview();
//                iv_show.setVisibility(View.VISIBLE);
                    mSurfaceView.setVisibility(View.GONE);
                    Toast.makeText(TakePhotoActivity.this, "拍照", Toast.LENGTH_SHORT).show();
//                iv_show.setImageBitmap(bitmap);
                }
            }
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mSurfaceView != null) {
            viewWidth = mSurfaceView.getWidth();
            viewHeight = mSurfaceView.getHeight();
        }
    }


}