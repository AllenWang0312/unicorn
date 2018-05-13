package edu.tjrac.swant.kaiyan.kaiyan;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 4:14
 * 修改人:
 * 修改时间:
 * 修改备注:
 */


public class AuthorBean {
    /**
     * id : 345
     * icon : http://img.kaiyanapp.com/40ab9e87b2bc1a4a983bb5d9b38a871f.jpeg?imageMogr2/quality/60
     * name : 索尼影视娱乐
     * description : 索尼影视娱乐有限公司（Sony Pictures Entertainment）在全球的业务包括：电影的制作和发行，电视节目的制作和发行，数码娱乐节目的制作和发行，全球范围的电视频道投资，购买和发行家庭娱乐节目，经营影视拍摄设施，开发新型娱乐产品、 服务和技术，以及在67个国家进行电影娱乐产品销售。
     * link :
     * latestReleaseTime : 1521699006000
     * videoNum : 91
     * adTrack : null
     * follow : {"itemType":"author","itemId":345,"followed":false}
     * shield : {"itemType":"author","itemId":345,"shielded":false}
     * approvedNotReadyVideoCount : 0
     * ifPgc : true
     */

    private int id;
    private String icon;
    private String name;
    private String description;
    private String link;
    private long latestReleaseTime;
    private int videoNum;
    private Object adTrack;
    private FollowBean follow;
    private ShieldBean shield;
    private int approvedNotReadyVideoCount;
    private boolean ifPgc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public void setLatestReleaseTime(long latestReleaseTime) {
        this.latestReleaseTime = latestReleaseTime;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public FollowBean getFollow() {
        return follow;
    }

    public void setFollow(FollowBean follow) {
        this.follow = follow;
    }

    public ShieldBean getShield() {
        return shield;
    }

    public void setShield(ShieldBean shield) {
        this.shield = shield;
    }

    public int getApprovedNotReadyVideoCount() {
        return approvedNotReadyVideoCount;
    }

    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
    }

    public boolean isIfPgc() {
        return ifPgc;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

    public static class FollowBean {
        /**
         * itemType : author
         * itemId : 345
         * followed : false
         */

        private String itemType;
        private int itemId;
        private boolean followed;

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }
    }

    public static class ShieldBean {
        /**
         * itemType : author
         * itemId : 345
         * shielded : false
         */

        private String itemType;
        private int itemId;
        private boolean shielded;

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public boolean isShielded() {
            return shielded;
        }

        public void setShielded(boolean shielded) {
            this.shielded = shielded;
        }
    }
}
