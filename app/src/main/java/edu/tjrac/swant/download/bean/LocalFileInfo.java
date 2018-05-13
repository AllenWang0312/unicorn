package edu.tjrac.swant.download.bean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/9 0009 下午 4:13
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class LocalFileInfo extends DownloadFileInfo{

    boolean chosed=false;

    public LocalFileInfo(String name, String url) {
        super(name, url);
    }

    public void switchState() {
        chosed=!chosed;
    }

    public boolean isChosed() {
        return chosed;
    }

    public void setChosed(boolean chosed) {
        this.chosed = chosed;
    }

}
