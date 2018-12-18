package edu.tjrac.swant.unicorn


//import com.apollographql.apollo.ApolloClient
import edu.tjrac.swant.douban.DoubanApi
import edu.tjrac.swant.gank.GankApi
import edu.tjrac.swant.kaiyan.KaiyanApi
import edu.tjrac.swant.qqmusic.QQMusicApi
import edu.tjrac.swant.qqmusic.QQMusicKeyApi
import edu.tjrac.swant.todo.JDApi
import edu.tjrac.swant.unicorn.Config.LOG
import edu.tjrac.swant.zhihu.ZhiHuApi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*


/**
 * retrofit
 * Created by palmy on 2016/11/1.
 */
class Net {

    internal var retrofits = HashMap<String, Retrofit>()

    fun getRetrofit(baseUrl: String): Retrofit {

        synchronized(mRetrofitLock) {
            if (retrofits[baseUrl] == null) {
                val retrofit = Retrofit.Builder().client(okHttpClient)
                        .baseUrl(baseUrl)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                retrofits.put(baseUrl, retrofit)
                return retrofit
            } else {
                return retrofits[baseUrl]!!
            }
        }
    }

    private //                            .addInterceptor(new RewriteCacheInterceptor())//无网络缓存(重复请求会使用缓存而不使用网络请求)
            //                            .addNetworkInterceptor(new RewriteCacheInterceptor())
            //缓存大小
            //log日志
            //                            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            //                            .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//使用保存在内存中的cookie
            //                            .cookieJar(new CookieJarImpl(new PersistentCookieStore()))//使用保存在sp中的cookie
            //                            .addInterceptor(new ReadCookiesInterceptor())//读cookie
            //                            .addInterceptor(new SaveCookiesInterceptor())//写cookie
            //                            .addInterceptor(new TokenInterceptor())//添加token头
            //                            .addNetworkInterceptor(new StethoInterceptor())//chrome://inspect
    val okHttpClient: OkHttpClient
        get() {
            if (sOkHttpClient == null) {
                synchronized(Net::class.java) {
                    val cache = Cache(File(App.getInstance().cacheDir, "HttpCache"),
                            (1024 * 1024 * 100).toLong())
                    if (sOkHttpClient == null) {
                        sOkHttpClient = OkHttpClient.Builder()
                                .cache(cache)
                                .addInterceptor(loggingInterceptor)
                                .build()
                    }
                }
            }
            return sOkHttpClient!!
        }

    private //打印完整的log
            //                    Logger.i(String.format("request %s", request.url()));
            //                    Logger.i(String.format(Locale.getDefault(), "response %s%ncost %.1fms",
            //                            response.request().url(), (t2 - t1) / 1e6d));
            //不打印log
    val loggingInterceptor: Interceptor
        get() {
            val loggingInterceptor: Interceptor
            if (LOG) {
                loggingInterceptor = Interceptor { chain ->
                    val request = chain.request()
                    val t1 = System.nanoTime()
                    val response = chain.proceed(request)
                    val t2 = System.nanoTime()
                    response
                }
            } else {
                loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            return loggingInterceptor
        }

    //    public <T> T get(Class<T> tClass) {
    //        return getRetrofit().create(tClass);
    //    }


    //add
    val zhihuService: ZhiHuApi
        get() {
            if (zhihuapi == null) {
                zhihuapi = getRetrofit(ZhiHuApi.URL).create(ZhiHuApi::class.java)
            }
            return zhihuapi!!
        }

    //add
    val douBanService: DoubanApi
        get() {
            if (doubanapi == null) {
                doubanapi = getRetrofit(DoubanApi.URL).create(DoubanApi::class.java)
            }
            return doubanapi!!
        }

    //add
    val gankService: GankApi
        get() {
            if (gankapi == null) {
                gankapi = getRetrofit(GankApi.URL).create(GankApi::class.java)
            }
            return gankapi!!
        }

    val unicornApi: UnicornApi
        get() {
            if (unicornapi == null) {
                unicornapi = getRetrofit(UnicornApi.URL).create(UnicornApi::class.java)
            }
            return unicornapi!!
        }

    val qQmusicApi: QQMusicApi
        get() {
            if (qqmusicapi == null) {
                qqmusicapi = getRetrofit(QQMusicApi.URL).create(QQMusicApi::class.java)
            }
            return qqmusicapi!!
        }

    val qQmusicKeyApi: QQMusicKeyApi
        get() {
            if (qqmusickeyapi == null) {
                qqmusickeyapi = getRetrofit(QQMusicKeyApi.URL).create(QQMusicKeyApi::class.java)
            }
            return qqmusickeyapi!!
        }

    val kaiyanApi: KaiyanApi
        get() {
            if (kaiyanapi == null) {
                kaiyanapi = getRetrofit(KaiyanApi.URL).create(KaiyanApi::class.java)
            }
            return kaiyanapi!!
        }

    val jdApi: JDApi
        get() {
            if (jdapi == null) {
                jdapi = getRetrofit(JDApi.URL).create(JDApi::class.java)
            }
            return jdapi!!
        }

    companion object {
        private val mRetrofitLock = Any()
        @Volatile private var sOkHttpClient: OkHttpClient? = null
        //    private static volatile OkHttpClient
        //kotlin 线程安全单例
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Net()
        }
        private var zhihuapi: ZhiHuApi? = null
        private var doubanapi: DoubanApi? = null
        private var gankapi: GankApi? = null




        private var unicornapi: UnicornApi? = null

        private var qqmusicapi: QQMusicApi? = null

        private var qqmusickeyapi: QQMusicKeyApi? = null

        private var kaiyanapi: KaiyanApi? = null

        internal var jdapi: JDApi? = null

//        fun initGraphQL() {
//            val apllo = ApolloClient.builder()
//                    .serverUrl("localhost:8080")
//                    .okHttpClient(sOkHttpClient!!)
//                    //                    .normalizedCache()
//                    .build()
//        }
    }
}
