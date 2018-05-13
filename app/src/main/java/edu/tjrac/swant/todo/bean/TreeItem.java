package edu.tjrac.swant.todo.bean;

import java.util.ArrayList;

/**
 * Created by wpc on 2018/4/27.
 */

public class TreeItem<T extends TreeItem> {

    boolean expand = true;
    Integer lv;
    T parent;
    ArrayList<T> childs;
}
