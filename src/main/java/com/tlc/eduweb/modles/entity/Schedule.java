package com.tlc.eduweb.modles.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 32, nullable = false)
    private String name;
    @Column(length = 32)
    private String type;

    @Column
    private Date date;

    @Column(length = 32)
    private String timeStart;
    @Column(length = 32)
    private String timeEnd;

    @Column
    private int classId;
}
