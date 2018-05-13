package edu.tjrac.swant.douban.doubane;

import java.util.List;

import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:31
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DoubanTop250 {

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

}
