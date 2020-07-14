package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;

@Data
public class ConsumptionsDTO extends DTOEven{
    public ConsumptionsDTO(String pageName){
        this.SHEET_INFOR.SHEET_NAME= "Lifetime Post Consumptions b...";
        this.SHEET_INFOR.SHEET_TYPE= 5;
        this.SHEET_INFOR.FIELD_NAME= pageName;
    }
}
