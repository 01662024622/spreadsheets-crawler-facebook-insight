package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class DTOEven {
    private int id=-1;
    private int video_play=-1;
    private int other_click=-1;
    private int link_click=-1;
    private int view=-1;

    public  DTOInfor SHEET_INFOR= new DTOInfor();

    public static final String RANGE = "!A2:N";
    public static final String TITLE= "!A1:N1";

    public static final String COLUM_ID= "ID bài viết";
    public static final String VIDEO_PLAY= "video play";
    public static final String OTHER_CLICK= "other clicks";
    public static final String LINK_CLICK= "link clicks";
    public static final String VIEW= "view";
}
