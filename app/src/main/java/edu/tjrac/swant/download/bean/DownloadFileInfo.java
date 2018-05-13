package edu.tjrac.swant.download.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/12 0012 上午 8:56
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DownloadFileInfo extends FileInfo implements Parcelable {

//    long taskId;
    public boolean isDownloading;
    public long size;
    public   long downloadSize;


//    public long getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(long taskId) {
//        this.taskId = taskId;
//    }



    public DownloadFileInfo(String name, String url) {
        super(name,url);
    }
    public static final Creator<DownloadFileInfo> CREATOR = new Creator<DownloadFileInfo>() {
        @Override
        public DownloadFileInfo createFromParcel(Parcel in) {
            return new DownloadFileInfo(in);
        }

        @Override
        public DownloadFileInfo[] newArray(int size) {
            return new DownloadFileInfo[size];
        }
    };



    public String cacheName() {
        return fileName + ".cache";
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public void setDownloading(boolean downloading) {
        isDownloading = downloading;
    }

    public void cacheToFile() {
        File file = new File(getSavePath(), cacheName());
        if (file.exists()) {
            file.renameTo(new File(getSavePath(), fileName));
        }
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }



    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(long download_size) {
        this.downloadSize = download_size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    protected DownloadFileInfo(Parcel in) {
        fileName = in.readString();
        url = in.readString();
        savePath=in.readString();
        absPath = in.readString();
//        taskId=in.readLong();
//        isDownloading=in.readByte();
        size=in.readLong();
        downloadSize=in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(url);
        dest.writeString(savePath);
        dest.writeString(absPath);
//        dest.writeLong(taskId);
        dest.writeLong(size);
        dest.writeLong(downloadSize);
    }

}
