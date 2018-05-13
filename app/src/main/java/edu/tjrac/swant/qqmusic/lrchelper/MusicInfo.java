package edu.tjrac.swant.qqmusic.lrchelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wpc on 2018/4/24.
 */

public class MusicInfo implements Parcelable{

    String name;
    String artiest;
    String ablum;


    LrcInfo lrcinfo;

    String filePath;
    String lrcPath;
    String url;
    String lrcUrl;

    long progress;
    long duration;

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public MusicInfo(){

    }
    public MusicInfo(String filePath) {
       setFilePath(filePath);
    }

    public MusicInfo(String url, String lrcUrl) {
        this.url = url;
        this.lrcUrl = lrcUrl;
    }

    protected MusicInfo(Parcel in) {
        name = in.readString();
        artiest = in.readString();
        ablum = in.readString();
        filePath = in.readString();
        lrcPath = in.readString();
        url = in.readString();
        lrcUrl = in.readString();
    }

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel in) {
            return new MusicInfo(in);
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtiest() {
        return artiest;
    }

    public void setArtiest(String artiest) {
        this.artiest = artiest;
    }

    public String getAblum() {
        return ablum;
    }

    public void setAblum(String ablum) {
        this.ablum = ablum;
    }

    public LrcInfo getLrcinfo() {
        return lrcinfo;
    }

    public void setLrcinfo(LrcInfo lrcinfo) {
        this.lrcinfo = lrcinfo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        name=filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf("."));
        this.filePath = filePath;
    }

    public String getLrcPath() {
        return lrcPath;
    }

    public void setLrcPath(String lrcPath) {
        this.lrcPath = lrcPath;
    }

    public String getUrl() {
        return url;
    }

    public MusicInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(artiest);
        dest.writeString(ablum);
        dest.writeString(filePath);
        dest.writeString(lrcPath);
        dest.writeString(url);
        dest.writeString(lrcUrl);
    }
}
