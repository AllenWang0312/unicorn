package edu.tjrac.swant.qqmusic.qqmucsic;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 上午 9:20
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class QQMusicKey {


    /**
     * code : 0
     * sip : ["http://ws.stream.qqmusic.qq.com/","http://cc.stream.qqmusic.qq.com/","http://124.14.15.19/streamoc.music.tc.qq.com/"]
     * key : 6BFDD0DFE8A88C65E5D7942967AE84A1F7BC2A96A9120C15A5032483EA5D0659
     */

    private int code;
    private String key;
    private List<String> sip;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getSip() {
        return sip;
    }

    public void setSip(List<String> sip) {
        this.sip = sip;
    }
}
