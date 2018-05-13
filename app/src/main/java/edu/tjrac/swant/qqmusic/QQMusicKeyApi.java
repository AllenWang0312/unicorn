package edu.tjrac.swant.qqmusic;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 上午 11:02
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface QQMusicKeyApi {

    public static String URL="http://base.music.qq.com/";

    @GET("fcgi-bin/fcg_musicexpress.fcg")
    Observable<String> getQQmusicKey(@Query("json") int json,
                                     @Query("loginUin") String loginUin,
                                     @Query("format") String format,
                                     @Query("inCharset") String inCharset,
                                     @Query("outCharset") String outCharset,
                                     @Query("notice") int notice,
                                     @Query("platform") String platform,
                                     @Query("needNewCode") int needNewCode);


}
