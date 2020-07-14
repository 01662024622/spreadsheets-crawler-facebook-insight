package com.google.spreadsheet.facebook.util.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.Action;
import com.google.spreadsheet.facebook.model.DTO.DTOAction;
import com.google.spreadsheet.facebook.model.DTO.StoriesDTO;
import com.google.spreadsheet.facebook.model.DTO.TalkAboutDTO;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.sender.SendMailContent;
import com.google.spreadsheet.facebook.util.validate.ValidateDatatypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ActionUtil {
    public static List<Action> getAllDataFromFile(Sheets sheetsService,
                                                  String fieldId, String fileName, DTOAction actionDTO) throws QTTTException {

        String range = actionDTO.SHEET_INFOR.SHEET_NAME + actionDTO.RANGE;
        ValueRange response;
        try {
            response = sheetsService.spreadsheets().values().get(fieldId, range).execute();
        } catch (Exception e) {
            throw new QTTTException("File: " + fieldId + " can't find data in sheet name data" +range);
        }
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            log.info("Post: File " + actionDTO.SHEET_INFOR.SHEET_NAME + " No data found.");
            return null;
        } else {
            List<Action> actionList = new ArrayList<>();
            for (List row:values) {
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    return null;
                }
                Action action = getValue(actionDTO,row);
                action.setSpreadsheet(fieldId);
                action.setType_page(actionDTO.SHEET_INFOR.SHEET_TYPE);
                actionList.add(action);
            }
            return actionList;
        }
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static DTOAction convertRowToTitleFileStructor(List row,DTOAction dto, SendEmailService sendEmailService) {
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            switch (cellValue) {
                case "":
                    break;
                case DTOAction.COLUM_ID:
                    dto.setId(i);
                    break;
                case DTOAction.ANSWER:
                    dto.setAnswer(i);
                    break;
                case DTOAction.COMMENT:
                    dto.setComment(i);
                    break;
                case DTOAction.LIKE:
                    dto.setLike(i);
                    break;
                case DTOAction.SHARE:
                    dto.setShare(i);
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
    private static Action getValue(DTOAction dtoAction,List row) {
        Action action = new Action();
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            if (cellValue==null||cellValue.equals("")||cellValue.equals(" ")){
                continue;
            }
            if (i == dtoAction.getId()) {
                String[] arr=cellValue.split("_",2);
                action.setId_post(Long.parseLong(arr[1]));
                continue;
            }
            if (i == dtoAction.getAnswer()) {
                action.setAnswers(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dtoAction.getComment()) {
                action.setComments(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dtoAction.getLike()) {
                action.setLikes(Integer.valueOf(cellValue));
                continue;
            }
            if (i == dtoAction.getShare()) {
                action.setShares(Integer.valueOf(cellValue));
                continue;
            }
        }
        log.info(action.toString());
        return action;
    }

    public static List<DTOAction> getTitleFromFileImport(FileImported fileImported, Sheets sheetservice, SendEmailService sendEmailService) throws QTTTException {
        List<DTOAction> dtoActions = new ArrayList<>();
        dtoActions.add(new StoriesDTO(fileImported.getFileName()));
        dtoActions.add(new TalkAboutDTO(fileImported.getFileName()));
        List<DTOAction> dtoActionsRes = new ArrayList<>();
        for (DTOAction dtoAction:dtoActions) {
            String range = dtoAction.SHEET_INFOR.SHEET_NAME + dtoAction.TITLE;
            ValueRange response_title;
            try {
                response_title = sheetservice.spreadsheets().values().get(fileImported.getFileId(), range)
                        .execute();
            } catch (Exception e) {
                throw new QTTTException("File: " + fileImported.getFileName() + dtoAction.SHEET_INFOR.SHEET_NAME + " can't find data in sheet name data");
            }

            List<List<Object>> values = response_title.getValues();
            if (values == null || values.isEmpty()) {
                log.info("Post: File " + dtoAction.SHEET_INFOR.SHEET_NAME + " No data found.");
            } else {
                List row = values.get(0) ;
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    continue;
                }
                DTOAction dto = convertRowToTitleFileStructor(row,dtoAction,sendEmailService);
                dtoActionsRes.add(dto);
            }
        }
        return dtoActionsRes;
    }
}
