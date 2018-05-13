package edu.tjrac.swant.todo.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/13 0013 上午 11:11
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class Notice implements jso{

    //once;
    int status ;//-1 过期 0 未执行 1 已执行

    // round;
    String time;// "8:00"
    Integer weekIndex;
    Integer monthIndex;

    int type ; //0 不循环 1循环
    long roundType ;// 1每天 7 每周 30 每月 周期

    @Override
    public JSONObject toJsonObject() {
        JSONObject object=new JSONObject();
        try {
            object.put("status",status);
            object.put("time",time);
            object.put("weekIndex",weekIndex);
            object.put("monthIndex",monthIndex);
            object.put("type",type);
            object.put("roundType",roundType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //once daily
    public Notice(String time, int type) {
        this.time = time;
        this.type = type;
        if (type==1){
            roundType=1;
        }
    }

    //round
    public Notice(String time, Integer weekIndex, Integer monthIndex, long roundType) {
        this.time = time;
        this.weekIndex = weekIndex;
        this.monthIndex = monthIndex;
        this.roundType = roundType;
    }
}
