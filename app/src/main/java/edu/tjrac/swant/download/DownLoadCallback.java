package edu.tjrac.swant.download;

import edu.tjrac.swant.download.bean.DownloadFileInfo;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/12 0012 上午 9:08
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface DownLoadCallback {

    void onProgress(DownloadFileInfo info);

    void onSuccess(DownloadFileInfo info);

    void onFaild(DownloadFileInfo info);
}
