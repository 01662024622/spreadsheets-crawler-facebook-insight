package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class StoriesDTO extends DTOAction{
    public StoriesDTO(String pageName){
        this.SHEET_INFOR.SHEET_NAME="Lifetime Post Stories by act...";
        this.SHEET_INFOR.SHEET_TYPE= 3;
        this.SHEET_INFOR.FIELD_NAME= pageName;
    }

}
