package edu.tjrac.swant.download.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.yckj.baselib.util.StringUtils;

import java.io.File;

import edu.tjrac.swant.unicorn.Config;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/11 0011 上午 11:35
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class FileInfo implements Parcelable {

    String fileName;
    String url = "";
    String savePath;
    String absPath;

    public FileInfo(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }


    public FileInfo(String path) {
        File file = new File(path);
        fileName = file.getName();
        absPath = path;
        savePath = absPath.substring(0, absPath.lastIndexOf("/"));
    }

    public FileInfo() {
    }

    public static final Creator<FileInfo> CREATOR = new Creator<FileInfo>() {
        @Override
        public FileInfo createFromParcel(Parcel in) {
            return new FileInfo(in);
        }

        @Override
        public FileInfo[] newArray(int size) {
            return new FileInfo[size];
        }
    };

    public String getSavePath() {
        File dir;
        if (StringUtils.isEmpty(savePath)) {
            dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return savePath;
        } else {
            dir = new File(Config.fileCacheDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return Config.fileCacheDir;
        }
    }

    protected FileInfo(Parcel in) {
        fileName = in.readString();
        url = in.readString();
        absPath = in.readString();
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(url);
        dest.writeString(savePath);
        dest.writeString(absPath);
    }
}
