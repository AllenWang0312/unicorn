package edu.tjrac.swant.kaiyan;


import edu.tjrac.swant.kaiyan.kaiyan.AuthorsBean;
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHomeBean;
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHotBean;
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanTabBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 下午 3:16
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface KaiyanApi {

    public static String URL = "http://baobab.kaiyanapp.com/";

    @GET("api/v4/tabs/selected")
    Observable<KaiYanHomeBean> getHomeBean();

    @GET("api/v4/discovery")
    Observable<KaiYanTabBean> getTabs();

    @GET("api/v4/discovery/{tag}")
    Observable<KaiYanHotBean> getTagBeans(@Path("tag") String tag);

    @GET("api/v4/pgcs/all")
    Observable<AuthorsBean> getAuthorsBean();

}
