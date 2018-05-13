package edu.tjrac.swant.todo.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/13 0013 上午 11:06
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class Target implements jso{
    int textColor;

    String showText;
    String url;
    int type;//0 地址 1 app 2 文件 3 网址


    @Override
    public JSONObject toJsonObject() {
        JSONObject object=new JSONObject();
        try {
            object.put("textColor",textColor);
            object.put("showText",showText);
            object.put("url",url);
            object.put("type",type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
