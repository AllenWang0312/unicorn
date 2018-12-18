package edu.tjrac.swant.opengl.view

import android.app.ActivityManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ConfigurationInfo
import android.opengl.GLSurfaceView
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.yckj.baselib.util.L

import edu.tjrac.swant.opengl.ColorPicker
import edu.tjrac.swant.opengl.bean.GLRenderer
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_open_gl.*

class OpenGLActivity : AppCompatActivity() {

    internal var filePath: String=""


    internal var supportsEs2: Boolean = false
    private val rotateDegreen = 0f
    private var mGLRenderer: GLRenderer? = null

    internal var x: Float = 0.toFloat()
    internal var y: Float = 0.toFloat()
    internal var startX: Float = 0.toFloat()
    internal var startY: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filePath = intent.getStringExtra("filePath")
        setContentView(R.layout.activity_open_gl)


        checkSupported()
        if (supportsEs2) {
            mGLRenderer = GLRenderer(filePath)
            gl.setRenderer(mGLRenderer)
        } else {
            Toast.makeText(this, "当前设备不支持OpenGL ES 2.0!", Toast.LENGTH_SHORT).show()
        }


        gl.setOnTouchListener(View.OnTouchListener { v, event ->
            L.i("onTouch:" + event.action, event.x.toString() + "_" + event.y)
            if (event.action == MotionEvent.ACTION_DOWN) {
                startX = event.x
            } else if (event.action == MotionEvent.ACTION_MOVE) {
                mGLRenderer!!.rotate((event.x - startX) * 5)
            } else if (event.action == MotionEvent.ACTION_UP) {

            }
            false
        })
    }


//    @OnClick(R.id.ambient, R.id.diffuse, R.id.specular, R.id.light)
    fun onClick(v: View) {
        val colorPicker = ColorPicker()
        colorPicker.setPositive(DialogInterface.OnClickListener { dialog, which ->
            v.setBackgroundColor(colorPicker.color)
            when (v.id) {
                R.id.ambient -> mGLRenderer!!.setColors(0, colorPicker.color)
                R.id.diffuse -> mGLRenderer!!.setColors(1, colorPicker.color)
                R.id.specular -> mGLRenderer!!.setColors(2, colorPicker.color)
                R.id.light -> mGLRenderer!!.setColors(3, colorPicker.color)
            }
        })

        colorPicker.show(fragmentManager, "colorPicker")
    }

    fun rotate(degree: Float) {
        mGLRenderer!!.rotate(degree)
        gl.invalidate()
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            rotate(rotateDegreen)
        }
    }


    fun checkSupported() {

        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = manager.deviceConfigurationInfo
        supportsEs2 = info.reqGlEsVersion >= 0x2000

        val isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"))
        supportsEs2 = supportsEs2 || isEmulator
    }


    override fun onPause() {
        super.onPause()
        if (gl != null) {
            gl.onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (gl != null) {
            gl.onResume()
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

    companion object {


        var res_type = ".stl"

        fun start(activity: Context, absolutePath: String) {
            activity.startActivity(Intent(activity, OpenGLActivity::class.java)
                    .putExtra("filePath", absolutePath))
        }
    }
}
