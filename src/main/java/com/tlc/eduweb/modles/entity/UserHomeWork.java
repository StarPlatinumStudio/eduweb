package com.tlc.eduweb.modles.entity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userhomework")
@EntityListeners(AuditingEntityListener.class)
public class UserHomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "homework", nullable = false)
    private int homework;
    @Column(name = "detial", nullable = false)
    private String detial;
    @Column(name = "createtime", nullable = false)
    private String createtime;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "openid", nullable = false)
    private String openid;
    @Column(name = "headimgurl", nullable = false)
    private String headimgurl;
    @Column(name = "theclass", nullable = false)
    private String theclass;
    @Column(name = "comment")
    private String comment;
    @Column(name = "commentadmin")
    private String commentadmin;
    @Column(name = "commenttime")
    private String commenttime;
    @Column(name = "pictures")
    private String pictures;
    @Column(name = "audio")
    private String audio;
    @Column(name = "video")
    private String video;
    @Column(name = "star")
    private int star;
    public UserHomeWork(){}

    public UserHomeWork(String openid,String  headimgurl,int homework, String detial, String createtime, String nickname, String theclass, String comment, String commentadmin, String commenttime, String pictures, String audio, String video, int star) {
       this.openid=openid;
        this.headimgurl = headimgurl;
        this.homework = homework;
        this.detial = detial;
        this.createtime = createtime;
        this.nickname = nickname;
        this.theclass = theclass;
        this.comment = comment;
        this.commentadmin = commentadmin;
        this.commenttime = commenttime;
        this.pictures = pictures;
        this.audio = audio;
        this.video = video;
        this.star = star;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomework() {
        return homework;
    }

    public void setHomework(int homework) {
        this.homework = homework;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTheclass() {
        return theclass;
    }

    public void setTheclass(String theclass) {
        this.theclass = theclass;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentadmin() {
        return commentadmin;
    }

    public void setCommentadmin(String commentadmin) {
        this.commentadmin = commentadmin;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
