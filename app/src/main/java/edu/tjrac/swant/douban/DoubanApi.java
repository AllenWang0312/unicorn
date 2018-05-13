package edu.tjrac.swant.douban;

import edu.tjrac.swant.douban.doubane.BookInfoBean;
import edu.tjrac.swant.douban.doubane.DoubanBookSearchResult;
import edu.tjrac.swant.douban.doubane.DoubanCelebrityBean;
import edu.tjrac.swant.douban.doubane.DoubanCommingMovieBean;
import edu.tjrac.swant.douban.doubane.DoubanIsShowingBean;
import edu.tjrac.swant.douban.doubane.DoubanMovieDetialBean;
import edu.tjrac.swant.douban.doubane.DoubanMovieSearchResultBean;
import edu.tjrac.swant.douban.doubane.DoubanSeriesBooksInfo;
import edu.tjrac.swant.douban.doubane.DoubanTop250;
import edu.tjrac.swant.douban.doubane.DoubanUSBox;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:13
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface DoubanApi {

    public static String URL = "https://api.douban.com/";

    @GET("v2/movie/subject/{id}")
    Observable<DoubanMovieDetialBean> getMovieDetialInfo(@Path("id") String id);

//    @GET("v2/movie/subject/{id}/photos")
//    Observable<DoubanMovieDetialBean> getMoviePhotosInfo(@Path("id") int id);


    @GET("v2/movie/subject/{id}/reviews")
    Observable<DoubanCelebrityBean> getReviewsInfo(@Path("id") int id);


    //    @GET("v2/movie/subject/{id}/comments")
//    Observable<DoubanCelebrityBean> getComments(@Path("id") int id);

    @GET("v2/movie/celebrity/{id}")
    Observable<DoubanCelebrityBean> getCelebrityInfo(@Path("id") String id);

//    @GET("v2/movie/celebrity/{id}/works")
//    Observable<DoubanCelebrityBean> getWorks(@Path("id") int id);

//    @GET("v2/movie/celebrity/{id}/photos")
//    Observable<DoubanCelebrityBean> getCelebrityPhotos(@Path("id") int id);

    @GET("v2/movie/search")
    Observable<DoubanMovieSearchResultBean> searchMovie(@Query("q") String str,
                                                   @Query("tag") String tag);

    @GET("v2/movie/top250")
    Observable<DoubanTop250> getTop250(@Query("start") int strat,@Query("count") int count);

    @GET("v2/movie/us_box")
    Observable<DoubanUSBox> getUSTop();

//需要授权
//    @GET("v2/movie/weekly")
//    Observable<DoubanUSBox> getWeeklyTop();

    //需要授权
//    @GET("v2/movie/new_movies")
//    Observable<DoubanUSBox> getNewTop();

    @GET("v2/movie/in_theaters")
    Observable<DoubanIsShowingBean> getIsShowingMovies();

    @GET("v2/movie/coming_soon")
    Observable<DoubanCommingMovieBean> getCommingMovies();

    @GET("v2/book/isbn/{name}")
    Observable<BookInfoBean> getBookInfo(@Path("name") String name);

    @GET("v2/book/{id}")
    Observable<BookInfoBean> getBookInfo(@Path("id") int id);

    @GET("v2/book/search")
    Observable<DoubanBookSearchResult> searchBook(
            @Query("q") String q,
            @Query("tag") String tag,
            @Query("start") int start,
            @Query("count") int count
    );

    @GET("v2/book/series/{id}/books")
    Observable<DoubanSeriesBooksInfo> getSeriesBooks(@Path("id") int id);

}
