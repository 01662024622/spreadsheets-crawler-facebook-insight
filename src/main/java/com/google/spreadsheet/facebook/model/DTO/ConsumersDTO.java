package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class ConsumersDTO extends DTOEven{
    public ConsumersDTO(String pageName){
        this.SHEET_INFOR.SHEET_NAME="Lifetime Post Consumers by type";
        this.SHEET_INFOR.SHEET_TYPE=4;
        this.SHEET_INFOR.FIELD_NAME= pageName;
    }

}
