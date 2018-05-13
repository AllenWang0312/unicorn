package edu.tjrac.swant.kaiyan.kaiyan;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 3:39
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public  class LabelBean {
    /**
     * text :
     * card :
     * detail : null
     */

    private String text;
    private String card;
    private Object detail;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}