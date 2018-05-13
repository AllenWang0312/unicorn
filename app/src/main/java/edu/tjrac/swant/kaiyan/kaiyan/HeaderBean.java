package edu.tjrac.swant.kaiyan.kaiyan;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 3:40
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public  class HeaderBean {
    /**
     * id : 0
     * title : 凯拉·奈特利 | 优雅、独立而大胆的英伦玫瑰
     * font : null
     * subTitle : null
     * subTitleFont : null
     * textAlign : left
     * cover : null
     * label : null
     * actionUrl : null
     * labelList : null
     * icon : http://img.kaiyanapp.com/1cf9f982561f8c62a9147a3b943b16b2.jpeg?imageMogr2/quality/60/format/jpg
     * description : 凯拉·奈特利 | 优雅、独立而大胆的英伦玫瑰
     */

    private int id;
    private String title;
    private String font;
    private String subTitle;
    private String subTitleFont;
    private String textAlign;
    private String cover;
    private LabelBean label;
    private String actionUrl;
    private List<Label> labelList;
    private String icon;
    private String description;

    class Label{
        String text;
        Object actionUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitleFont() {
        return subTitleFont;
    }

    public void setSubTitleFont(String subTitleFont) {
        this.subTitleFont = subTitleFont;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getCover() {
        return cover;
    }
   public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
