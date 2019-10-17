package com.tlc.eduweb.modles.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "openid", nullable = false)
    private String openid;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "headimgurl", nullable = false)
    private String headimgurl;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "sex", nullable = false)
    private int sex;
    @Column(name = "theclass")
    private String theclass;

    public User(String theclass,String openid, String nickname, String remarks, String city, String country, String headimgurl, String language, String province, int sex) {
        this.openid = openid;
        this.nickname = nickname;
        this.remarks = remarks;
        this.city = city;
        this.country = country;
        this.headimgurl = headimgurl;
        this.language = language;
        this.province = province;
        this.sex = sex;
        this.theclass=theclass;
    }
    public User(){}

    public String getTheclass() {
        return theclass;
    }

    public void setTheclass(String theclass) {
        this.theclass = theclass;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", remarks='" + remarks + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", language='" + language + '\'' +
                ", province='" + province + '\'' +
                ", sex=" + sex +
                '}';
    }
}
