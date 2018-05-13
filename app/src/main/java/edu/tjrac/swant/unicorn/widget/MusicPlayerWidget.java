package edu.tjrac.swant.unicorn.widget;//package edu.tjrac.swant.unicorn.module.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.RemoteViews;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.media.MusicPlayService;

/**
 * Implementation of App Widget functionality.
 */
public class MusicPlayerWidget extends AppWidgetProvider {

    private MusicPlayService.MusicPlayerBinder binder;
    private ServiceConnection coonection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicPlayService.MusicPlayerBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.music_player_widget);
        views.setTextViewText(R.id.name, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        context.bindService(new Intent(context, MusicPlayService.class), coonection, Context.BIND_AUTO_CREATE);
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

