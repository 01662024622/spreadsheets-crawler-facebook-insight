package com.google.spreadsheet.facebook.util.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.DTO.*;
import com.google.spreadsheet.facebook.model.Even;
import com.google.spreadsheet.facebook.model.Feedback;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.sender.SendMailContent;
import com.google.spreadsheet.facebook.util.validate.ValidateDatatypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FeedbackUtil {
    public static List<Feedback> getAllDataFromFile(Sheets sheetsService,
                                                    String fieldId, String fileName, DTOFeedback dto) throws QTTTException {

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
            List<Feedback> feedbackList = new ArrayList<>();
            for (List row:values) {
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    return null;
                }
                Feedback feedback = getValue(dto,row);
                feedback.setSpreadsheet(fieldId);
                feedback.setType_page(dto.SHEET_INFOR.SHEET_TYPE);
                feedbackList.add(feedback);
            }
            return feedbackList;
        }
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static DTOFeedback convertRowToTitleFileStructor(List row,DTOFeedback dto,SendEmailService sendEmailService) {
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            switch (cellValue) {
                case "":
                    break;
                case DTOFeedback.COLUM_ID:
                    dto.setId(i);
                    break;
                case DTOFeedback.HIDE:
                    dto.setHide(i);
                    break;
                case DTOFeedback.HIDE_ALL:
                    dto.setHide_all(i);
                    break;
                case DTOFeedback.REPORT_SPAM:
                    dto.setReport_spam(i);
                    break;
                case DTOFeedback.UNLIKE_PAGE:
                    dto.setUnlike_page(i);
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
    private static Feedback getValue(DTOFeedback dto,List row) {
        Feedback feedback = new Feedback();
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            if (cellValue==null||cellValue.equals("")||cellValue.equals(" ")){
                continue;
            }
            if (i == dto.getId()) {
                String[] arr=cellValue.split("_",2);
                feedback.setId_post(Long.parseLong(arr[1]));
                continue;
            }
            if (i == dto.getHide()) {
                feedback.setHide(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dto.getHide_all()) {
                feedback.setHide_all(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dto.getReport_spam()) {
                feedback.setReport_spam(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dto.getUnlike_page()) {
                feedback.setUnlike_page(Integer.valueOf(cellValue));
                continue;
            }
        }
        return feedback;
    }

    public static List<DTOFeedback> getTitleFromFileImport(FileImported fileImported, Sheets sheetservice, SendEmailService sendEmailService) throws QTTTException {
        List<DTOFeedback> dtos = new ArrayList<>();
        dtos.add(new NegativeDTO(fileImported.getFileName()));
        dtos.add(new NegativeUniqueDTO(fileImported.getFileName()));
        List<DTOFeedback> dtoFeedbackRes = new ArrayList<>();
        for (DTOFeedback dto:dtos) {
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
                DTOFeedback dtores = convertRowToTitleFileStructor(row,dto,sendEmailService);
                dtoFeedbackRes.add(dtores);
            }
        }
        return dtoFeedbackRes;
    }
}
