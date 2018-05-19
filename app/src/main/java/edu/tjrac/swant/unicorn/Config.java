package edu.tjrac.swant.unicorn;

import com.yckj.baselib.util.FileUtils;

/**
 * Created by wpc on 2018/3/12 0012.
 */

public class Config {


    public static final boolean LOG = true;

    //public static String URL = "http://10.0.1.39:8888";
//    public static String UNICORN_URL = "http://10.0.1.48:8082";
    public static String UNICORN_URL = "http://180.76.233.124:8080";
    //public static String URL = "http://192.168.42.67:8082";


    public static String packageName =
//            "edu.tjrac.swant." +
            "unicorn";

    public static String ZHIHU_URL = "https://news-at.zhihu.com/";

    public static String fileCacheDir = FileUtils.getExtSDCardPath() +packageName+ "/download/";


    public class SP{
        public static final String GallerySetting="gallery_setting";

        public static final String sortType="sortType";
        public static final String sortOrdition="sortOrdition";
    }
}
