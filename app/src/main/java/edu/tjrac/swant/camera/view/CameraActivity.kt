package edu.tjrac.swant.camera.view

//package edu.tjrac.swant.unicorn;

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import edu.tjrac.swant.unicorn.R
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private val back: ImageView? = null
    //    @BindView(R.id.iv_switch) ImageView iv_switch;//返回和切换前后置摄像头
    //    @BindView(R.id.sv_camera) sv_cameraView sv_camera;
    //    @BindView(R.id.iv_camera) ImageView iv_camera;//快门
    private val holder: SurfaceHolder? = null
    private var camera: Camera? = null//声明相机
    private var filepath = ""//照片保存路径
    private var cameraiv_switch = 1//0代表前置摄像头，1代表后置摄像头

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)//没有标题
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)//设置全屏
        this.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)//拍照过程屏幕一直处于高亮
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        //SCREEN_ORIENTATION_BEHIND： 继承Activity堆栈中当前Activity下面的那个Activity的方向
        //SCREEN_ORIENTATION_LANDSCAPE： 横屏(风景照) ，显示时宽度大于高度
        //SCREEN_ORIENTATION_PORTRAIT： 竖屏 (肖像照) ， 显示时高度大于宽度
        //SCREEN_ORIENTATION_SENSOR  由重力感应器来决定屏幕的朝向,它取决于用户如何持有设备,当设备被旋转时方向会随之在横屏与竖屏之间变化
        //SCREEN_ORIENTATION_NOSENSOR： 忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)
        //SCREEN_ORIENTATION_UNSPECIFIED： 未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择
        //SCREEN_ORIENTATION_USER： 用户当前的首选方向

        setContentView(R.layout.activity_camera)

    }

    //    @OnClick(R.id.iv_switch)
    internal fun switchCameras() {
        var cameraCount = 0
        val cameraInfo = Camera.CameraInfo()
        cameraCount = Camera.getNumberOfCameras()
        for (i in 0..cameraCount - 1) {
            Camera.getCameraInfo(i, cameraInfo)
            if (cameraiv_switch == 1) {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    camera!!.stopPreview()
                    camera!!.release()
                    camera = null
                    camera = Camera.open(i)
                    try {
                        camera!!.setPreviewDisplay(holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    camera!!.startPreview()
                    cameraiv_switch = 0
                    break
                }
            } else {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    camera!!.stopPreview()
                    camera!!.release()
                    camera = null
                    camera = Camera.open(i)
                    try {
                        camera!!.setPreviewDisplay(holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    camera!!.startPreview()
                    cameraiv_switch = 1
                    break
                }
            }
        }

    }

    //    @OnClick(R.id.iv_camera)
    internal fun takePhoto() {
        camera!!.autoFocus { success, camera ->
            if (success) {
                val params = camera.parameters
                params.pictureFormat = PixelFormat.JPEG
                params.setPreviewSize(800, 400)
                camera.parameters = params
                camera.takePicture(null, null, jpeg)
            }
        }
    }

    //创建jpeg图片回调数据对象
    internal var jpeg: Camera.PictureCallback = Camera.PictureCallback { data, camera ->
        // TODO Auto-generated method stub
        try {
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            //自定义文件保存路径  以拍摄时间区分命名
            filepath = "/sdcard/Messages/MyPictures/" + SimpleDateFormat("yyyyMMddHHmmss").format(Date()) + ".jpg"
            val file = File(filepath)
            val bos = BufferedOutputStream(FileOutputStream(file))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)//将图片压缩的流里面
            bos.flush()// 刷新此缓冲区的输出流
            bos.close()// 关闭此输出流并释放与此流有关的所有系统资源
            camera.stopPreview()//关闭预览 处理数据
            camera.startPreview()//数据处理完后继续开始预览
            bitmap.recycle()//回收bitmap空间
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (camera == null) {
            camera = Camera.open()
            try {
                camera!!.setPreviewDisplay(holder)//通过sv_cameraview显示取景画面
                camera!!.startPreview()//开始预览
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        var holder = holder
        camera!!.stopPreview()
        camera!!.release()
        camera = null
        holder = null
//        sv_camera = null
    }
}
