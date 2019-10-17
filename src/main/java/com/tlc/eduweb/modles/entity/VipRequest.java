package com.tlc.eduweb.modles.entity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "viprequest")
@EntityListeners(AuditingEntityListener.class)
public class VipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "openid", nullable = false)
    private String openid;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "detial", nullable = false)
    private String detial;

    @Column(name = "statu", nullable = false)
    private String statu;

    @Column(name = "createtime", nullable = false)
    private String createtime;
    @Column(name = "finishtime")
    private String finishtime;

    @Column(name = "headimgurl")
    private String headimgurl;
    @Column(name = "theclass")
    private String theclass;
    @Column(name = "adminmessage")
    private String adminmessage;
    @Column(name = "adminname")
    private String adminname;
    public VipRequest(){}

    public VipRequest(String openid, String nickname, String detial, String statu, String createtime, String finishtime, String headimgurl, String theclass, String adminmessage, String adminname) {
        this.openid = openid;
        this.nickname = nickname;
        this.detial = detial;
        this.statu = statu;
        this.createtime = createtime;
        this.finishtime = finishtime;
        this.headimgurl = headimgurl;
        this.theclass = theclass;
        this.adminmessage = adminmessage;
        this.adminname = adminname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getTheclass() {
        return theclass;
    }

    public void setTheclass(String theclass) {
        this.theclass = theclass;
    }

    public String getAdminmessage() {
        return adminmessage;
    }

    public void setAdminmessage(String adminmessage) {
        this.adminmessage = adminmessage;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
}
