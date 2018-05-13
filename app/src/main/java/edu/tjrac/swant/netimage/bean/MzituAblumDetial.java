package edu.tjrac.swant.netimage.bean;

import java.util.List;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MzituAblumDetial {

    /**
     * info : {"home":"www.mzitu.com","path":"","page":"","maxPage":""}
     * data : ["http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg","http://i.meizitu.net/2018/01/31b01.jpg"]
     * status : faild
     * code : -1
     * message :
     */

    private InfoBean info;
    private String status;
    private int code;
    private String message;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public static class InfoBean {
        /**
         * home : www.mzitu.com
         * path :
         * page :
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
}
