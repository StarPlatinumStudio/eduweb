package com.tlc.eduweb.modles.entity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "school")
@EntityListeners(AuditingEntityListener.class)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "value", nullable = false)
    private String value;
    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "detial", nullable = false)
    private String detial;
    @Column(name = "classdata")
    private String classdata;

    public School(String value, String label, String address, String detial, String classdata) {
        this.value = value;
        this.label = label;
        this.address = address;
        this.detial = detial;
        this.classdata = classdata;
    }
    public School(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getClassdata() {
        return classdata;
    }

    public void setClassdata(String classdata) {
        this.classdata = classdata;
    }
}
