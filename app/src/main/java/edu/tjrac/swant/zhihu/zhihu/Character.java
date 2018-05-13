package edu.tjrac.swant.zhihu.zhihu;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 2:11
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class Character implements Parcelable{

    /**
     * alt : https://movie.douban.com/celebrity/1276787/
     * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1351678808.44.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1351678808.44.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1351678808.44.webp"}
     * name : 申·阿克
     * id : 1276787
     */

    private String alt;
    private AvatarsBeanX avatars;
    private String name;
    private String id;

    protected Character(Parcel in) {
        alt = in.readString();
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public AvatarsBeanX getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBeanX avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alt);
        dest.writeString(name);
        dest.writeString(id);
    }

    public static class AvatarsBeanX {
        /**
         * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1351678808.44.webp
         * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1351678808.44.webp
         * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1351678808.44.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }
}
