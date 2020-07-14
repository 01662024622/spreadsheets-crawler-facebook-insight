package com.google.spreadsheet.facebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fileimported")
public class FileImported {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileId;

    @Column
    private String fileName;

    @Column
    private String driveId;

    @Column
    private String createdTime;

    @Column
    private String modifiedTime;

}
