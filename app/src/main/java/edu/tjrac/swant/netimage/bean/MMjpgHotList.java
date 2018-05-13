package edu.tjrac.swant.netimage.bean;

import java.util.List;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MMjpgHotList {


    /**
     * info : {"home":"http://www.mmjpg.com/","path":"/tag/xinggan","page":"1","maxPage":31}
     * data : [{"url":"http://www.mmjpg.com/mm/1252","cover":"http://img.mmjpg.com/small/2018/1252.jpg","title":"美女尤妮丝豹纹内衣写真身材异常性感"},{"url":"http://www.mmjpg.com/mm/1226","cover":"http://img.mmjpg.com/small/2018/1226.jpg","title":"刘钰儿劲爆的好身材内衣都被撑到变形"},{"url":"http://www.mmjpg.com/mm/1215","cover":"http://img.mmjpg.com/small/2017/1215.jpg","title":"李颖诗超级火辣性感装扮带你度过周末"},{"url":"http://www.mmjpg.com/mm/1207","cover":"http://img.mmjpg.com/small/2017/1207.jpg","title":"身材超好的妹子李梓熙美胸美臀写真图"},{"url":"http://www.mmjpg.com/mm/1201","cover":"http://img.mmjpg.com/small/2017/1201.jpg","title":"女神半透诱惑的圆润双峰让人无法抵御"},{"url":"http://www.mmjpg.com/mm/1197","cover":"http://img.mmjpg.com/small/2017/1197.jpg","title":"丰满美女娜露各种姿势特写让你看个够"},{"url":"http://www.mmjpg.com/mm/1192","cover":"http://img.mmjpg.com/small/2017/1192.jpg","title":"女神刘钰儿开背毛衣写真魅力无法抵御"},{"url":"http://www.mmjpg.com/mm/1189","cover":"http://img.mmjpg.com/small/2017/1189.jpg","title":"大胸妹孟狐狸酥胸如凝脂白玉香艳无比"},{"url":"http://www.mmjpg.com/mm/1187","cover":"http://img.mmjpg.com/small/2017/1187.jpg","title":"女神妲己内衣下雪乳娇躯令人鼻血狂流"},{"url":"http://www.mmjpg.com/mm/1186","cover":"http://img.mmjpg.com/small/2017/1186.jpg","title":"性感美艳娇娘唐琪儿白皙美臀夺人心目"},{"url":"http://www.mmjpg.com/mm/1167","cover":"http://img.mmjpg.com/small/2017/1167.jpg","title":"性感妹子宋KiKi肚兜下的美胸挺拔诱人"},{"url":"http://www.mmjpg.com/mm/1160","cover":"http://img.mmjpg.com/small/2017/1160.jpg","title":"乖巧猫耳女仆杜花花藏不住的性感妖娆"},{"url":"http://www.mmjpg.com/mm/1158","cover":"http://img.mmjpg.com/small/2017/1158.jpg","title":"性感模特蛋蛋雪白的肌肤想要一亲芳泽"},{"url":"http://www.mmjpg.com/mm/1152","cover":"http://img.mmjpg.com/small/2017/1152.jpg","title":"必看!性感模特雪千寻巨乳肥臀惹火身材"},{"url":"http://www.mmjpg.com/mm/1151","cover":"http://img.mmjpg.com/small/2017/1151.jpg","title":"女神刘钰儿穿情趣内衣想要去一亲芳泽"}]
     * status : success
     * code : 200
     * message :
     * page : 1
     */

    private InfoBean info;
    private String status;
    private int code;
    private String message;
    private String page;
    private List<DataBean> data;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class InfoBean {
        /**
         * home : http://www.mmjpg.com/
         * path : /tag/xinggan
         * page : 1
         * maxPage : 31
         */

        private String home;
        private String path;

        private String page;
        private String maxPage;

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getMaxPage() {
            return maxPage;
        }

        public void setMaxPage(String maxPage) {
            this.maxPage = maxPage;
        }
    }

    public static class DataBean {
        /**
         * url : http://www.mmjpg.com/mm/1252
         * cover : http://img.mmjpg.com/small/2018/1252.jpg
         * title : 美女尤妮丝豹纹内衣写真身材异常性感
         */

        private String url;
        private String cover;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
