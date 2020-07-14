package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class NegativeUniqueDTO extends DTOFeedback {
    public NegativeUniqueDTO(String pageName){
        this.SHEET_INFOR.SHEET_NAME= "Lifetime Negative Feedback";
        this.SHEET_INFOR.SHEET_TYPE= 7;
        this.SHEET_INFOR.FIELD_NAME= pageName;
    }

}
