package edu.tjrac.swant.zhihu.zhihu;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 下午 1:54
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public  class StoriesBean implements MultiItemEntity {
    public StoriesBean(int type, String title) {
        this.type = type;
        this.title = title;
    }

    /**
     * images : ["http://p4.zhimg.com/7b/c8/7bc8ef5947b069513c51e4b9521b5c82.jpg"]
     * type : 0
     * id : 1747159
     * ga_prefix : 111822
     * title : 深夜食堂 · 我的张曼妮
     * multipic : true
     */


    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private boolean multipic;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public int getItemType() {
        return type;
    }
}