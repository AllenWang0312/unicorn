package edu.tjrac.swant.unicorn.widget;//package edu.tjrac.swant.unicorn.module.widget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yckj.baselib.util.FileUtils;

import java.io.File;
import java.util.ArrayList;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/5 0005.
 */

public class WidgetDataModel extends Service{

    static SharedPreferences sp;
    static Context context;
    static File file_dir;
    static String wallpaper_dir;
    static ArrayList<String> showAbleChilds;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context=getBaseContext();
        sp=getSp();
        return super.onStartCommand(intent, flags, startId);
    }

    public static File getFile_dir() {
        if(file_dir==null){
            file_dir=new File(getWallpaper_dir());
        }
        return file_dir;
    }


    public static String getWallpaper_dir() {
        if(wallpaper_dir==null){
            wallpaper_dir=getSp().getString("wallpaper_dir","");
        }
        return wallpaper_dir;
    }

    public static void setWallpaper_dir(Context con, String dir) {
        context=con;
        wallpaper_dir = dir;
        file_dir = new File(wallpaper_dir);
        showAbleChilds = getShowAbleChilds(context, file_dir);
    }

    public static ArrayList<String> getShowAbleChilds() {
        if(showAbleChilds==null){
            showAbleChilds=getShowAbleChilds(context,getFile_dir());
        }
        return showAbleChilds;
    }
    private static ArrayList<String> getShowAbleChilds(Context context, File file_dir) {
        ArrayList<String> files = new ArrayList<>();
        File[] chids = file_dir.listFiles();
        for (File file : chids) {
            String end = file.getName().substring(file.getName().lastIndexOf("."));
            if (FileUtils.endWith(context, end, R.array.support_image_type)) {
                files.add(file.getAbsolutePath());
            }
        }
        return files;
    }

    public static void setShowAbleChilds(ArrayList<String> showAbleChilds) {
        WidgetDataModel.showAbleChilds = showAbleChilds;
    }

    public static SharedPreferences getSp() {
        if (sp == null) {
            sp = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        }
        return sp;
    }

    public static void init(Context con) {
        context=con;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
