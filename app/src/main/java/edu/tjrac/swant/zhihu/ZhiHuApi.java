package edu.tjrac.swant.zhihu;


import edu.tjrac.swant.unicorn.Config;
import edu.tjrac.swant.zhihu.zhihu.BeforeDataBean;
import edu.tjrac.swant.zhihu.zhihu.Comments;
import edu.tjrac.swant.zhihu.zhihu.DailyReportBean;
import edu.tjrac.swant.zhihu.zhihu.StoryExtra;
import edu.tjrac.swant.zhihu.zhihu.StroyDetialBean;
import edu.tjrac.swant.zhihu.zhihu.ThemeDetialBean;
import edu.tjrac.swant.zhihu.zhihu.ZhihuDailyBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/15 0015 下午 5:25
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface ZhiHuApi {


    public static final String URL = Config.ZHIHU_URL;

    @GET("api/4/news/latest")
    Observable<ZhihuDailyBean> requestZhihuDaily();

    @GET("api/4/news/{id}")
    Observable<StroyDetialBean> requestStroyDetial(@Path("id") int id);

    @GET("api/4/news/before/{date}")
    Observable<BeforeDataBean> requestBeforeData(@Path("date") String date);

    @GET("api/4/story-extra/{id}")
    Observable<StoryExtra> requestStoryExtra(@Path("id") int id);

    @GET("api/4/story/{id}/long-comments")
    Observable<Comments> requestStoryComments(@Path("id")int id);

    @GET("api/4/story/{id}/short-comments")
    Observable<Comments> requestStoryShortComments(@Path("id")int id);

    @GET("api/4/themes")
    Observable<DailyReportBean> requestDailyReportThemes();

    @GET("api/4/theme/{id}")
    Observable<ThemeDetialBean> requestThemeData(@Path("id") int id, @Query("page") int page);

}
