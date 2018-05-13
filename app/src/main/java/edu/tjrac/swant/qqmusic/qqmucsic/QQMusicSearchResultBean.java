package edu.tjrac.swant.qqmusic.qqmucsic;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 上午 8:40
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class QQMusicSearchResultBean {


    /**
     * code : 0
     * data : {"keyword":"","priority":0,"qc":[],"semantic":{"curnum":0,"curpage":1,"list":[],"totalnum":0},"song":{"curnum":5,"curpage":1,"list":[{"albumName_hilight":"等你下课","chinesesinger":0,"docid":"11127252070543836748","f":"212877900|等你下课(with 杨瑞代) (;)|4558|周杰伦|3883404|等你下课|0|270|4|1|0|10802353|4321060|0|0|31780875|31922907|6045129|6521861|0|001J5QJL1pRQYB|0025NhlN2yWrP4|003bSL0v4bpKAx|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"等你下课(with 杨瑞代)","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"","nt":10015,"only":1,"pubTime":1516204800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"等你下课(with 杨瑞代)","t":1,"tag":0,"ver":0},{"albumName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>的床边故事","chinesesinger":0,"docid":"7065911643897223382","f":"107192078|告白气球 (;)|4558|周杰伦|1458791|周杰伦的床边故事|1856582|215|1|1|0|8608939|3443771|2116000|0|24929083|24971563|5001304|5191087|0|003OUlho2HcRHC|0025NhlN2yWrP4|003RMaRI1iFoYd|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"告白气球","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"u00222le4ox","nt":10000,"only":1,"pubTime":1466697600,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"告白气球","t":1,"tag":0,"ver":0},{"albumName_hilight":"魔杰座","chinesesinger":0,"docid":"2257006635702513351","f":"449205|稻香 (;)|4558|周杰伦|36062|魔杰座|1813383|223|9|1|0|8929849|3581117|320000|0|25245000|26093502|5156630|5354369|0|003aAYrm3GE0Ac|0025NhlN2yWrP4|002Neh8l0uciQZ|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"稻香","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"l0013kgn1be","nt":10006,"only":1,"pubTime":1224000000,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"稻香","t":1,"tag":0,"ver":0},{"albumName_hilight":"我很忙","chinesesinger":0,"docid":"4953153690890266244","f":"410316|青花瓷 (;)|4558|周杰伦|33021|我很忙|1942555|239|8|1|0|9573872|3836490|320000|0|25541938|26237796|5414428|5617369|0|002qU5aY3Qu24y|0025NhlN2yWrP4|002eFUFm2XYZ7z|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"青花瓷","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"l00131om505","nt":10003,"only":1,"pubTime":1193932800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"青花瓷","t":1,"tag":0,"ver":0},{"albumName_hilight":"叶惠美","chinesesinger":0,"docid":"4660176279196411288","f":"97773|晴天 (;)|4558|周杰伦|8220|叶惠美|2186317|269|9|1|0|10793267|4319991|320000|0|30143423|31518872|5871273|6308305|0|0039MnYb0qxYhV|0025NhlN2yWrP4|000MkMni19ClKG|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"晴天","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"z0014q87sal","nt":10005,"only":1,"pubTime":1059580800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"晴天","t":1,"tag":0,"ver":0}],"totalnum":388},"totaltime":8.3E-5,"zhida":{"chinesesinger":0,"type":0}}
     * message :
     * notice :
     * subcode : 0
     * time : 1521592767
     * tips :
     */

    private int code;
    private DataBean data;
    private String message;
    private String notice;
    private int subcode;
    private int time;
    private String tips;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getSubcode() {
        return subcode;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public static class DataBean {
        /**
         * keyword :
         * priority : 0
         * qc : []
         * semantic : {"curnum":0,"curpage":1,"list":[],"totalnum":0}
         * song : {"curnum":5,"curpage":1,"list":[{"albumName_hilight":"等你下课","chinesesinger":0,"docid":"11127252070543836748","f":"212877900|等你下课(with 杨瑞代) (;)|4558|周杰伦|3883404|等你下课|0|270|4|1|0|10802353|4321060|0|0|31780875|31922907|6045129|6521861|0|001J5QJL1pRQYB|0025NhlN2yWrP4|003bSL0v4bpKAx|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"等你下课(with 杨瑞代)","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"","nt":10015,"only":1,"pubTime":1516204800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"等你下课(with 杨瑞代)","t":1,"tag":0,"ver":0},{"albumName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>的床边故事","chinesesinger":0,"docid":"7065911643897223382","f":"107192078|告白气球 (;)|4558|周杰伦|1458791|周杰伦的床边故事|1856582|215|1|1|0|8608939|3443771|2116000|0|24929083|24971563|5001304|5191087|0|003OUlho2HcRHC|0025NhlN2yWrP4|003RMaRI1iFoYd|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"告白气球","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"u00222le4ox","nt":10000,"only":1,"pubTime":1466697600,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"告白气球","t":1,"tag":0,"ver":0},{"albumName_hilight":"魔杰座","chinesesinger":0,"docid":"2257006635702513351","f":"449205|稻香 (;)|4558|周杰伦|36062|魔杰座|1813383|223|9|1|0|8929849|3581117|320000|0|25245000|26093502|5156630|5354369|0|003aAYrm3GE0Ac|0025NhlN2yWrP4|002Neh8l0uciQZ|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"稻香","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"l0013kgn1be","nt":10006,"only":1,"pubTime":1224000000,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"稻香","t":1,"tag":0,"ver":0},{"albumName_hilight":"我很忙","chinesesinger":0,"docid":"4953153690890266244","f":"410316|青花瓷 (;)|4558|周杰伦|33021|我很忙|1942555|239|8|1|0|9573872|3836490|320000|0|25541938|26237796|5414428|5617369|0|002qU5aY3Qu24y|0025NhlN2yWrP4|002eFUFm2XYZ7z|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"青花瓷","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"l00131om505","nt":10003,"only":1,"pubTime":1193932800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"青花瓷","t":1,"tag":0,"ver":0},{"albumName_hilight":"叶惠美","chinesesinger":0,"docid":"4660176279196411288","f":"97773|晴天 (;)|4558|周杰伦|8220|叶惠美|2186317|269|9|1|0|10793267|4319991|320000|0|30143423|31518872|5871273|6308305|0|0039MnYb0qxYhV|0025NhlN2yWrP4|000MkMni19ClKG|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"晴天","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"z0014q87sal","nt":10005,"only":1,"pubTime":1059580800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"晴天","t":1,"tag":0,"ver":0}],"totalnum":388}
         * totaltime : 8.3E-5
         * zhida : {"chinesesinger":0,"type":0}
         */

        private String keyword;
        private int priority;
        private SemanticBean semantic;
        private SongBean song;
        private double totaltime;
        private ZhidaBean zhida;
        private List<?> qc;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public SemanticBean getSemantic() {
            return semantic;
        }

        public void setSemantic(SemanticBean semantic) {
            this.semantic = semantic;
        }

        public SongBean getSong() {
            return song;
        }

        public void setSong(SongBean song) {
            this.song = song;
        }

        public double getTotaltime() {
            return totaltime;
        }

        public void setTotaltime(double totaltime) {
            this.totaltime = totaltime;
        }

        public ZhidaBean getZhida() {
            return zhida;
        }

        public void setZhida(ZhidaBean zhida) {
            this.zhida = zhida;
        }

        public List<?> getQc() {
            return qc;
        }

        public void setQc(List<?> qc) {
            this.qc = qc;
        }

        public static class SemanticBean {
            /**
             * curnum : 0
             * curpage : 1
             * list : []
             * totalnum : 0
             */

            private int curnum;
            private int curpage;
            private int totalnum;
            private List<?> list;

            public int getCurnum() {
                return curnum;
            }

            public void setCurnum(int curnum) {
                this.curnum = curnum;
            }

            public int getCurpage() {
                return curpage;
            }

            public void setCurpage(int curpage) {
                this.curpage = curpage;
            }

            public int getTotalnum() {
                return totalnum;
            }

            public void setTotalnum(int totalnum) {
                this.totalnum = totalnum;
            }

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }
        }

        public static class SongBean {
            /**
             * curnum : 5
             * curpage : 1
             * list : [{"albumName_hilight":"等你下课","chinesesinger":0,"docid":"11127252070543836748","f":"212877900|等你下课(with 杨瑞代) (;)|4558|周杰伦|3883404|等你下课|0|270|4|1|0|10802353|4321060|0|0|31780875|31922907|6045129|6521861|0|001J5QJL1pRQYB|0025NhlN2yWrP4|003bSL0v4bpKAx|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"等你下课(with 杨瑞代)","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"","nt":10015,"only":1,"pubTime":1516204800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"等你下课(with 杨瑞代)","t":1,"tag":0,"ver":0},{"albumName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>的床边故事","chinesesinger":0,"docid":"7065911643897223382","f":"107192078|告白气球 (;)|4558|周杰伦|1458791|周杰伦的床边故事|1856582|215|1|1|0|8608939|3443771|2116000|0|24929083|24971563|5001304|5191087|0|003OUlho2HcRHC|0025NhlN2yWrP4|003RMaRI1iFoYd|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"告白气球","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"u00222le4ox","nt":10000,"only":1,"pubTime":1466697600,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"告白气球","t":1,"tag":0,"ver":0},{"albumName_hilight":"魔杰座","chinesesinger":0,"docid":"2257006635702513351","f":"449205|稻香 (;)|4558|周杰伦|36062|魔杰座|1813383|223|9|1|0|8929849|3581117|320000|0|25245000|26093502|5156630|5354369|0|003aAYrm3GE0Ac|0025NhlN2yWrP4|002Neh8l0uciQZ|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"稻香","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"l0013kgn1be","nt":10006,"only":1,"pubTime":1224000000,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"稻香","t":1,"tag":0,"ver":0},{"albumName_hilight":"我很忙","chinesesinger":0,"docid":"4953153690890266244","f":"410316|青花瓷 (;)|4558|周杰伦|33021|我很忙|1942555|239|8|1|0|9573872|3836490|320000|0|25541938|26237796|5414428|5617369|0|002qU5aY3Qu24y|0025NhlN2yWrP4|002eFUFm2XYZ7z|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"青花瓷","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"l00131om505","nt":10003,"only":1,"pubTime":1193932800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"青花瓷","t":1,"tag":0,"ver":0},{"albumName_hilight":"叶惠美","chinesesinger":0,"docid":"4660176279196411288","f":"97773|晴天 (;)|4558|周杰伦|8220|叶惠美|2186317|269|9|1|0|10793267|4319991|320000|0|30143423|31518872|5871273|6308305|0|0039MnYb0qxYhV|0025NhlN2yWrP4|000MkMni19ClKG|0|4009","fiurl":"","fnote":2009,"fsinger":"周杰伦","fsinger2":"","fsong":"晴天","grp":[],"isupload":0,"isweiyun":0,"lyric":"","lyric_hilight":"","mv":"z0014q87sal","nt":10005,"only":1,"pubTime":1059580800,"pure":0,"singerMID":"0025NhlN2yWrP4","singerMID2":"","singerName2_hilight":"","singerName_hilight":"<span class=\"c_tx_highlight\">周杰伦<\/span>","singerid":4558,"singerid2":0,"songName_hilight":"晴天","t":1,"tag":0,"ver":0}]
             * totalnum : 388
             */

            private int curnum;
            private int curpage;
            private int totalnum;
            private List<ListBean> list;

            public int getCurnum() {
                return curnum;
            }

            public void setCurnum(int curnum) {
                this.curnum = curnum;
            }

            public int getCurpage() {
                return curpage;
            }

            public void setCurpage(int curpage) {
                this.curpage = curpage;
            }

            public int getTotalnum() {
                return totalnum;
            }

            public void setTotalnum(int totalnum) {
                this.totalnum = totalnum;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements MultiItemEntity{
                /**
                 * albumName_hilight : 等你下课
                 * chinesesinger : 0
                 * docid : 11127252070543836748
                 * f : 212877900|等你下课(with 杨瑞代) (;)|4558|周杰伦|3883404|等你下课|0|270|4|1|0|10802353|4321060|0|0|31780875|31922907|6045129|6521861|0|001J5QJL1pRQYB|0025NhlN2yWrP4|003bSL0v4bpKAx|0|4009
                 //// String[25]  id: str[20] img: str[22] lrc:str[0]


                 * fiurl :
                 * fnote : 2009
                 * fsinger : 周杰伦
                 * fsinger2 :
                 * fsong : 等你下课(with 杨瑞代)
                 * grp : []
                 * isupload : 0
                 * isweiyun : 0
                 * lyric :
                 * lyric_hilight :
                 * mv :
                 * nt : 10015
                 * only : 1
                 * pubTime : 1516204800
                 * pure : 0
                 * singerMID : 0025NhlN2yWrP4
                 * singerMID2 :
                 * singerName2_hilight :
                 * singerName_hilight : <span class="c_tx_highlight">周杰伦</span>
                 * singerid : 4558
                 * singerid2 : 0
                 * songName_hilight : 等你下课(with 杨瑞代)
                 * t : 1
                 * tag : 0
                 * ver : 0
                 */

                private String albumName_hilight;
                private int chinesesinger;
                private String docid;
                private String f;
                private String playUrl;

                public String getPlayUrl() {
                    return playUrl;
                }

                public void setPlayUrl(String playUrl) {
                    this.playUrl = playUrl;
                }

                private String fiurl;
                private int fnote;
                private String fsinger;
                private String fsinger2;
                private String fsong;
                private int isupload;
                private int isweiyun;
                private String lyric;
                private String lyric_hilight;
                private String mv;
                private int nt;
                private int only;
                private int pubTime;
                private int pure;
                private String singerMID;
                private String singerMID2;
                private String singerName2_hilight;
                private String singerName_hilight;
                private int singerid;
                private int singerid2;
                private String songName_hilight;
                private int t;
                private int tag;
                private int ver;
                private List<?> grp;

                public String getAlbumName_hilight() {
                    return albumName_hilight;
                }

                public void setAlbumName_hilight(String albumName_hilight) {
                    this.albumName_hilight = albumName_hilight;
                }

                public int getChinesesinger() {
                    return chinesesinger;
                }

                public void setChinesesinger(int chinesesinger) {
                    this.chinesesinger = chinesesinger;
                }

                public String getDocid() {
                    return docid;
                }

                public void setDocid(String docid) {
                    this.docid = docid;
                }

                public String getF() {
                    return f;
                }

                public void setF(String f) {
                    this.f = f;
                }

                public String getFiurl() {
                    return fiurl;
                }

                public void setFiurl(String fiurl) {
                    this.fiurl = fiurl;
                }

                public int getFnote() {
                    return fnote;
                }

                public void setFnote(int fnote) {
                    this.fnote = fnote;
                }

                public String getFsinger() {
                    return fsinger;
                }

                public void setFsinger(String fsinger) {
                    this.fsinger = fsinger;
                }

                public String getFsinger2() {
                    return fsinger2;
                }

                public void setFsinger2(String fsinger2) {
                    this.fsinger2 = fsinger2;
                }

                public String getFsong() {
                    return fsong;
                }

                public void setFsong(String fsong) {
                    this.fsong = fsong;
                }

                public int getIsupload() {
                    return isupload;
                }

                public void setIsupload(int isupload) {
                    this.isupload = isupload;
                }

                public int getIsweiyun() {
                    return isweiyun;
                }

                public void setIsweiyun(int isweiyun) {
                    this.isweiyun = isweiyun;
                }

                public String getLyric() {
                    return lyric;
                }

                public void setLyric(String lyric) {
                    this.lyric = lyric;
                }

                public String getLyric_hilight() {
                    return lyric_hilight;
                }

                public void setLyric_hilight(String lyric_hilight) {
                    this.lyric_hilight = lyric_hilight;
                }

                public String getMv() {
                    return mv;
                }

                public void setMv(String mv) {
                    this.mv = mv;
                }

                public int getNt() {
                    return nt;
                }

                public void setNt(int nt) {
                    this.nt = nt;
                }

                public int getOnly() {
                    return only;
                }

                public void setOnly(int only) {
                    this.only = only;
                }

                public int getPubTime() {
                    return pubTime;
                }

                public void setPubTime(int pubTime) {
                    this.pubTime = pubTime;
                }

                public int getPure() {
                    return pure;
                }

                public void setPure(int pure) {
                    this.pure = pure;
                }

                public String getSingerMID() {
                    return singerMID;
                }

                public void setSingerMID(String singerMID) {
                    this.singerMID = singerMID;
                }

                public String getSingerMID2() {
                    return singerMID2;
                }

                public void setSingerMID2(String singerMID2) {
                    this.singerMID2 = singerMID2;
                }

                public String getSingerName2_hilight() {
                    return singerName2_hilight;
                }

                public void setSingerName2_hilight(String singerName2_hilight) {
                    this.singerName2_hilight = singerName2_hilight;
                }

                public String getSingerName_hilight() {
                    return singerName_hilight;
                }

                public void setSingerName_hilight(String singerName_hilight) {
                    this.singerName_hilight = singerName_hilight;
                }

                public int getSingerid() {
                    return singerid;
                }

                public void setSingerid(int singerid) {
                    this.singerid = singerid;
                }

                public int getSingerid2() {
                    return singerid2;
                }

                public void setSingerid2(int singerid2) {
                    this.singerid2 = singerid2;
                }

                public String getSongName_hilight() {
                    return songName_hilight;
                }

                public void setSongName_hilight(String songName_hilight) {
                    this.songName_hilight = songName_hilight;
                }

                public int getT() {
                    return t;
                }

                public void setT(int t) {
                    this.t = t;
                }

                public int getTag() {
                    return tag;
                }

                public void setTag(int tag) {
                    this.tag = tag;
                }

                public int getVer() {
                    return ver;
                }

                public void setVer(int ver) {
                    this.ver = ver;
                }

                public List<?> getGrp() {
                    return grp;
                }

                public void setGrp(List<?> grp) {
                    this.grp = grp;
                }

                @Override
                public int getItemType() {
                    return 2;
                }
            }
        }

        public static class ZhidaBean {
            /**
             * chinesesinger : 0
             * type : 0
             */

            private int chinesesinger;
            private int type;

            public int getChinesesinger() {
                return chinesesinger;
            }

            public void setChinesesinger(int chinesesinger) {
                this.chinesesinger = chinesesinger;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
