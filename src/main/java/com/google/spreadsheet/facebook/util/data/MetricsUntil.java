package com.google.spreadsheet.facebook.util.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.DTO.MetricsDTO;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.model.Metrics;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.sender.SendMailContent;
import com.google.spreadsheet.facebook.util.validate.ValidateDatatypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MetricsUntil {


    public static List<Metrics> getAllDataFromFile(Sheets sheetsService,
                                                   FileImported fileImported, SendEmailService sendEmailService) throws QTTTException {

        String range = MetricsDTO.SHEET_NAME + MetricsDTO.RANGE;
        ValueRange response;
        try {
            response = sheetsService.spreadsheets().values().get(fileImported.getFileId(), range)
                    .execute();
        } catch (Exception e) {
            throw new QTTTException("File: " + fileImported.getFileName() + " can't find data in sheet name data");
        }
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            log.info("Post: File " + MetricsDTO.SHEET_NAME + " No data found.");
            return null;
        } else {
            MetricsDTO metricsDTO = getTitleFromFileImport(fileImported,sheetsService,sendEmailService);
            List<Metrics> metricsList = new ArrayList<>();
            String[] arr = fileImported.getFileName().split("_");
            for (List row:values) {
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    return null;
                }
                Metrics metrics = getValue(metricsDTO,row);
                metrics.setPage_export_day(Integer.valueOf(arr[0]));
                metrics.setType_sheet(Integer.valueOf(arr[1]));
                metrics.setSpreadsheet(fileImported.getFileId());
                metricsList.add(metrics);

            }
            return metricsList;
        }
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static MetricsDTO convertRowToTitleFileStructor(List row,String fileName,SendEmailService sendEmailService) {
        MetricsDTO dto = new MetricsDTO();
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            switch (cellValue) {
                case "":
                    break;
                case MetricsDTO.COLUM_ID:
                        dto.setId(i);
                    break;
                case MetricsDTO.LINK:
                    dto.setLink(i);
                    break;
                case MetricsDTO.CONTENT:
                    dto.setContent(i);
                    break;
                case MetricsDTO.TYPE:
                    dto.setType(i);
                    break;
                case MetricsDTO.CREATED_AT:
                    dto.setCreated_at(i);
                    break;
                case MetricsDTO.POST_TOTAL_REACH:
                    dto.setPost_total_reach(i);
                    break;
                case MetricsDTO.POST_ORGANIC_REACH:
                    dto.setPost_organic_reach(i);
                    break;
                case MetricsDTO.POST_PAID_REACH:
                    dto.setPost_paid_reach(i);
                    break;
                case MetricsDTO.POST_TOTAL_IMPRESSIONS:
                    dto.setPost_total_impressions(i);
                    break;
                case MetricsDTO.POST_ORGANIC_IMPRESSIONS:
                    dto.setPost_organic_impressions(i);
                    break;
                case MetricsDTO.POST_PAID_IMPRESSIONS:
                    dto.setPost_paid_impressions(i);
                    break;case MetricsDTO.ENGAGED_USERS:
                    dto.setEngaged_users(i);
                    break;
                case MetricsDTO.MATCHED_AUDIENCE_TARGETING_CONSUMERS_ON_POST:
                    dto.setMatched_audience_targeting_consumers_on_post(i);
                    break;
                case MetricsDTO.MATCHED_AUDIENCE_TARGETING_CONSUMPTIONS_ON_POST:
                    dto.setMatched_audience_targeting_consumptions_on_post(i);
                    break;
                case MetricsDTO.NEGATIVE_FEEDBACK_FROM_USERS:
                    dto.setNegative_feedback_from_users(i);
                    break;
                case MetricsDTO.NEGATIVE_FEEDBACK:
                    dto.setNegative_feedback(i);
                    break;
                case MetricsDTO.POST_IMPRESSIONS_BY_PEOPLE_WHO_HAVE_LIKED_YOUR_PAGE:
                    dto.setPost_impressions_by_people_who_have_liked_your_page(i);
                    break;
                case MetricsDTO.POST_REACH_BY_PEOPLE_WHO_LIKE_YOUR_PAGE:
                    dto.setPost_reach_by_people_who_like_your_page(i);
                    break;
                case MetricsDTO.POST_PAID_IMPRESSIONS_BY_PEOPLE_WHO_HAVE_LIKED_YOUR_PAGE:
                    dto.setPost_paid_impressions_by_people_who_have_liked_your_page(i);
                    break;
                case MetricsDTO.PAID_REACH_OF_A_POST_BY_PEOPLE_WHO_LIKE_YOUR_PAGE:
                    dto.setPaid_reach_of_a_post_by_people_who_like_your_page(i);
                    break;
                case MetricsDTO.PEOPLE_WHO_HAVE_LIKED_YOUR_PAGE_AND_ENGAGED_WITH_YOUR_POST:
                    dto.setPeople_who_have_liked_your_page_and_engaged_with_your_post(i);
                    break;
                case MetricsDTO.ORGANIC_VIEWS_TO_95:
                    if (dto.getOrganic_views_to_95()!=-1){
                        dto.setOrganic_views_to_95_total(i);
                    }else {
                        dto.setOrganic_views_to_95(i);
                    }
                    break;
                case MetricsDTO.PAID_VIEWS_TO_95:
                    if (dto.getPaid_views_to_95()!=-1){
                        dto.setPaid_views_to_95_total(i);
                    }else {
                        dto.setPaid_views_to_95(i);
                    }
                    break;
                case MetricsDTO.ORGANIC_VIDEO_VIEWS:
                    if (dto.getOrganic_video_views()!=-1){
                        dto.setOrganic_video_views_total(i);
                    }else {
                        dto.setOrganic_video_views(i);
                    }
                    break;
                case MetricsDTO.PAID_VIDEO_VIEWS:
                    if (dto.getPaid_video_views()!=-1){
                        dto.setPaid_video_views_total(i);
                    }else {
                        dto.setPaid_video_views(i);
                    }
                    break;
                case MetricsDTO.AVERAGE_TIME_VIDEO_VIEWED:
                    dto.setAverage_time_video_viewed(i);
                    break;
                case MetricsDTO.VIDEO_LENGTH:
                    dto.setVideo_length(i);
                    break;
                default:
//                    sendEmailService.sendSimpleMessage(SendMailContent.getErrorField("post",fileName,dto.SHEET_NAME,cellValue));

                    break;
            }
        }
        return dto;
    }


    /**
     * Lay gia tri cua hang trong file
     */
    private static Metrics getValue(MetricsDTO metricsDTO,List row) {
        Metrics metrics = new Metrics();
        for (int i = 0; i < row.size(); i++) {
            String cellValue =  row.get(i).toString();
            if (cellValue==null||cellValue.equals("")||cellValue.equals(" ")){
                continue;
            }
            if (i == metricsDTO.getId()) {
                String[] arr=cellValue.split("_",2);
                metrics.setId(Long.parseLong(arr[1]));
                continue;
            }
            if (i == metricsDTO.getLink()) {
                metrics.setLink(cellValue);
                continue;
            }
            if (i == metricsDTO.getType()) {
                metrics.setType(cellValue);
                continue;
            }
            if (i == metricsDTO.getCreated_at()) {
                metrics.setCreated_date(cellValue);
                Integer date=ValidateDatatypeUtil.validateDateString(cellValue);
                metrics.setCreated_at(date);
                continue;
            }
            if (i == metricsDTO.getPost_organic_reach()) {
                metrics.setPost_organic_reach(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_total_reach()) {
                metrics.setPost_total_reach(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_paid_reach()) {
                metrics.setPost_paid_reach(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_total_impressions()) {
                metrics.setPost_total_impressions(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_organic_impressions()) {
                metrics.setPost_organic_impressions(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_paid_impressions()) {
                metrics.setPost_paid_impressions(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getEngaged_users()) {
                metrics.setEngaged_users(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getMatched_audience_targeting_consumers_on_post()) {
                metrics.setMatched_audience_targeting_consumers_on_post(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getMatched_audience_targeting_consumptions_on_post()) {
                metrics.setMatched_audience_targeting_consumptions_on_post(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getNegative_feedback_from_users()) {
                metrics.setNegative_feedback_from_users(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getNegative_feedback()) {
                metrics.setNegative_feedback(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_impressions_by_people_who_have_liked_your_page()) {
                metrics.setPost_impressions_by_people_who_have_liked_your_page(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_reach_by_people_who_like_your_page()) {
                metrics.setPost_reach_by_people_who_like_your_page(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPost_paid_impressions_by_people_who_have_liked_your_page()) {
                metrics.setPost_paid_impressions_by_people_who_have_liked_your_page(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPaid_reach_of_a_post_by_people_who_like_your_page()) {
                metrics.setPaid_reach_of_a_post_by_people_who_like_your_page(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPeople_who_have_liked_your_page_and_engaged_with_your_post()) {
                metrics.setPeople_who_have_liked_your_page_and_engaged_with_your_post(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getOrganic_views_to_95()) {
                metrics.setOrganic_views_to_95(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getOrganic_views_to_95_total()) {
                metrics.setOrganic_views_to_95_total(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPaid_views_to_95()) {
                metrics.setPaid_views_to_95(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPaid_views_to_95_total()) {
                metrics.setPaid_views_to_95_total(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getOrganic_video_views()) {
                metrics.setOrganic_video_views(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getOrganic_video_views_total()) {
                metrics.setOrganic_video_views_total(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPaid_video_views()) {
                metrics.setPaid_video_views(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getPaid_video_views_total()) {
                metrics.setPaid_video_views_total(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getAverage_time_video_viewed()) {
                metrics.setAverage_time_video_viewed(Integer.valueOf(cellValue));
                continue;
            }
            if (i == metricsDTO.getVideo_length()) {
                metrics.setVideo_length(Integer.valueOf(cellValue));
                continue;
            }

        }
        return metrics;
    }

    public static MetricsDTO getTitleFromFileImport(FileImported fileImported,Sheets sheetservice,SendEmailService sendEmailService) throws QTTTException {
        String range = MetricsDTO.SHEET_NAME + MetricsDTO.TITLE;
        ValueRange response_title;
        try {
            response_title = sheetservice.spreadsheets().values().get(fileImported.getFileId(), range)
                    .execute();
        } catch (Exception e) {
            throw new QTTTException("File: " + fileImported.getFileName() + " can't find data in sheet name data");
        }
        List<List<Object>> values = response_title.getValues();
        if (values == null || values.isEmpty()) {
            log.info("Post: File " + MetricsDTO.SHEET_NAME + " No data found.");
            return null;
        } else {
            List row = values.get(0) ;
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    return null;
                }

                MetricsDTO metrics_title = convertRowToTitleFileStructor(row,fileImported.getFileName(),sendEmailService);

        return metrics_title;
        }
    }
}
