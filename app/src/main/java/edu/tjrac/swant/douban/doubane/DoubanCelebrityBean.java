package edu.tjrac.swant.douban.doubane;

import java.util.List;

import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:20
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DoubanCelebrityBean {


    private String mobile_url;

    private String name;
    private String gender;
    private AvatarsBean avatars;
    private String id;
    private String name_en;
    private String born_place;//出身地
    private String alt;//条目页URL
    private List<String> aka_en;//英文名
    private List<WorksBean> works;//作品
    private List<String> aka;//中文名

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getBorn_place() {
        return born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<String> getAka_en() {
        return aka_en;
    }

    public void setAka_en(List<String> aka_en) {
        this.aka_en = aka_en;
    }

    public List<WorksBean> getWorks() {
        return works;
    }

    public void setWorks(List<WorksBean> works) {
        this.works = works;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class AvatarsBean {


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

    public static class WorksBean {


        private SubjectsBean subject;
        private List<String> roles;

        public SubjectsBean getSubject() {
            return subject;
        }

        public void setSubject(SubjectsBean subject) {
            this.subject = subject;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
