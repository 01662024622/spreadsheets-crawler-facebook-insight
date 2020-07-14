package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class DTOAction {

    private int id=-1;
    private int like=-1;
    private int share=-1;
    private int comment=-1;
    private int answer=-1;

    public  DTOInfor SHEET_INFOR= new DTOInfor();
    public static final String RANGE = "!A2:N";
    public static final String TITLE= "!A1:N1";

    public static final String COLUM_ID= "ID bài viết";
    public static final String SHARE= "share";
    public static final String COMMENT= "comment";
    public static final String LIKE= "like";
    public static final String ANSWER= "answer";
}
