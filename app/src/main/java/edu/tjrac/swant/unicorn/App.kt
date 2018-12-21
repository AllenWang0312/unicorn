package edu.tjrac.swant.unicorn


//import com.squareup.leakcanary.LeakCanary

import edu.tjrac.swant.filesystem.CarryPathDialogFragment
import edu.tjrac.swant.kotlin.baselib.common.BaseApplication

/**
 * Created by wpc on 2018/1/13 0013.
 */

class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //fix someproblems;

        //Install CustomActivityOnCrash
//        if (BuildConfig.DEBUG) {
//
//        } else {
//            CustomActivityOnCrash.install(this)
//            CustomActivityOnCrash.setLaunchErrorActivityWhenInBackground(false)
//            CustomActivityOnCrash.setShowErrorDetails(true)
//            CustomActivityOnCrash.setDefaultErrorActivityDrawable(R.mipmap.ufo_ed)
//            CustomActivityOnCrash.setEventListener(MyEventListener())
//
//            CustomActivityOnCrash.setEnableAppRestart(true)//true/false 重启/关闭
//            CustomActivityOnCrash.setRestartActivityClass(MainActivity::class.java)
//        }
        BaseApplication.instance = this
        //        Realm.init(this);

//        LeakCanary.install(this)
        //        LocationUtils.getInstance(getApplicationContext()).showLocation();

        //        JPushInterface.setDebugMode(true);
        //        JPushInterface.init(this);
        //        JPushInterface.setAlias(this, "18814837150", new TagAliasCallback() {
        //            @Override
        //            public void gotResult(int i, String s, Set<String> set) {
        //                L.i("setAlias:","success");
        //            }
        //        });

        CarryPathDialogFragment.initCarrySetting()

    }

//    private class MyEventListener : CustomActivityOnCrash.EventListener {
//        override fun onLaunchErrorActivity() {
//            Log.i("bqt", "onLaunchErrorActivity")
//        }
//
//        override fun onRestartAppFromErrorActivity() {
//            Log.i("bqt", "onRestartAppFromErrorActivity")
//        }
//
//        override fun onCloseAppFromErrorActivity() {
//            Log.i("bqt", "onCloseAppFromErrorActivity")
//        }
//    }
//
//    companion object {
//
//        var loged: User? = null
//    }

}
