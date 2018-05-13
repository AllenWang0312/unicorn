package edu.tjrac.swant.unicorn;

import edu.tjrac.swant.netimage.bean.MMjpgHotList;
import edu.tjrac.swant.netimage.bean.MzituAblumDetial;
import edu.tjrac.swant.netimage.bean.MzituTagAblum;
import edu.tjrac.swant.netimage.bean.MzituZhuanTiAblum;
import edu.tjrac.swant.unicorn.bean.Result;
import edu.tjrac.swant.unicorn.bean.User;
import edu.tjrac.swant.unicorn.bean.response.FriendList_0002;
import edu.tjrac.swant.unicorn.bean.response.MusicInfo3001;
import edu.tjrac.swant.unicorn.bean.response.RegisterResult0002;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wpc on 2018/2/8 0008.
 */

public interface UnicornApi {


    public  static  String URL = Config.UNICORN_URL;

//    @GET("user/login")
//    Call<LoginResult0001> login(@Query("username") String name,
//                                               @Query("password") String psd);

    @HTTP(method = "POST",path = "api/user/regist",hasBody = true)
    Call<RegisterResult0002> regist(@Body User user);

    @POST("api/user/login")
    Call<Result<User>> login(String trade, String json);

    @POST("api/user/token")
    Call<Result<User>> login(String token);

    @HTTP(method = "GET", path = "api/user/friendlist")
    Call<FriendList_0002> getFriendList();

    @HTTP(method = "GET", path = "api/music/{id}")
    Call<MusicInfo3001> getMusicInfo(@Path("id") int id);

    @HTTP(method = "GET",path = "api/image/mmjpg")
    Call<MMjpgHotList> getMMjpgAblumList(@Query("path")String path,@Query("page")int page );


    @HTTP(method = "GET",path = "api/image/mzitu/zhuanti")
    Call<MzituZhuanTiAblum> getMzituZhuanTi();

    @HTTP(method = "GET",path = "api/image/mzitu/tag")
    Call<MzituTagAblum> getMzituTag(@Query("tag")String tag, @Query("page")int page);

    @HTTP(method = "GET",path = "api/image/mzitu/")
    Call<MzituTagAblum> getMzituAblums(@Query("url")String url,@Query("page")int page);

    @HTTP(method = "GET",path = "api/image/mzitu/")
    Call<MzituAblumDetial> getMzituAblumDetial(@Query("id")int id);


    @HTTP(method = "GET",path = "api/img/mmjpg/detial")
    Call<MMjpgHotList> getMMjpgDetialList(@Query("id")int id);

//    @GET("/0001/{user}/repos")
//    Call<ResponseBody>  getBlog（@Path("user") String user ）;

}
