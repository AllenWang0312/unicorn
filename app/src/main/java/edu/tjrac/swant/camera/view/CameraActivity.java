package edu.tjrac.swant.camera.view;//package edu.tjrac.swant.unicorn;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tjrac.swant.unicorn.R;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private ImageView back;
    @BindView(R.id.iv_switch) ImageView position;//返回和切换前后置摄像头
    @BindView(R.id.sv_camera) SurfaceView surface;
    @BindView(R.id.iv_camera) ImageView shutter;//快门
    private SurfaceHolder holder;
    private Camera camera;//声明相机
    private String filepath = "";//照片保存路径
    private int cameraPosition = 1;//0代表前置摄像头，1代表后置摄像头

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//没有标题
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//拍照过程屏幕一直处于高亮
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        //SCREEN_ORIENTATION_BEHIND： 继承Activity堆栈中当前Activity下面的那个Activity的方向
        //SCREEN_ORIENTATION_LANDSCAPE： 横屏(风景照) ，显示时宽度大于高度
        //SCREEN_ORIENTATION_PORTRAIT： 竖屏 (肖像照) ， 显示时高度大于宽度
        //SCREEN_ORIENTATION_SENSOR  由重力感应器来决定屏幕的朝向,它取决于用户如何持有设备,当设备被旋转时方向会随之在横屏与竖屏之间变化
        //SCREEN_ORIENTATION_NOSENSOR： 忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)
        //SCREEN_ORIENTATION_UNSPECIFIED： 未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择
        //SCREEN_ORIENTATION_USER： 用户当前的首选方向

        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.iv_switch)
    void switchCameras() {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraPosition == 1) {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                    camera = Camera.open(i);
                    try {
                        camera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    camera.startPreview();
                    cameraPosition = 0;
                    break;
                }
            } else {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                    camera = Camera.open(i);
                    try {
                        camera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    camera.startPreview();
                    cameraPosition = 1;
                    break;
                }
            }
        }

    }

    @OnClick(R.id.iv_camera)
    void takePhoto() {
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    Camera.Parameters params = camera.getParameters();
                    params.setPictureFormat(PixelFormat.JPEG);
                    params.setPreviewSize(800, 400);
                    camera.setParameters(params);
                    camera.takePicture(null, null, jpeg);
                }
            }
        });
    }
    //创建jpeg图片回调数据对象
    Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                //自定义文件保存路径  以拍摄时间区分命名
                filepath = "/sdcard/Messages/MyPictures/"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ ".jpg";
                File file = new File(filepath);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩的流里面
                bos.flush();// 刷新此缓冲区的输出流
                bos.close();// 关闭此输出流并释放与此流有关的所有系统资源
                camera.stopPreview();//关闭预览 处理数据
                camera.startPreview();//数据处理完后继续开始预览
                bitmap.recycle();//回收bitmap空间
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(camera == null) {
            camera = Camera.open();
            try {
                camera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
                camera.startPreview();//开始预览
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
        holder = null;
        surface = null;
    }
}
