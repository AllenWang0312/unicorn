package edu.tjrac.swant.media;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.yckj.baselib.util.L;
import com.yckj.baselib.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import edu.tjrac.swant.media.view.MusicPlayerActivity;
import edu.tjrac.swant.qqmusic.lrchelper.LrcInfo;
import edu.tjrac.swant.qqmusic.lrchelper.LrcParser;
import edu.tjrac.swant.qqmusic.lrchelper.MusicInfo;
import edu.tjrac.swant.unicorn.R;

import static edu.tjrac.swant.media.MusicPlayService.CycleType.list;
import static edu.tjrac.swant.media.MusicPlayService.CycleType.once;
import static edu.tjrac.swant.media.MusicPlayService.CycleType.random;
import static edu.tjrac.swant.media.MusicPlayService.CycleType.single;

/**
 * Created by wpc on 2018/2/5 0005.
 */

public class MusicPlayService extends Service {


    enum CycleType {
        single, list, random, once
    }

    private String TAG = "MusicPlayService";
    private MediaPlayer mMediaPlayer;
    private MusicPlayerBinder binder;
    int index;

    CycleType cycle_type = list;
    public static final String PLAY_ACTION = "edu.tjrac.swant.unicorn.module.media.PLAY_ACTION";
    public static final String PLAY_NEXT_ACTION = "edu.tjrac.swant.unicorn.module.media.PLAY_NEXT_ACTION";
    public static final String PLAY_PRE_ACTION = "edu.tjrac.swant.unicorn.module.media.PLAY_PRE_ACTION";

    public static final String CLOSE_PLAYER = "edu.tjrac.swant.unicorn.module.media.CLOSE_PLAYER";
    public static final String UPDATA_PLAY_INFO = "edu.tjrac.swant.unicorn.module.media.UPDATA_PLAY_INFO";


    private BroadcastReceiver control = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (PLAY_NEXT_ACTION.equals(action)) {
                binder.playNext();
            } else if (CLOSE_PLAYER.equals(action)) {
                stopSelf();
            } else if (PLAY_PRE_ACTION.equals(action)) {
                binder.playPre();
            } else if (PLAY_ACTION.equals(action)) {
                index = intent.getIntExtra("index", 0);
                binder.play();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (cycle_type == list) {
                binder.playNext();

            } else if (cycle_type == once) {
                mMediaPlayer.release();
                stopSelf();
            } else if (cycle_type == single) {
                binder.play();
            } else if (cycle_type == random) {
                index = (int) (Math.random() * binder.playlist.size());
                binder.play();
            }
        }
    };

    @Override
    public void onCreate() {
        binder = new MusicPlayerBinder();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PLAY_NEXT_ACTION);
        filter.addAction(PLAY_PRE_ACTION);
        filter.addAction(PLAY_ACTION);

        registerReceiver(control, filter);

        L.i(TAG, "onCreate");
        super.onCreate();
    }

    int NOTIFY_ID = 1234;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.i(TAG, "onBind");
        ArrayList<MusicInfo> list = intent.getParcelableArrayListExtra("list");
        index = intent.getIntExtra("index", 0);

        if (list != null) {
            binder.playlist = list;
        } else {
//            String name = intent.getStringExtra("name");
//            String url=intent.getStringExtra("url");
//
//            if(StringUtils.isEmpty(name)){
////                binder.playlist
//                binder.playlist=new ArrayList<>();
//                binder.playlist.add(new MusicInfo().setUrl(url));
//            }else {
//                binder.playlist = getPlayList(name);
//
////            if (binder.playlist != null && binder.playlist.contains(name)) {
////                index = binder.playlist.indexOf(name);
////            } else {
//                if (!StringUtils.isEmpty(name)) {
//                    File file = new File(name).getParentFile();
//                    if (file.isDirectory()) {
//                        binder.playlist = getPlayList(file);
//                        index = 0;
////                        index = binder.playlist.indexOf(name);
//                    }
//                }
////            }
//            }

        }


//        String option = intent.getStringExtra("option");
//        binder.play();
        return binder;
    }

    private ArrayList<MusicInfo> getPlayList(String name) {
        ArrayList<MusicInfo> infos = new ArrayList<>();
        File file = new File(name);
        if (file.exists()) {
            for (File file1 : file.getParentFile().listFiles()) {
                String filename = file1.getName();
                if (MusicPlayerActivity.res_type.contains(filename.substring(filename.lastIndexOf(".")))) {
                    infos.add(new MusicInfo(file1.getAbsolutePath()));
                }
            }
        }
        return infos;
    }

    private ArrayList<MusicInfo> getPlayList(File file) {
        ArrayList<MusicInfo> array = new ArrayList<>();
        File[] childs = file.listFiles();
        for (File item : childs) {
            String end = StringUtils.getEndString(item.getName());
            if (!StringUtils.isEmpty(end)) {
                if (MusicPlayerActivity.res_type.contains(end)) {
                    L.i("getPlayList", item.getAbsolutePath());
                    array.add(new MusicInfo(item.getAbsolutePath()));
                }
            }

        }
        return array;
    }

    String playing = "";


    void showNotification() {
        Notification.Builder builder = new Notification.Builder(getBaseContext());
//       builder.setSmallIcon(R.mipmap.logo)
//               .setContentIntent(playing)
    }



    public class MusicPlayerBinder extends Binder {

        int state=-1;//-1 0 准备好 1 播放


        int length;
        int progress;
        private LrcInfo lrcInfo;
        private LrcLoadCallBack callback;
        private MusicResCallBack musicCallback;
        public ArrayList<MusicInfo> playlist;

        public void setLrcCallback(LrcLoadCallBack callback) {
            this.callback = callback;
        }

        public void setMusicCallback(MusicResCallBack musicCallback) {
            this.musicCallback = musicCallback;
        }

        public LrcInfo getLrcInfo() {

            return lrcInfo;
        }

        public void scrollTo(int time) {
            mMediaPlayer.seekTo(time);
        }

        public int getLength() {
            if (mMediaPlayer != null) {
                return mMediaPlayer.getDuration();
            } else {
                return 0;
            }
        }

        public int getProgress() {
            return mMediaPlayer.getCurrentPosition();
        }

        public MusicPlayService getService() {
            return MusicPlayService.this;
        }

        void playPre() {
            if (index == 0) {
                index = binder.playlist.size() - 1;
            } else {
                index--;
            }
            playing = "";
            play();
        }

        public void play() {

            if(state==0){
                mMediaPlayer.start();
                return;
            }

            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mMediaPlayer.seton
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mp) {
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.i(TAG, "onPrepared");
                        state=0;
                        mMediaPlayer.start();
                        state=1;
                    }
                });
                mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.i(TAG, "what:" + what + "extra" + extra);
                        return true;
                    }
                });
            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
//            if(mMediaPlayer.getCurrentPosition()>0){
//                mMediaPlayer.start();
//                return;
//            }
            if (playing.equals(playlist.get(index))) {
                mMediaPlayer.start();
//                L.i("play", playlist.size() + ":" + playlist.get(index));
            } else {

                try {
                    if (!StringUtils.isEmpty(playlist.get(index).getFilePath())) {
                        playing = playlist.get(index).getFilePath();
                        Uri uri = Uri.fromFile(new File(playing));
                        mMediaPlayer.setDataSource(MusicPlayService.this, uri);
                        lrcInfo = new LrcParser().parser(new FileInputStream(new File(playlist.get(index).getLrcPath())));
                        callback.onSuccess();

                        mMediaPlayer.prepare();
                        mMediaPlayer.start();
                    } else {
                        playing = playlist.get(index).getUrl();
//                        Uri uri = Uri.fromParts();
                        mMediaPlayer.setDataSource(playing);
                        new LrcLoader(playlist.get(index).getLrcUrl()).execute();
                        mMediaPlayer.prepareAsync();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
//            Intent notificationIntent = new Intent(getApplicationContext(), MusicPlayerActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
//            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(MusicPlayService.this)
//                    .setSmallIcon(R.mipmap.logo)
//                    .setTicker("MusicPlayer")
//                    .setWhen(System.currentTimeMillis())
//                    .setContentTitle(getString(R.string.app_name))
//                    .setContentText("this is song")
//                    .setContentIntent(pendingIntent);
//            Notification notification = mNotifyBuilder.build();
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.notification_music_player);

            PendingIntent next =
                    PendingIntent.getBroadcast(MusicPlayService.this,
                            1, new Intent(PLAY_NEXT_ACTION), PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.iv_next, next);

            PendingIntent pre =
                    PendingIntent.getBroadcast(MusicPlayService.this,
                            1, new Intent(PLAY_PRE_ACTION), PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.iv_pre, pre);

            PendingIntent icon =
                    PendingIntent.getBroadcast(MusicPlayService.this,
                            1,
                            new Intent(MusicPlayService.this, MusicPlayerActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.iv_icon, icon);

            PendingIntent close =
                    PendingIntent.getBroadcast(MusicPlayService.this,
                            1,
                            new Intent(CLOSE_PLAYER), PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.iv_close, close);

            NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(MusicPlayService.this);
            mbuilder.setSmallIcon(R.mipmap.logo)
                    .setCustomContentView(views);
            Notification notification = mbuilder.build();
            //自定义的布局视图


            startForeground(NOTIFY_ID, notification);
        }

        void playNext() {

            if (index == binder.playlist.size() - 1) {
                index = 0;
            } else {
                index++;
            }
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer.reset();
            state=-1;
            play();
        }

        public void stop() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                state=0;
            }
        }

        public MediaPlayer getMediaPlayer() {
            return mMediaPlayer;
        }


        class LrcLoader extends AsyncTask<Void, Void, LrcInfo> {

            String url;

            LrcLoader(String url) {
                this.url = url;
            }

            @Override
            protected LrcInfo doInBackground(Void... voids) {
                lrcInfo = null;
                try {
                    URL u = new URL(url);
                    lrcInfo = new LrcParser().parser(u.openStream());
                    if (lrcInfo != null) {
                        callback.onSuccess();
                    }
                } catch (IOException e) {
                    callback.onFaild(e.getMessage());
                    e.printStackTrace();
                }
                return lrcInfo;
            }

            @Override
            protected void onPostExecute(LrcInfo lrcInfo) {
                super.onPostExecute(lrcInfo);
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(control);
        super.onDestroy();
    }


}
