package edu.tjrac.swant.unicorn.widget;//package edu.tjrac.swant.unicorn.module.widget;

import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.yckj.baselib.util.L;
import com.yckj.baselib.util.StringUtils;

import java.io.IOException;

import edu.tjrac.swant.filesystem.view.SelectDirActivity;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/3 0003.
 * FrameLayout、LinearLayout、RelativeLayout
 * TextClock、Button、Chronometer、ImageButton、ImageView、ProgressBar、TextView
 */

public class UnicornWidgetProvider extends AppWidgetProvider {

    public static final String CLICK_ACTION = "edu.tjrac.swant.unicorn.module.widget.CLICK"; // 点击事件的广播ACTION
    public static final String NEXT_ACTION = "edu.tjrac.swant.unicorn.module.widget.NEXT";
    public static final String PRE_ACTION = "edu.tjrac.swant.unicorn.module.widget.PRE";
    public static final String SET_ACTION = "edu.tjrac.swant.unicorn.module.widget.SET";
    public static final String CHOSE_ACTION = "edu.tjrac.swant.unicorn.module.widget.CHOSE";


    //    static File file_dir;
//    static String wallpaper_dir;
//    static ArrayList<String> showAbleChilds;
     int index = 0;

     int[] ids;
     AppWidgetManager mManager;
     RemoteViews remoteViews;

    /**
     * 每次窗口小部件被更新都调用一次该方法
     */

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        L.i("unicorn", "onUpdate");
        WidgetDataModel.init(context);
        context.startService(new Intent(context, WidgetDataModel.class));

        mManager = appWidgetManager;
        ids = appWidgetIds;
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_unicorn);


        String dir = WidgetDataModel.getSp().getString("wallpaper_dir", "");
        if (!StringUtils.isEmpty(dir)) {
            remoteViews.setViewVisibility(R.id.ll_viewer, View.VISIBLE);
            remoteViews.setViewVisibility(R.id.set, View.VISIBLE);

            if (!dir.equals(WidgetDataModel.getWallpaper_dir())) {
                WidgetDataModel.setWallpaper_dir(context, dir);
            }

            L.i("onReceive", WidgetDataModel.getShowAbleChilds().toString());
//            if (present != null) {
//                present.recycle();
//                present = null;
//            }
            present = BitmapFactory.decodeFile(WidgetDataModel.getShowAbleChilds().get(index));
            remoteViews.setImageViewBitmap(R.id.imageview,
                    present);
//            initBitmap();
            Intent intent = new Intent(CLICK_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, R.id.imageview, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.imageview, pendingIntent);

            Intent next = new Intent(NEXT_ACTION);
            PendingIntent nextPadding = PendingIntent.getBroadcast(
                    context, R.id.next, next, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.next, nextPadding);

            Intent pre = new Intent(PRE_ACTION);
            PendingIntent prePadding = PendingIntent.getBroadcast(
                    context, R.id.pre, pre, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.pre, prePadding);

            Intent set = new Intent(SET_ACTION);
            PendingIntent setPadding = PendingIntent.getBroadcast(
                    context, R.id.set, set, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.set, setPadding);

        } else {
            remoteViews.setViewVisibility(R.id.ll_viewer, View.GONE);
            remoteViews.setViewVisibility(R.id.set, View.GONE);
        }


        Intent chose = new Intent(context, SelectDirActivity.class).putExtra("key", "wallpaper_dir");
        PendingIntent choseIntent = PendingIntent.getActivity(context, 0, chose, 0);
//        PendingIntent chosePadding = PendingIntent.getBroadcast(
//                context, R.id.chose, chose, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.chose, choseIntent);

        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }

    }


    /**
     * 接收窗口小部件点击时发送的广播
     */

     Bitmap present;

    @Override
    public void onReceive(Context context, Intent intent) {
        L.i("unicorn", "onReceive");
        super.onReceive(context, intent);
//     RemoteViews   remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_unicorn);
        String action = intent.getAction();
        if (CLICK_ACTION.equals(action)) {
            Toast.makeText(context, "click!", Toast.LENGTH_SHORT).show();
        } else if (NEXT_ACTION.equals(action)) {
            if (index == WidgetDataModel.getShowAbleChilds().size() - 1) {
                index = 0;
            } else {
                index++;
            }
//            if (present != null) {
//                present.recycle();
//                present = null;
//            }
            present = BitmapFactory.decodeFile(WidgetDataModel.getShowAbleChilds().get(index));
            remoteViews.setImageViewBitmap(R.id.imageview,
                    present);
            for (int appWidgetId : ids) {
                mManager.updateAppWidget(appWidgetId, remoteViews);
            }

        } else if (PRE_ACTION.equals(action)) {
            if (index == 0) {
                index = WidgetDataModel.getShowAbleChilds().size() - 1;
            } else {
                index--;
            }
//            if (present != null) {
//                present.recycle();
//                present = null;
//            }
            present = BitmapFactory.decodeFile(WidgetDataModel.getShowAbleChilds().get(index));
            remoteViews.setImageViewBitmap(R.id.imageview,
                    present);
            for (int appWidgetId : ids) {
                mManager.updateAppWidget(appWidgetId, remoteViews);
            }

        } else if (SET_ACTION.equals(action)) {
            //利用WallpaparManager,添加权限set_wallpaper
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            try {
                wallpaperManager.setBitmap(
                        BitmapFactory.decodeFile(WidgetDataModel.getShowAbleChilds().get(index))
//                        BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }

            //getSystemService(WALLPAPER_SERVICE)获取wallpaperManager会出问题
          /*  @SuppressLint("ServiceCast")
            WallpaperManager manager =(WallpaperManager)getSystemService(WALLPAPER_SERVICE);
            try {
                manager.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.pic1));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

    }

    /**
     * 每删除一次窗口小部件就调用一次
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * 当最后一个该窗口小部件删除时调用该方法
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    /**
     * 当小部件大小改变时
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    /**
     * 当小部件从备份恢复时调用该方法
     */
    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }


}
