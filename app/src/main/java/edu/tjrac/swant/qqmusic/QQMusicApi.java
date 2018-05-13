package edu.tjrac.swant.qqmusic;

import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicSearchResultBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 5:32
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface QQMusicApi {
    public static String URL = "http://s.music.qq.com/";


    //fcgi-bin/music_search_new_platform?t=0&n={2}&aggr=1&cr=1&loginUin={3}
    // &format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json
    // &needNewCode=0&p={1}&catZhida=0&remoteplace=sizer.newclient.next_song&w={0}
    //0 searchstring 1 page 2 count 3 qqid
    @GET("fcgi-bin/music_search_new_platform")
    Observable<QQMusicSearchResultBean> searchMusic(@Query("t") int t,
                                                    @Query("n") int n,
                                                    @Query("aggr") int aggr,
                                                    @Query("cr") int cr,
                                                    @Query("loginUin") int loginUin,
                                                    @Query("format") String format,
                                                    @Query("inCharset") String charset,
                                                    @Query("outCharset") String outCharset,
                                                    @Query("notice") int notice,
                                                    @Query("platform") String platform,
                                                    @Query("needNewCode") int needNewCode,
                                                    @Query("p") int p,
                                                    @Query("catZhida") int catZhida,
                                                    @Query("remoteplace") String remoteplace,
                                                    @Query("w") String w);
//播放ying音乐
//    http://base.music.qq.com/fcgi-bin/fcg_musicexpress.fcg?json=3&loginUin={0}&format=jsonp
// &inCharset=GB2312&outCharset=GB2312&notice=0&platform=yqq&needNewCode=0

    @GET("fcgi-bin/fcg_musicexpress.fcg?json=3&loginUin={qqid}&format=jsonp&inCharset=GB2312&outCharset=GB2312&notice=0&platform=yqq&needNewCode=0")
    Observable<String> easyGetQQmusicKey(@Path("qqid") String qqid);





    //播放
    public static final String playUrl = "http://cc.stream.qqmusic.qq.com/C200{ID}.m4a?vkey={Key}&fromtag=0";

    public static String getPlayUrl(String id,String key){

        return playUrl.replace("{ID}",id).replace("{Key}",key);
    }

    //图片
    //http://imgcache.qq.com/music/photo/mid_album_90/{Img的倒数第二个字符}/{上面取到的Img的最后一个字符}/{img}.jpg
    public static final String imagePath = "http://imgcache.qq.com/music/photo/mid_album_90/";

    public static String getImagePath(String image){

        return imagePath
                +image.charAt(image.length()-1)
                +"/"+image.charAt(image.length())
                +"/"+image+".jpg";
    }
    //歌词
    public static final String lrcPath = "http://music.qq.com/miniportal/static/lyric/{Lrc%100}/{Lrc}.xml";

    public static String getLrcPath(String lrc){
       Integer i= Integer.valueOf(lrc);

        return lrcPath.replace("{Lrc%100}",i%100+"")
                .replace("{Lrc}",lrc);
    }
}
