package com.google.spreadsheet.facebook.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="evens")
public class Even {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long id_post;

    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer video_play=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer other_click=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer link_click=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer view=0;

    private Integer type_page;
    private String spreadsheet;
}
