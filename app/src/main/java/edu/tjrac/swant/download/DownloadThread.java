package edu.tjrac.swant.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.tjrac.swant.download.bean.DownloadFileInfo;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/12 0012 上午 9:04
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DownloadThread implements Runnable {

    private  DownloadThreadCallback threadCallback;
    private DownloadFileInfo info;
//    private DownLoadCallback callback;

    //        private boolean isdownloading;
    private URL url;
    private RandomAccessFile localFile;
    private HttpURLConnection urlConn;
    private InputStream inputStream;
    private int progress = -1;

    public DownloadThread(DownloadFileInfo info, DownloadThreadCallback threadcallback) {
        this.info = info;
        threadCallback=threadcallback;
    }

    @Override
    public void run() {
//            Log.i("download thread start",sqlDownLoadInfo.toString());
        try {
            url = new URL(info.getUrl());
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            while (sqlDownLoadInfo.downloadtimes < sqlDownLoadInfo.maxdownloadtimes) { //做3次请求的尝试

        try {

            if (info.getDownloadSize() == info.getSize()) {
                threadCallback.onThreadProgress(info.getUrl()+"_"+info.getSavePath(),info);
//                callback.onSuccess(info);
                info.cacheToFile();
                return;
            }

            if (new File(info.getSavePath(), info.cacheName()).exists()) {
                localFile = new RandomAccessFile(new File(info.getSavePath(), info.cacheName()), "rwd");
                localFile.seek(info.getDownloadSize());
                urlConn.setRequestProperty("Range", "bytes=" + info.getDownloadSize() + "-");
            } else {
//                openConnention();
                long urlfilesize = urlConn.getContentLength();
                if (urlfilesize > 0) {
//            isFolderExist();
                    localFile = new RandomAccessFile(new File(info.getSavePath(), info.cacheName()), "rwd");
                    localFile.setLength(urlfilesize);
                    info.setSize(urlfilesize);
//            if (sqlDownLoadInfo.isOnDownloading()) {
//                saveDownloadInfo();
//            }
                }
            }
//                    if (sqlDownLoadInfo.downloadtimes< 1) {//第一次下载，初始化
//                        openConnention();
//                    } else {
//
//                    }
            inputStream = urlConn.getInputStream();
            byte[] buffer = new byte[1024 * 4];
            int length = -1;
            while ((length = inputStream.read(buffer)) != -1) {
                localFile.write(buffer, 0, length);
                info.downloadSize += length;
//                callback.onProgress(info);
                threadCallback.onThreadProgress(info.getUrl()+"_"+info.getSavePath(),info);
//                int nowProgress = sqlDownLoadInfo.getProgress();
//                if (nowProgress > progress + 5) {
//                    progress = nowProgress;
//                    handler.sendEmptyMessage(TASK_PROGESS);
//                }
            }
//            sqlDownLoadInfo.downloadtimes += 1;
            //下载完了
            if (info.downloadSize >= info.size) {
//                callback.onSuccess(info);
                threadCallback.onThreadSuccess(info.getUrl()+"_"+info.getSavePath(),info);
                info.cacheToFile();
//                this.stop();
//                this.stop();
//                boolean renameResult = RenameFile();
////                        Log.i("rename file", String.valueOf(renameResult));
//                if (renameResult) {
//                    handler.sendEmptyMessage(TASK_SUCCESS); //转移文件成功
//
//                    if (sqlDownLoadInfo.getTaskID().endsWith("图片")) {
//                        MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                                sqlDownLoadInfo.getFilePath(), sqlDownLoadInfo.getFileName(), null);
//                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + FileHelper.getFileDefaultPath())));
//                        LogUtil.v("DownLoader" + "DownLoaderSuccess");
//                    }
//                    sleep(500);
//                    handler.sendEmptyMessage(TASK_AFTER_SUCCESS); //
//                } else {
//                    new File(FileHelper.getTempDirPath(), getcacheFileName(sqlDownLoadInfo)).delete();
//                    handler.sendEmptyMessage(TASK_ERROR);//转移文件失败
//                }
                //清除数据库任务
//                        datakeeper.deleteDownLoadInfo(userID, sqlDownLoadInfo.getTaskID());
//                       downLoadThread.releseRes();
//                        downLoadThread = null;
//                      pool.remove(downLoadThread);
//                        sqlDownLoadInfo.setOnDownloading(false);
//                        sqlDownLoadInfo. downloadtimes =sqlDownLoadInfo. maxdownloadtimes;
            }

        } catch (Exception e) {
            threadCallback.onThreadFaild(info.getUrl()+"_"+info.getSavePath(),info,e.getMessage());
//            if (sqlDownLoadInfo.isOnDownloading()) {
//                if (isSupportBreakpoint) {
//                    sqlDownLoadInfo.downloadtimes++;
//                    if (sqlDownLoadInfo.downloadtimes >= sqlDownLoadInfo.maxdownloadtimes) {
//                        if (sqlDownLoadInfo.fileSize > 0) {
//                            saveDownloadInfo();
//                        }
//                        pool.remove(downLoadThread);
//                        downLoadThread = null;
//                        sqlDownLoadInfo.setOnDownloading(false);
//                        handler.sendEmptyMessage(TASK_ERROR);
//                    }
//                } else {
//                    sqlDownLoadInfo.downFileSize = 0;
//                    sqlDownLoadInfo.downloadtimes = sqlDownLoadInfo.maxdownloadtimes;
//                    sqlDownLoadInfo.setOnDownloading(false);
//                    downLoadThread = null;
//                    handler.sendEmptyMessage(TASK_ERROR);
//                }
//
//            } else {
//                sqlDownLoadInfo.downloadtimes = sqlDownLoadInfo.maxdownloadtimes;
//            }
            e.printStackTrace();
        } finally {
            try {
                if (urlConn != null) {
                    urlConn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (localFile != null) {
                    localFile.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//            }

    }
//
//        public void stopDownLoad() {
//
//        }

    private void openConnention() throws Exception {

    }

}
