package edu.tjrac.swant.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.tjrac.swant.download.bean.DownloadFileInfo;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/8 0008 下午 4:45
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DownLoadService extends Service {

    //线程池
    private ThreadPoolExecutor pool;

    private DownLoadBinder binder;
    HashMap<String,DownloadFileInfo> infos;
    ArrayList<DownloadFileInfo> beans;


    private  DownloadThreadCallback threadcallback;

    public void setThreadcallback(DownloadThreadCallback threadcallback) {
        this.threadcallback = threadcallback;
    }

    private final int MAX_DOWNLOADING_TASK = Runtime.getRuntime().availableProcessors(); // 最大同时下载数

    @Override
    public void onCreate() {
        pool = new ThreadPoolExecutor(
                MAX_DOWNLOADING_TASK >> 1,
                MAX_DOWNLOADING_TASK, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000));
        binder = new DownLoadBinder();
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        DownloadFileInfo info = intent.getParcelableExtra("downloadInfo");
        if(info==null){

        }else {
            binder.addThread(info);


        }
        return binder;
    }


    class DownLoadBinder extends Binder {

        public DownLoadService getService() {
            return DownLoadService.this;
        }

        public void addThread(DownloadFileInfo info){
            DownloadThread thread= new DownloadThread(info,threadcallback);
            if(infos==null){
                infos=new HashMap<String,DownloadFileInfo>();
                beans=new ArrayList<>();
            }
            infos.put(info.getUrl()+"_"+info.getSavePath(),info);
            beans.add(info);
            if(adapter!=null){
                adapter.notifyDataSetChanged();
            }
            pool.execute(thread);
        }

    }

    DownloadFileInfo getDownLoadInfo(long id){
        return infos.get(id);
    }

    DownLoadInfoAdapter adapter;

    public void bindToRecycler(BaseQuickAdapter adapter){
        adapter=new DownLoadInfoAdapter(beans);
//        recyclerView.setLayoutManager(new LinearLayoutManager(DownLoadService.this));
//        recyclerView.setAdapter(adapter);
//        adapter.get
//        adapter.getData()
    }
}
