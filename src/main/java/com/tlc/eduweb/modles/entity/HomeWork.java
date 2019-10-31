package com.tlc.eduweb.modles.entity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "homework")
@EntityListeners(AuditingEntityListener.class)
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "detial", nullable = false)
    private String detial;
    @Column(name = "starttime", nullable = false)
    private Date starttime;
    @Column(name = "finishtime", nullable = false)
    private Date finishtime;
    @Column(name = "createtime", nullable = false)
    private String createtime;
    @Column(name = "theclass", nullable = false)
    private String theclass;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "adminname", nullable = false)
    private String adminname;
    @Column(name = "pictures")
    private String pictures;
    @Column(name = "video")
    private String video;
    public HomeWork(){}

    public HomeWork(String detial, Date starttime, Date finishtime, String createtime, String theclass, String name, String adminname, String pictures, String video) {
        this.detial = detial;
        this.starttime = starttime;
        this.finishtime = finishtime;
        this.createtime = createtime;
        this.theclass = theclass;
        this.name = name;
        this.adminname = adminname;
        this.pictures = pictures;
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTheclass() {
        return theclass;
    }

    public void setTheclass(String theclass) {
        this.theclass = theclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
