package edu.tjrac.swant.gank;


import edu.tjrac.swant.gank.Gank.CategoryListBean;
import edu.tjrac.swant.gank.Gank.HistroyBean;
import edu.tjrac.swant.gank.Gank.HistroyDateBean;
import edu.tjrac.swant.gank.Gank.HistroyDayBean;
import edu.tjrac.swant.gank.Gank.RandomDataBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/19 0019 下午 5:24
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface GankApi {
    public static String URL = "http://gank.io/";


    //count 最大 50
    //category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App

    @GET("api/search/query/listview/category/{os}/count/{count}/page/{page}")
    Observable<CategoryListBean> getCategoryList(@Path("os") String os,@Path("count") int pageCount,@Path("page") int page);

    @GET("api/history/content/{count}/{page}")
    Observable<HistroyBean> getHistroyBean(@Path("count") int count, @Path("page") int page);

    //获取特定日期网站数据:
    // 2016/05/11
    @GET("api/history/content/day/{day}")
    Observable<HistroyDayBean> getHistroyDayBean(@Path("day") String day);


    //获取发过干货日期接口:

    @GET("api/day/history")
    Observable<HistroyDateBean> getHistoryDateBean();

    @GET("api/random/data/{type}/{num}")
    Observable<RandomDataBean> getRandomDataBean(@Path("type") String type, @Path("num") int num);


}
