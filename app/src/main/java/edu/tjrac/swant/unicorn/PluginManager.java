package edu.tjrac.swant.unicorn;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by wpc on 2018/5/13.
 */

public class PluginManager {
    private static PluginManager instance = new PluginManager();

    public static PluginManager getInstance(){
        return instance;
    }

    private Context context;

    public void setContext(Context context){
        this.context = context.getApplicationContext();
    }

    public void loadPath(String path){
        setEntryName(path);
        setClassLoader(path);
    }

    private String entryName;

    private void setEntryName(String path){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path,PackageManager.GET_ACTIVITIES);
        entryName=packageInfo.activities[0].name;
    }

    public String getEntryName(){
        return entryName;
    }

    private DexClassLoader dexClassLoader;

    private void setClassLoader(String path){
        File dexOutFile = context.getDir("dex",Context.MODE_APPEND);
        dexClassLoader = new DexClassLoader(path,dexOutFile.getAbsoluteFile().getAbsolutePath()
        ,null,context.getClassLoader());

    }

    public DexClassLoader getDexClassLoader(){
        return dexClassLoader;
    }

    private Resources resources;

    public Resources getResources(){
        return resources;
    }

    public void setResources(String path){
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            resources = new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources()
            .getConfiguration());

        }catch (Exception e){

        }
    }
}
