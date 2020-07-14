package com.google.spreadsheet.facebook.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long id_post;

    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer hide_all=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer hide=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer unlike_page=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer report_spam=0;

    private Integer type_page;
    private String spreadsheet;
}
