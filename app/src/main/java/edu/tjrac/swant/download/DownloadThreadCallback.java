package edu.tjrac.swant.download;

import edu.tjrac.swant.download.bean.DownloadFileInfo;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/12 0012 上午 10:05
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface DownloadThreadCallback {

    void onThreadProgress(String tag, DownloadFileInfo info);

    void onThreadSuccess(String tag, DownloadFileInfo info);

    void onThreadFaild(String tag, DownloadFileInfo info,String err);
}
