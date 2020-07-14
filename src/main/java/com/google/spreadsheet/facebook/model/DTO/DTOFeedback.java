package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class DTOFeedback {
    private int id=-1;
    private int hide_all=-1;
    private int hide=-1;
    private int unlike_page=-1;
    private int report_spam=-1;

    public  DTOInfor SHEET_INFOR= new DTOInfor();

    public static final String RANGE = "!A2:N";
    public static final String TITLE= "!A1:N1";

    public static final String COLUM_ID= "ID bài viết";
    public static final String HIDE_ALL= "hide_all_clicks";
    public static final String HIDE= "hide_clicks";
    public static final String UNLIKE_PAGE= "unlike_page_clicks";
    public static final String REPORT_SPAM= "report_spam_clicks";
}
