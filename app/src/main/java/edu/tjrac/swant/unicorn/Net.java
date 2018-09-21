package edu.tjrac.swant.unicorn;


import com.apollographql.apollo.ApolloClient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import edu.tjrac.swant.douban.DoubanApi;
import edu.tjrac.swant.gank.GankApi;
import edu.tjrac.swant.kaiyan.KaiyanApi;
import edu.tjrac.swant.qqmusic.QQMusicApi;
import edu.tjrac.swant.qqmusic.QQMusicKeyApi;
import edu.tjrac.swant.todo.JDApi;
import edu.tjrac.swant.zhihu.ZhiHuApi;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.tjrac.swant.unicorn.Config.LOG;


/**
 * retrofit
 * Created by palmy on 2016/11/1.
 */
public class Net {
    private final static Object mRetrofitLock = new Object();
    private static volatile OkHttpClient sOkHttpClient;
    //    private static volatile OkHttpClient
    private static Net instance;
    private static ZhiHuApi zhihuapi;
    private static DoubanApi doubanapi;
    private static GankApi gankapi;

    public static Net getInstance() {
        if (instance == null) {
            synchronized (Net.class) {
                if (instance == null) {
                    instance = new Net();
                }
            }
        }
        return instance;
    }

    HashMap<String, Retrofit> retrofits = new HashMap<>();

    public Retrofit getRetrofit(String baseUrl) {

        synchronized (mRetrofitLock) {
            if (retrofits.get(baseUrl) == null) {
                Retrofit retrofit = new Retrofit.Builder().client(getOkHttpClient())
                        .baseUrl(baseUrl)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                retrofits.put(baseUrl, retrofit);
                return retrofit;
            } else {
                return retrofits.get(baseUrl);
            }
        }
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (Net.class) {
                Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
//                            .addInterceptor(new RewriteCacheInterceptor())//无网络缓存(重复请求会使用缓存而不使用网络请求)
//                            .addNetworkInterceptor(new RewriteCacheInterceptor())
                            .cache(cache)//缓存大小
                            .addInterceptor(getLoggingInterceptor())//log日志
//                            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
//                            .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//使用保存在内存中的cookie
//                            .cookieJar(new CookieJarImpl(new PersistentCookieStore()))//使用保存在sp中的cookie
//                            .addInterceptor(new ReadCookiesInterceptor())//读cookie
//                            .addInterceptor(new SaveCookiesInterceptor())//写cookie
//                            .addInterceptor(new TokenInterceptor())//添加token头
//                            .addNetworkInterceptor(new StethoInterceptor())//chrome://inspect
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }

    private Interceptor getLoggingInterceptor() {
        Interceptor loggingInterceptor;
        if (LOG) {
            //打印完整的log
            loggingInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    long t1 = System.nanoTime();
//                    Logger.i(String.format("request %s", request.url()));
                    Response response = chain.proceed(request);
                    long t2 = System.nanoTime();
//                    Logger.i(String.format(Locale.getDefault(), "response %s%ncost %.1fms",
//                            response.request().url(), (t2 - t1) / 1e6d));
                    return response;
                }
            };
        } else {
            //不打印log
            loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

//    public <T> T get(Class<T> tClass) {
//        return getRetrofit().create(tClass);
//    }


    //add
    public ZhiHuApi getZhihuService() {
        if (zhihuapi == null) {
            zhihuapi = getRetrofit(ZhiHuApi.URL).create(ZhiHuApi.class);
        }
        return zhihuapi;
    }

    //add
    public DoubanApi getDouBanService() {
        if (doubanapi == null) {
            doubanapi = getRetrofit(DoubanApi.URL).create(DoubanApi.class);
        }
        return doubanapi;
    }

    //add
    public GankApi getGankService() {
        if (gankapi == null) {
            gankapi = getRetrofit(GankApi.URL).create(GankApi.class);
        }
        return gankapi;
    }


    private static UnicornApi unicornapi;

    public UnicornApi getUnicornApi() {
        if (unicornapi == null) {
            unicornapi = getRetrofit(UnicornApi.Companion.getURL()).create(UnicornApi.class);
        }
        return unicornapi;
    }

    private static QQMusicApi qqmusicapi;

    public QQMusicApi getQQmusicApi() {
        if (qqmusicapi == null) {
            qqmusicapi = getRetrofit(QQMusicApi.URL).create(QQMusicApi.class);
        }
        return qqmusicapi;
    }

    private static QQMusicKeyApi qqmusickeyapi;

    public QQMusicKeyApi getQQmusicKeyApi() {
        if (qqmusickeyapi == null) {
            qqmusickeyapi = getRetrofit(QQMusicKeyApi.URL).create(QQMusicKeyApi.class);
        }
        return qqmusickeyapi;
    }

    private static KaiyanApi kaiyanapi;

    public KaiyanApi getKaiyanApi() {
        if (kaiyanapi == null) {
            kaiyanapi = getRetrofit(KaiyanApi.URL).create(KaiyanApi.class);
        }
        return kaiyanapi;
    }

    static JDApi jdapi;

    public JDApi getJDApi() {
        if (jdapi == null) {
            jdapi = getRetrofit(JDApi.URL).create(JDApi.class);
        }
        return jdapi;
    }

    public static void initGraphQL() {
      ApolloClient apllo=  ApolloClient.builder()
                .serverUrl("localhost:8080")
                .okHttpClient(sOkHttpClient)
//                    .normalizedCache()
                .build();
    }
}
