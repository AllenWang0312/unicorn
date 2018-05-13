package edu.tjrac.swant.todo.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/13 0013 上午 11:04
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class Todo extends TreeItem<Todo> implements jso {



    boolean finished;
    String title;
    Integer iconId;
    Integer theme;
    Integer color;

    Target target;
    Notice notice;




    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public ArrayList<Todo> format(){

        ArrayList<Todo> format=new ArrayList<>();
        format.add(this);
        if(expand){

            if (childs != null && childs.size() > 0) {
                for (Todo child : childs) {
                    format.addAll(child.format());
                }
            }
        }

        return format;
    }
    public int getCount() {
        int i = 1;
        if (childs != null && childs.size() > 0) {
            for (Todo child : childs) {
                i += child.getCount();
            }
        }
        return i;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("parent", parent);
            object.put("lv", lv);
            object.put("title", title);
            object.put("iconId", iconId);
            object.put("theme", theme);
            object.put("color", color);
            object.put("target", target.toJsonObject());
            if (childs != null && childs.size() > 0) {
                JSONArray jsa = new JSONArray();
                for (Todo item : childs) {
                    jsa.put(item.toJsonObject());
                }
                object.put("childs", jsa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Todo(String title) {
        this.title = title;
    }

    public int lv() {
        if (lv != null) {
            return lv;
        }

        if (parent == null) {
            return 0;
        } else {
            lv = parent.lv() + 1;
            return lv;
        }
    }

    public void addChild(Todo todo) {
        todo.setParent(this);
        if (childs == null) {
            childs = new ArrayList<>();
        }
        expand = false;
        childs.add(todo);
    }

    public void setParent(Todo parent) {
        this.parent = parent;
    }

    public void switchState() {
        finished=!finished;
    }

    public void switchExpend() {
        expand=!expand;
    }
}
