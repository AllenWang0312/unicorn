package edu.tjrac.swant.download;

import android.content.Context;

import edu.tjrac.swant.download.bean.DownloadFileInfo;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/16 0016 上午 8:51
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class NotificationDownloadThread extends Thread {

    private Context mContext;
    private DownloadFileInfo info;

    NotificationDownloadThread(Context context){
        this.mContext=context;
    }

    public void setInfo(DownloadFileInfo info){
        this.info=info;
    }
    @Override
    public void run() {
        super.run();
    }

}
