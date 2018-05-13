package edu.tjrac.swant.douyin.widget;

/**
 * Created by wpc on 2018/3/2 0002.
 */

public interface Selectedable {
    boolean isSelected();
    void select();
    void unSelected();

    void selectAnimate();
    void unselectAnimate();
}
