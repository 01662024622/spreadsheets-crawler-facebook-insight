package com.google.spreadsheet.facebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="actions")
public class Action {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long id_post;

    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer likes=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer shares=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer comments=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer answers=0;

    private Integer type_page;
    private String spreadsheet;
}
