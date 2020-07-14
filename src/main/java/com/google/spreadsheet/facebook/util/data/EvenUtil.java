package com.google.spreadsheet.facebook.util.data;


import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.Action;
import com.google.spreadsheet.facebook.model.DTO.*;
import com.google.spreadsheet.facebook.model.Even;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.sender.SendMailContent;
import com.google.spreadsheet.facebook.util.validate.ValidateDatatypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EvenUtil {
    public static List<Even> getAllDataFromFile(Sheets sheetsService,
                                                  String fieldId, String fileName, DTOEven dto) throws QTTTException {

        String range = dto.SHEET_INFOR.SHEET_NAME + dto.RANGE;
        ValueRange response;
        try {
            response = sheetsService.spreadsheets().values().get(fieldId, range)
                    .execute();
        } catch (Exception e) {
            throw new QTTTException("File: " + fieldId + " can't find data in sheet name data");
        }
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            log.info("Post: File " + dto.SHEET_INFOR.SHEET_NAME + " No data found.");
            return null;
        } else {
            List<Even> evenList = new ArrayList<>();
            for (List row:values) {
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    return null;
                }
                Even even = getValue(dto,row);
                even.setSpreadsheet(fieldId);
                even.setType_page(dto.SHEET_INFOR.SHEET_TYPE);
                evenList.add(even);
            }
            return evenList;
        }
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static DTOEven convertRowToTitleFileStructor(List row, DTOEven dto, SendEmailService sendEmailService) {
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            switch (cellValue) {
                case "":
                    break;
                case DTOEven.COLUM_ID:
                    dto.setId(i);
                    break;
                case DTOEven.LINK_CLICK:
                    dto.setLink_click(i);
                    break;
                case DTOEven.OTHER_CLICK:
                    dto.setOther_click(i);
                    break;
                case DTOEven.VIDEO_PLAY:
                    dto.setVideo_play(i);
                    break;
                case DTOEven.VIEW:
                    dto.setView(i);
                    break;
                default:
//                    sendEmailService.sendSimpleMessage(SendMailContent.getErrorField("post",dto.SHEET_INFOR.FIELD_NAME,dto.SHEET_INFOR.SHEET_NAME,cellValue));
                    break;
            }
        }
        return dto;
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static Even getValue(DTOEven dto,List row) {
        Even even = new Even();
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            if (cellValue==null||cellValue.equals("")||cellValue.equals(" ")){
                continue;
            }
            if (i == dto.getId()) {
                String[] arr=cellValue.split("_",2);
                even.setId_post(Long.parseLong(arr[1]));
                continue;
            }
            if (i == dto.getLink_click()) {
                even.setLink_click(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dto.getOther_click()) {
                even.setOther_click(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dto.getVideo_play()) {
                even.setVideo_play(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dto.getView()) {
                even.setView(Integer.valueOf(cellValue));
                continue;
            }
        }
        return even;
    }

    public static List<DTOEven> getTitleFromFileImport(FileImported fileImported, Sheets sheetservice,SendEmailService sendEmailService) throws QTTTException {
        List<DTOEven> dtos = new ArrayList<>();
        dtos.add(new ConsumersDTO(fileImported.getFileName()));
        dtos.add(new ConsumptionsDTO(fileImported.getFileName()));
        List<DTOEven> dtoEvenRes = new ArrayList<>();
        for (DTOEven dto:dtos) {
            String range = dto.SHEET_INFOR.SHEET_NAME + dto.TITLE;
            ValueRange response_title;
            try {
                response_title = sheetservice.spreadsheets().values().get(fileImported.getFileId(), range)
                        .execute();
            } catch (Exception e) {
                throw new QTTTException("File: " + fileImported.getFileName() + dto.SHEET_INFOR.SHEET_NAME + " can't find data in sheet name data");
            }

            List<List<Object>> values = response_title.getValues();
            if (values == null || values.isEmpty()) {
                log.info("Post: File " + dto.SHEET_INFOR.SHEET_NAME + " No data found.");
            } else {
                List row = values.get(0) ;
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    continue;
                }
                DTOEven dtores = convertRowToTitleFileStructor(row,dto,sendEmailService);
                dtoEvenRes.add(dtores);
            }
        }
        return dtoEvenRes;
    }
}
