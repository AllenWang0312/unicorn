package edu.tjrac.swant.unicorn.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;

import com.yckj.baselib.common.AppInterface;
import com.yckj.baselib.common.base.BaseActivity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import edu.tjrac.swant.unicorn.PluginManager;

/**
 * Created by wpc on 2018/5/13.
 */

public class ProxyActivity extends BaseActivity{

    private String clasName="";
    private AppInterface appInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clasName = getIntent().getStringExtra("className");
        try {
            Class activityClass=getClassLoader().loadClass(clasName);
           Constructor constructor = activityClass.getConstructor();
           Object instance = constructor.newInstance();
           appInterface= (AppInterface) instance;
           appInterface.attach(this);
           appInterface.onCreate(new Bundle());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        appInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        appInterface.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appInterface.onDestroy();
    }


    public void load(View view){
        File file=new File(Environment.getExternalStorageDirectory(),"plugin.apk");
        PluginManager.getInstance().loadPath(file.getAbsolutePath());
    }

    public ClassLoader getClassLoader(){
        return PluginManager.getInstance().getDexClassLoader();
    }

    public Resources getResources(){
        return PluginManager.getInstance().getResources();
    }

    public void click(View view){
        Intent intent=new Intent(this,ProxyActivity.class);
        intent.putExtra("className",PluginManager.getInstance().getEntryName());
        startActivity(intent);
    }






}
