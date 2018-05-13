package edu.tjrac.swant.unicorn;

import com.google.gson.Gson;
import com.yckj.baselib.util.L;

import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicKey;
import edu.tjrac.swant.qqmusic.QQMusicApi;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 上午 9:29
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class ApiUtils {

    public static String getQQMusicImagePath(String imgId) {
        StringBuffer sb = new StringBuffer(QQMusicApi.imagePath);
        sb.append(imgId.charAt(imgId.length() - 2));
        sb.append("/");
        sb.append(imgId.charAt(imgId.length() - 1));
        sb.append("/");
        sb.append(imgId);
        sb.append(".jpg");
        String result = sb.toString();
        L.i("getQQMusicImagePath", result);
        return result;
    }

    public static String getQQMusicPlayUrl(String micId, String key) {
        String withid = QQMusicApi.playUrl.replace("{ID}", micId);
        return withid.replace("{Key}", key);
    }

    public static QQMusicKey formatKeyResut(String keyString) {
        String jsonString = keyString.substring(12, keyString.length() - 2);
        return new Gson().fromJson(jsonString, QQMusicKey.class);
    }


    //"2018-03-12" 2016/05/11
    public static String formatGankDate(String s) {
        return s.replace("-","/");
    }
}
