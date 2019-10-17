package com.tlc.eduweb.modles.entity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "theclass")
@EntityListeners(AuditingEntityListener.class)
public class TheClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "value", nullable = false)
    private String value;
    @Column(name = "detial", nullable = false)
    private String detial;
    @Column(name = "school", nullable = false)
    private String school;

    public TheClass() {
    }

    public TheClass(int id,String label, String value, String detial, String school) {
        this.id =id;
        this.label = label;
        this.value = value;
        this.detial = detial;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

