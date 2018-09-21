package edu.tjrac.swant.unicorn

import edu.tjrac.swant.netimage.bean.MMjpgHotList
import edu.tjrac.swant.netimage.bean.MzituAblumDetial
import edu.tjrac.swant.netimage.bean.MzituTagAblum
import edu.tjrac.swant.netimage.bean.MzituZhuanTiAblum
import edu.tjrac.swant.unicorn.bean.Result
import edu.tjrac.swant.unicorn.bean.User
import edu.tjrac.swant.unicorn.bean.response.FriendList_0002
import edu.tjrac.swant.unicorn.bean.response.MusicInfo3001
import edu.tjrac.swant.unicorn.bean.response.RegisterResult0002
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by wpc on 2018/2/8 0008.
 */

interface UnicornApi {

    companion object {
        val URL = Config.UNICORN_URL
    }



    //    @GET("user/login")
    //    Call<LoginResult0001> login(@Query("username") String name,
    //                                               @Query("password") String psd);

    @HTTP(method = "POST", path = "api/user/regist", hasBody = true)
    fun regist(@Body user: User): Call<RegisterResult0002>

    @POST("api/user/login")
    fun login(trade: String, json: String): Call<Result<User>>

    @POST("api/user/token")
    fun login(token: String): Call<Result<User>>

    @get:HTTP(method = "GET", path = "api/user/friendlist")
    val friendList: Call<FriendList_0002>

    @HTTP(method = "GET", path = "api/music/{id}")
    fun getMusicInfo(@Path("id") id: Int): Call<MusicInfo3001>

    @HTTP(method = "GET", path = "api/image/mmjpg")
    fun getMMjpgAblumList(@Query("path") path: String, @Query("page") page: Int): Call<MMjpgHotList>


    @get:HTTP(method = "GET", path = "api/image/mzitu/zhuanti")
    val mzituZhuanTi: Call<MzituZhuanTiAblum>

    @HTTP(method = "GET", path = "api/image/mzitu/tag")
    fun getMzituTag(@Query("tag") tag: String, @Query("page") page: Int): Call<MzituTagAblum>

    @HTTP(method = "GET", path = "api/image/mzitu/")
    fun getMzituAblums(@Query("url") url: String, @Query("page") page: Int): Call<MzituTagAblum>

    @HTTP(method = "GET", path = "api/image/mzitu/")
    fun getMzituAblumDetial(@Query("id") id: Int): Call<MzituAblumDetial>


    @HTTP(method = "GET", path = "api/img/mmjpg/detial")
    fun getMMjpgDetialList(@Query("id") id: Int): Call<MMjpgHotList>


    //    @GET("/0001/{user}/repos")
    //    Call<ResponseBody>  getBlog（@Path("user") String user ）;

}
