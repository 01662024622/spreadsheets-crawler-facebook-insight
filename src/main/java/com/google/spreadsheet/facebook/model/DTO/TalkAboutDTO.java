package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class TalkAboutDTO extends DTOAction{
    public TalkAboutDTO(String pageName){
        this.SHEET_INFOR.SHEET_NAME= "Lifetime Talking About This(...";
        this.SHEET_INFOR.SHEET_TYPE= 2;
        this.SHEET_INFOR.FIELD_NAME= pageName;
    }


}
