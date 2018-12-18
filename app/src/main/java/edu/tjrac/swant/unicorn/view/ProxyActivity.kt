package edu.tjrac.swant.unicorn.view

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.yckj.baselib.common.AppInterface
import com.yckj.baselib.common.base.BaseActivity
import edu.tjrac.swant.unicorn.PluginManager
import java.io.File
import java.lang.reflect.InvocationTargetException

/**
 * Created by wpc on 2018/5/13.
 */

class ProxyActivity : BaseActivity() {

    private var clasName = ""
    private var appInterface: AppInterface? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clasName = intent.getStringExtra("className")
        try {
            val activityClass = classLoader.loadClass(clasName)
            val constructor = activityClass.getConstructor()
            val instance = constructor.newInstance()
            appInterface = instance as AppInterface
            appInterface!!.attach(this)
            appInterface!!.onCreate(Bundle())

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

    }

    override fun onStart() {
        super.onStart()
        appInterface!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        appInterface!!.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        appInterface!!.onDestroy()
    }


    fun load(view: View) {
        val file = File(Environment.getExternalStorageDirectory(), "plugin.apk")
        PluginManager.getInstance().loadPath(file.absolutePath)
    }

    override fun getClassLoader(): ClassLoader {
        return PluginManager.getInstance().dexClassLoader
    }

    override fun getResources(): Resources {
        return PluginManager.getInstance().resources
    }

    fun click(view: View) {
        val intent = Intent(this, ProxyActivity::class.java)
        intent.putExtra("className", PluginManager.getInstance().entryName)
        startActivity(intent)
    }


}
