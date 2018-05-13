package edu.tjrac.swant.todo;


import edu.tjrac.swant.qqmusic.PackageSearchResult;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wpc on 2018/4/27.
 */

public interface JDApi {

    public static String URL = "https://way.jd.com/";


    String key = "c8c1f952008d1a032e33c2e315aec9e8";

    @POST("LICZX/licKD")
    Observable<PackageSearchResult> searchPackage(@Query("no") String no,
                                                  @Query("appkey") String key);


}
