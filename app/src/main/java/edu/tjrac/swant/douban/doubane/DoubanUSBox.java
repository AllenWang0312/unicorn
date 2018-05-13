package edu.tjrac.swant.douban.doubane;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:32
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DoubanUSBox {

    private String date;
    private String title;
    private List<SubjectBean> subjects;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectBean implements MovieData {

        private int box;
        @SerializedName("new") private boolean newX;
        private int rank;
        private SubjectsBean subject;

        public int getBox() {
            return box;
        }

        public void setBox(int box) {
            this.box = box;
        }

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public SubjectsBean getSubject() {
            return subject;
        }

        public void setSubject(SubjectsBean subject) {
            this.subject = subject;
        }

        @Override
        public SubjectsBean getMovieData() {
            subject.setType(2);
            return subject;
        }

        @Override
        public int getItemType() {
            return getMovieData().getType();
        }
    }
}
