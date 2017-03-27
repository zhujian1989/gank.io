package com.jzhu.io.data.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by zhujian on 2017/3/16.
 "_id": "58d24805421aa90f033451c1",
 "createdAt": "2017-03-22T17:46:45.109Z",
 "desc": "\u4e00\u4efd\u5c0f\u7ec4\u534f\u540c\u5f00\u53d1\u53ef\u4ee5\u4f7f\u7528\u7684Android\u4ee3\u7801\u89c4\u8303",
 "images": [
 "http://img.gank.io/15e2d305-1742-4129-8dce-146666494993"
 ],
 "publishedAt": "2017-03-24T12:12:34.753Z",
 "source": "web",
 "type": "Android",
 "url": "https://github.com/LoranWong/Android-Code-Style/blob/master/README.md",
 "used": true,
 "who": "\u9ec4\u7070\u7ea2"
 */

public class GankAndroidAndiOSEntities {

    private Long _id ;
    private Date createAt ;
    private String desc;
    private List<String> images;
    private Date publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean use;
    private String who;

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
