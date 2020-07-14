package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class NegativeDTO extends DTOFeedback {
    public NegativeDTO(String pageName){
        this.SHEET_INFOR.SHEET_NAME= "Lifetime Negative Feedback f...";
        this.SHEET_INFOR.SHEET_TYPE= 6;
        this.SHEET_INFOR.FIELD_NAME= pageName;
    }

}
