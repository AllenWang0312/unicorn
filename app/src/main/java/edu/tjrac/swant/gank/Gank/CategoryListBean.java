package edu.tjrac.swant.gank.Gank;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/19 0019 下午 5:28
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class CategoryListBean {


    /**
     * count : 10
     * error : false
     * results : [{"desc":"还在用ListView？","ganhuo_id":"57334c9d67765903fb61c418","publishedAt":"2016-05-12T12:04:43.857000","readability":"","type":"Android","url":"http://www.jianshu.com/p/a92955be0a3e","who":"陈宇明"},{"desc":"Android Data Binding Adapter for ListView and RecyclerView.","ganhuo_id":"56cc6d23421aa95caa707ac9","publishedAt":"2015-07-21T04:10:11.904000","readability":"","type":"Android","url":"https://github.com/evant/binding-collection-adapter","who":"mthli"},{"desc":"在ListView中实现日历视图","ganhuo_id":"573d2a896776591c9fd0cd77","publishedAt":"2016-05-19T12:09:29.617000","readability":"","type":"Android","url":"https://github.com/traex/CalendarListview","who":"大熊"},{"desc":"给scrollview、listview、recycleview添加header与footer","ganhuo_id":"56cc6d23421aa95caa707ab2","publishedAt":"2015-07-02T03:50:48.024000","readability":"","type":"Android","url":"https://github.com/lawloretienne/QuickReturn","who":"Jason"},{"desc":"滑动listview的顶部菜单动画效果","ganhuo_id":"56cc6d26421aa95caa707d66","publishedAt":"2015-09-25T03:35:19.842000","readability":"","type":"Android","url":"https://github.com/xuechinahb/AnimatorMenu","who":"Jason"},{"desc":"一个滋瓷 Android RecyclerView, ListView, GridView, ScrollView ...的scroll","ganhuo_id":"56cc6d26421aa95caa707fa2","publishedAt":"2015-12-10T04:13:03.804000","readability":"","type":"Android","url":"https://github.com/EverythingMe/overscroll-decor","who":"有时放纵"},{"desc":"一个Quick Return UI Pattern页脚和页眉，支持RecyclerView, ListView和ScrollView.","ganhuo_id":"56cc6d1d421aa95caa7076db","publishedAt":"2015-07-02T03:50:48.024000","readability":"","type":"Android","url":"https://github.com/lawloretienne/QuickReturn","who":"有时放纵"},{"desc":"使用 twoway-view 的 ItemSelectionSupport 实现类似 ListView 的 RecyclerView 的单选，多选。","ganhuo_id":"56cc6d26421aa95caa707eca","publishedAt":"2015-11-24T09:58:31.251000","readability":"","type":"Android","url":"https://plus.google.com/+LucasRocha/posts/g9r83QYys2y","who":"Ailurus"},{"desc":"【腾讯Bugly干货分享】Android ListView 与 RecyclerView 对比浅析\u2014缓存机制","ganhuo_id":"5819912d421aa9137697460f","publishedAt":"2016-11-04T11:48:56.654000","readability":"","type":"Android","url":"https://zhuanlan.zhihu.com/p/23339185","who":"LHF"},{"desc":"Google Map 易用性封装，支持切换主题，ListView 展示等小功能。","ganhuo_id":"58f97515421aa954511ebee5","publishedAt":"2017-04-21T11:30:29.323000","readability":"","type":"Android","url":"https://github.com/bkhezry/ExtraMapUtils","who":"代码家"}]
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc : 还在用ListView？
         * ganhuo_id : 57334c9d67765903fb61c418
         * publishedAt : 2016-05-12T12:04:43.857000
         * readability :
         * type : Android
         * url : http://www.jianshu.com/p/a92955be0a3e
         * who : 陈宇明
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
