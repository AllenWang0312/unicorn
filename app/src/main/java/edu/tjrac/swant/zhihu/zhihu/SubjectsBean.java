package edu.tjrac.swant.zhihu.zhihu;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import edu.tjrac.swant.douban.doubane.MovieData;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 10:01
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public  class SubjectsBean implements MultiItemEntity,Parcelable,MovieData {
    /**
     * rating : {"max":10,"average":0,"stars":"00","min":0}
     * genres : ["动作","科幻","冒险"]
     * title : 环太平洋：雷霆再起
     * casts : [{"alt":"https://movie.douban.com/celebrity/1339915/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp"},"name":"约翰·博耶加","id":"1339915"},{"alt":"https://movie.douban.com/celebrity/1000188/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1418720473.76.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1418720473.76.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1418720473.76.webp"},"name":"斯科特·伊斯特伍德","id":"1000188"},{"alt":"https://movie.douban.com/celebrity/1275432/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1407683852.1.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1407683852.1.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1407683852.1.webp"},"name":"景甜","id":"1275432"}]
     * collect_count : 631
     * original_title : Pacific Rim: Uprising
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1340823/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp"},"name":"斯蒂文·S·迪奈特","id":"1340823"}]
     * year : 2018
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512983475.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512983475.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512983475.webp"}
     * alt : https://movie.douban.com/subject/20435622/
     * id : 20435622
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    private int type ;

    public SubjectsBean(String title, int type) {
        this.title = title;
        this.type = type;
    }

    protected SubjectsBean(Parcel in) {
        title = in.readString();
        collect_count = in.readInt();
        original_title = in.readString();
        subtype = in.readString();
        year = in.readString();
        alt = in.readString();
        id = in.readString();
        genres = in.createStringArrayList();
        type = in.readInt();
    }

    public static final Creator<SubjectsBean> CREATOR = new Creator<SubjectsBean>() {
        @Override
        public SubjectsBean createFromParcel(Parcel in) {
            return new SubjectsBean(in);
        }

        @Override
        public SubjectsBean[] newArray(int size) {
            return new SubjectsBean[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(collect_count);
        dest.writeString(original_title);
        dest.writeString(subtype);
        dest.writeString(year);
        dest.writeString(alt);
        dest.writeString(id);
        dest.writeStringList(genres);
        dest.writeInt(type);
    }

    @Override
    public SubjectsBean getMovieData() {
        return this;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 0
         * stars : 00
         * min : 0
         */

        private int max;
        private float average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public float getAverage() {
            return average;
        }

        public void setAverage(float average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512983475.webp
         * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512983475.webp
         * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512983475.webp
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

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1339915/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp"}
         * name : 约翰·博耶加
         * id : 1339915
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
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

        public static class AvatarsBean {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447164061.84.webp
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

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1340823/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp"}
         * name : 斯蒂文·S·迪奈特
         * id : 1340823
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

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

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pqiCuEHOtvNIcel_avatar_uploaded1403339434.41.webp
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
}