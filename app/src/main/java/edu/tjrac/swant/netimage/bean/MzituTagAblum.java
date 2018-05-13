package edu.tjrac.swant.netimage.bean;

import java.util.List;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MzituTagAblum {


    /**
     * info : {"home":"www.mzitu.com","path":"/tag/xinggan","page":"1","maxPage":""}
     * data : [{"path":"http://www.mzitu.com/119965","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"超级奶霸妲己Toxic胸器逼人 视觉冲击让人窒息"},{"path":"http://www.mzitu.com/119916","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"极品美女土肥圆矮挫穷白嫩肉体光滑至极"},{"path":"http://www.mzitu.com/119864","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"头条女神爱丽莎熟女韵味优雅不失性感"},{"path":"http://www.mzitu.com/119798","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"天生尤物尤柔美艳美百变 万种柔情"},{"path":"http://www.mzitu.com/119711","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"魅妍社波菲大尺度私房写真美不胜收"},{"path":"http://www.mzitu.com/119757","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"勾人小妖精LULU小璐璐: 你看到我饥渴的眼神了吗?"},{"path":"http://www.mzitu.com/119604","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"爆乳美女苏可可身材火爆令人瞠目"},{"path":"http://www.mzitu.com/119552","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"风骚熟女玄子上演各种放荡之姿激情挑逗"},{"path":"http://www.mzitu.com/119434","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"性感女皇王婉悠颜值爆表身材撩人"},{"path":"http://www.mzitu.com/119393","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"短发宝贝陆梓琪挤胸撅臀香艳销魂"},{"path":"http://www.mzitu.com/119348","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"美女宋KiKi私房照: 从少女到少妇的蜕变"},{"path":"http://www.mzitu.com/119309","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"日本写真女优谷桃子魅惑风情表情销魂"},{"path":"http://www.mzitu.com/119282","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"火辣赛车女郎温心怡撩人姿势风骚大胆"},{"path":"http://www.mzitu.com/119226","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"豪乳正妹高翘蜜桃臀解锁新姿势"},{"path":"http://www.mzitu.com/119162","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"小甜心杨晨晨释放野性 就是这么有韵味"},{"path":"http://www.mzitu.com/119122","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"性感美女何晨曦甜美婉约妩媚诱人"},{"path":"http://www.mzitu.com/119095","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"遇到这样狂野的女上司,hold不住啊!"},{"path":"http://www.mzitu.com/119045","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"超正美眉萌琪琪家中扮演寂寞少妇"},{"path":"http://www.mzitu.com/118979","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"软妹子艾可丝从青涩稚嫩向成熟性感蜕变"},{"path":"http://www.mzitu.com/118874","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"巨乳美女奶糖Rabbit纯天然G罩杯让你看花眼"},{"path":"http://www.mzitu.com/118808","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"性感小甜心上演致命诱惑 微胖极品令人回味无穷"},{"path":"http://www.mzitu.com/118759","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"豪放熟女西希白兔一丝不挂露出蜜桃美臀好撩人"},{"path":"http://www.mzitu.com/118659","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"美女模特尤妮丝公园坦胸露乳拍写真 无视路人目光"},{"path":"http://www.mzitu.com/118501","cover":"http://i.meizitu.net/pfiles/img/lazy.png","title":"松果儿热辣勾魂真空上阵 透视蕾丝大胆展示傲人曲线"}]
     * status : faild
     * code : -1
     * message :
     */

    private InfoBean info;
    private String status;
    private int code;
    private String message;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class InfoBean {
        /**
         * home : www.mzitu.com
         * path : /tag/xinggan
         * page : 1
         * maxPage :
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
         * path : http://www.mzitu.com/119965
         * cover : http://i.meizitu.net/pfiles/img/lazy.png
         * title : 超级奶霸妲己Toxic胸器逼人 视觉冲击让人窒息
         */

        private String path;
        private String cover;
        private String title;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
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
