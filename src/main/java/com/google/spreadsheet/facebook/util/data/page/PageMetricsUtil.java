package com.google.spreadsheet.facebook.util.data.page;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.DTO.page.PageMetricsDTO;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.model.page.PageMetrics;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.sender.SendMailContent;
import com.google.spreadsheet.facebook.util.validate.ValidateDatatypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PageMetricsUtil {
    public static List<PageMetrics> getAllDataFromFile(Sheets sheetsService,
                                                       FileImported fileImported, SendEmailService sendEmailService) throws QTTTException {

        String range = PageMetricsDTO.SHEET_NAME + PageMetricsDTO.RANGE;
        ValueRange response;
        try {
            response = sheetsService.spreadsheets().values().get(fileImported.getFileId(), range)
                    .execute();
        } catch (Exception e) {
            throw new QTTTException("File: " + fileImported.getFileName() + " can't find data in sheet name data");
        }
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            log.info("Post: File " + PageMetricsDTO.SHEET_NAME + " No data found.");
            return null;
        } else {
            PageMetricsDTO pageMetricsDTO = getTitleFromFileImport(fileImported, sheetsService, sendEmailService);
            List<PageMetrics> pageMetricsList = new ArrayList<>();
            String[] arr = fileImported.getFileName().split("_page_");
            for (List row : values) {
                if (ValidateDatatypeUtil.isBlankRow(row)) {
                    return null;
                }
                PageMetrics pageMetrics = getValue(pageMetricsDTO, row);
                pageMetrics.setPage_export_day(Integer.valueOf(arr[0]));
                pageMetrics.setType_sheet(Integer.valueOf(arr[1]));
                pageMetrics.setSpreadsheet(fileImported.getFileId());
                pageMetricsList.add(pageMetrics);

            }
            return pageMetricsList;
        }
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static PageMetricsDTO convertRowToTitleFileStructor(FileImported fileImported, List row,SendEmailService sendEmailService) {
        PageMetricsDTO dto = new PageMetricsDTO();
        for (int i = 0; i < row.size(); i++) {
            String cellValue = row.get(i).toString();
            switch (cellValue) {
                case "":
                    break;
                case PageMetricsDTO.DATE_ID:
                    dto.setDate_id(i);
                    break;
                case PageMetricsDTO.LIFETIME_TOTAL_LIKES:
                    dto.setLifetime_total_likes(i);
                    break;
                case PageMetricsDTO.DAILY_NEW_LIKES:
                    dto.setDaily_new_likes(i);
                    break;
                case PageMetricsDTO.DAILY_UNLIKES:
                    dto.setDaily_unlikes(i);
                    break;
                case PageMetricsDTO.DAILY_PAGE_ENGAGED_USERS:
                    dto.setDaily_page_engaged_users(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAGE_ENGAGED_USERS:
                    dto.setWeekly_page_engaged_users(i);
                    break;
                case PageMetricsDTO.DAYS_PAGE_ENGAGED_USERS:
                    dto.setDays_page_engaged_users(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_REACH:
                    dto.setDaily_total_reach(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_REACH:
                    dto.setWeekly_total_reach(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_REACH:
                    dto.setDays_total_reach(i);
                    break;
                case PageMetricsDTO.DAILY_ORGANIC_REACH:
                    dto.setDaily_organic_reach(i);
                    break;
                case PageMetricsDTO.WEEKLY_ORGANIC_REACH:
                    dto.setWeekly_organic_reach(i);
                    break;
                case PageMetricsDTO.DAYS_ORGANIC_REACH:
                    dto.setDays_organic_reach(i);
                    break;
                case PageMetricsDTO.DAILY_PAID_REACH:
                    dto.setDaily_paid_reach(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAID_REACH:
                    dto.setWeekly_paid_reach(i);
                    break;
                case PageMetricsDTO.DAYS_PAID_REACH:
                    dto.setDays_paid_reach(i);
                    break;
                case PageMetricsDTO.DAILY_VIRAL_REACH:
                    dto.setDaily_viral_reach(i);
                    break;
                case PageMetricsDTO.WEEKLY_VIRAL_REACH:
                    dto.setWeekly_viral_reach(i);
                    break;
                case PageMetricsDTO.DAYS_VIRAL_REACH:
                    dto.setDays_viral_reach(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_IMPRESSIONS:
                    dto.setDaily_total_impressions(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_IMPRESSIONS:
                    dto.setWeekly_total_impressions(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_IMPRESSIONS:
                    dto.setDays_total_impressions(i);
                    break;
                case PageMetricsDTO.DAILY_ORGANIC_IMPRESSIONS:
                    dto.setDaily_organic_impressions(i);
                    break;
                case PageMetricsDTO.WEEKLY_ORGANIC_IMPRESSIONS:
                    dto.setWeekly_organic_impressions(i);
                    break;
                case PageMetricsDTO.DAYS_ORGANIC_IMPRESSIONS:
                    dto.setDays_organic_impressions(i);
                    break;
                case PageMetricsDTO.DAILY_PAID_IMPRESSIONS:
                    dto.setDaily_paid_impressions(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAID_IMPRESSIONS:
                    dto.setWeekly_paid_impressions(i);
                    break;
                case PageMetricsDTO.DAYS_PAID_IMPRESSIONS:
                    dto.setDays_paid_impressions(i);
                    break;
                case PageMetricsDTO.DAILY_VIRAL_IMPRESSIONS:
                    dto.setDaily_viral_impressions(i);
                    break;
                case PageMetricsDTO.WEEKLY_VIRAL_IMPRESSIONS:
                    dto.setWeekly_viral_impressions(i);
                    break;
                case PageMetricsDTO.DAYS_VIRAL_IMPRESSIONS:
                    dto.setDays_viral_impressions(i);
                    break;
                case PageMetricsDTO.DAILY_LOGGED_IN_PAGE_VIEWS:
                    if (dto.getDaily_logged_in_page_views() != -1) {

                        dto.setDaily_logged_in_page_views_total(i);
                    } else {
                        dto.setDaily_logged_in_page_views(i);
                    }
                    break;
                case PageMetricsDTO.WEEKLY_LOGGED_IN_PAGE_VIEWS:
                    if (dto.getWeekly_logged_in_page_views() != -1) {
                        dto.setWeekly_logged_in_page_views_total(i);
                    } else {
                        dto.setWeekly_logged_in_page_views(i);
                    }
                    break;
                case PageMetricsDTO.DAILY_REACH_OF_PAGE_POSTS:
                    dto.setDaily_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_REACH_OF_PAGE_POSTS:
                    dto.setWeekly_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAYS_REACH_OF_PAGE_POSTS:
                    dto.setDays_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAILY_ORGANIC_REACH_OF_PAGE_POSTS:
                    dto.setDaily_organic_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_ORGANIC_REACH_OF_PAGE_POSTS:
                    dto.setWeekly_organic_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAYS_ORGANIC_REACH_OF_PAGE_POSTS:
                    dto.setDays_organic_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAILY_PAID_REACH_OF_PAGE_POSTS:
                    dto.setDaily_paid_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAID_REACH_OF_PAGE_POSTS:
                    dto.setWeekly_paid_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAYS_PAID_REACH_OF_PAGE_POSTS:
                    dto.setDays_paid_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAILY_VIRAL_REACH_OF_PAGE_POSTS:
                    dto.setDaily_viral_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_VIRAL_REACH_OF_PAGE_POSTS:
                    dto.setWeekly_viral_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAYS_VIRAL_REACH_OF_PAGE_POSTS:
                    dto.setDays_viral_reach_of_page_posts(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDaily_total_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setWeekly_total_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDays_total_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAILY_ORGANIC_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDaily_organic_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_ORGANIC_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setWeekly_organic_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAYS_ORGANIC_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDays_organic_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAILY_PAID_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDaily_paid_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAID_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setWeekly_paid_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAYS_PAID_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDays_paid_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAILY_VIRAL_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDaily_viral_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.WEEKLY_VIRAL_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setWeekly_viral_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAYS_VIRAL_IMPRESSIONS_OF_YOUR_POSTS:
                    dto.setDays_viral_impressions_of_your_posts(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_CONSUMERS:
                    dto.setDaily_total_consumers(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_CONSUMERS:
                    dto.setWeekly_total_consumers(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_CONSUMERS:
                    dto.setDays_total_consumers(i);
                    break;
                case PageMetricsDTO.DAILY_PAGE_CONSUMPTIONS:
                    dto.setDaily_page_consumptions(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAGE_CONSUMPTIONS:
                    dto.setWeekly_page_consumptions(i);
                    break;
                case PageMetricsDTO.DAYS_PAGE_CONSUMPTIONS:
                    dto.setDays_page_consumptions(i);
                    break;
                case PageMetricsDTO.DAILY_NEGATIVE_FEEDBACK:
                    dto.setDaily_negative_feedback(i);
                    break;
                case PageMetricsDTO.WEEKLY_NEGATIVE_FEEDBACK:
                    dto.setWeekly_negative_feedback(i);
                    break;
                case PageMetricsDTO.DAYS_NEGATIVE_FEEDBACK:
                    dto.setDays_negative_feedback(i);
                    break;
                case PageMetricsDTO.DAILY_NEGATIVE_FEEDBACK_FROM_USERS:
                    dto.setDaily_negative_feedback_from_users(i);
                    break;
                case PageMetricsDTO.WEEKLY_NEGATIVE_FEEDBACK_FROM_USERS:
                    dto.setWeekly_negative_feedback_from_users(i);
                    break;
                case PageMetricsDTO.DAYS_NEGATIVE_FEEDBACK_FROM_USERS:
                    dto.setDays_negative_feedback_from_users(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_CHECK_INS:
                    if (dto.getDaily_total_check_ins() != -1) {

                        dto.setDaily_total_check_ins_total(i);
                    } else {
                        dto.setDaily_total_check_ins(i);
                    }

                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_CHECK_INS:
                    if (dto.getWeekly_total_check_ins() != -1) {

                        dto.setWeekly_total_check_ins_total(i);
                    } else {
                        dto.setWeekly_total_check_ins(i);
                    }

                    break;
                case PageMetricsDTO.DAYS_TOTAL_CHECK_INS:
                    if (dto.getDays_total_check_ins() != -1) {

                        dto.setDays_total_check_ins_toal(i);
                    } else {
                        dto.setDays_total_check_ins(i);
                    }

                    break;
                case PageMetricsDTO.DAILY_TOTAL_CHECK_INS_USING_MOBILE_DEVICES:
                    if (dto.getDaily_total_check_ins_using_mobile_devices() != -1) {

                        dto.setDaily_total_check_ins_using_mobile_devices_total(i);
                    } else {
                        dto.setDaily_total_check_ins_using_mobile_devices(i);
                    }

                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_CHECK_INS_USING_MOBILE_DEVICES:
                    if (dto.getWeekly_total_check_ins_using_mobile_devices() != -1) {

                        dto.setWeekly_total_check_ins_using_mobile_devices_total(i);
                    } else {
                        dto.setWeekly_total_check_ins_using_mobile_devices(i);
                    }

                    break;
                case PageMetricsDTO.DAYS_TOTAL_CHECK_INS_USING_MOBILE_DEVICES:
                    if (dto.getDays_total_check_ins_using_mobile_devices() != -1) {

                        dto.setDays_total_check_ins_using_mobile_devices_total(i);
                    } else {
                        dto.setDays_total_check_ins_using_mobile_devices(i);
                    }

                    break;
                case PageMetricsDTO.DAILY_TOTAL_ORGANIC_VIEWS:
                    dto.setDaily_total_organic_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_ORGANIC_VIEWS:
                    dto.setWeekly_total_organic_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_ORGANIC_VIEWS:
                    dto.setDays_total_organic_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_PROMOTED_VIEWS:
                    dto.setDaily_total_promoted_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_PROMOTED_VIEWS:
                    dto.setWeekly_total_promoted_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_PROMOTED_VIEWS:
                    dto.setDays_total_promoted_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_ORGANIC_30_SECOND_VIEWS:
                    dto.setDaily_total_organic_30_second_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_ORGANIC_30_SECOND_VIEWS:
                    dto.setWeekly_total_organic_30_second_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_ORGANIC_30_SECOND_VIEWS:
                    dto.setDays_total_organic_30_second_views(i);
                    break;
                case PageMetricsDTO.DAILY_PAID_30_SECOND_VIEWS:
                    dto.setDaily_paid_30_second_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_PAID_30_SECOND_VIEWS:
                    dto.setWeekly_paid_30_second_views(i);
                    break;
                case PageMetricsDTO.DAYS_PAID_30_SECOND_VIEWS:
                    dto.setDays_paid_30_second_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_VIDEO_VIEWS:
                    dto.setDaily_total_video_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_VIDEO_VIEWS:
                    dto.setWeekly_total_video_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_VIDEO_VIEWS:
                    dto.setDays_total_video_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_AUTO_PLAYED_VIEWS:
                    dto.setDaily_total_auto_played_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_AUTO_PLAYED_VIEWS:
                    dto.setWeekly_total_auto_played_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_AUTO_PLAYED_VIEWS:
                    dto.setDays_total_auto_played_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_CLICKED_VIEWS:
                    dto.setDaily_total_clicked_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_CLICKED_VIEWS:
                    dto.setWeekly_total_clicked_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_CLICKED_VIEWS:
                    dto.setDays_total_clicked_views(i);
                    break;
                case PageMetricsDTO.DAILY_VIDEO_REPEATS:
                    dto.setDaily_video_repeats(i);
                    break;
                case PageMetricsDTO.WEEKLY_VIDEO_REPEATS:
                    dto.setWeekly_video_repeats(i);
                    break;
                case PageMetricsDTO.DAYS_VIDEO_REPEATS:
                    dto.setDays_video_repeats(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_UNIQUE_VIDEO_VIEWS:
                    dto.setDaily_total_unique_video_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_UNIQUE_VIDEO_VIEWS:
                    dto.setWeekly_total_unique_video_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_UNIQUE_VIDEO_VIEWS:
                    dto.setDays_total_unique_video_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_30_SECOND_VIEWS:
                    dto.setDaily_total_30_second_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_30_SECOND_VIEWS:
                    dto.setWeekly_total_30_second_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_30_SECOND_VIEWS:
                    dto.setDays_total_30_second_views(i);
                    break;
                case PageMetricsDTO.DAILY_AUTO_PLAYED_30_SECOND_VIEWS:
                    dto.setDaily_auto_played_30_second_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_AUTO_PLAYED_30_SECOND_VIEWS:
                    dto.setWeekly_auto_played_30_second_views(i);
                    break;
                case PageMetricsDTO.DAYS_AUTO_PLAYED_30_SECOND_VIEWS:
                    dto.setDays_auto_played_30_second_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_CLICKED_30_SECOND_VIEWS:
                    dto.setDaily_total_clicked_30_second_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_CLICKED_30_SECOND_VIEWS:
                    dto.setWeekly_total_clicked_30_second_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_CLICKED_30_SECOND_VIEWS:
                    dto.setDays_total_clicked_30_second_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_30_SECOND_REPEATS:
                    dto.setDaily_total_30_second_repeats(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_30_SECOND_REPEATS:
                    dto.setWeekly_total_30_second_repeats(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_30_SECOND_REPEATS:
                    dto.setDays_total_30_second_repeats(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_UNIQUE_30_SECOND_VIEWS:
                    dto.setDaily_total_unique_30_second_views(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_UNIQUE_30_SECOND_VIEWS:
                    dto.setWeekly_total_unique_30_second_views(i);
                    break;
                case PageMetricsDTO.DAYS_TOTAL_UNIQUE_30_SECOND_VIEWS:
                    dto.setDays_total_unique_30_second_views(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_TOTAL_ACTION_COUNT_PER_PAGE:
                    dto.setDaily_total_total_action_count_per_page(i);
                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_TOTAL_ACTION_COUNT_PER_PAGE:
                    dto.setWeekly_total_total_action_count_per_page(i);
                    break;
                case PageMetricsDTO.DAILY_TOTAL_GET_DIRECTION_CLICK_COUNT_PER_PAGE:
                    if (dto.getDaily_total_get_direction_click_count_per_page() != -1) {

                        dto.setDaily_total_get_direction_click_count_per_page_toal(i);
                    } else {
                        dto.setDaily_total_get_direction_click_count_per_page(i);
                    }

                    break;

                case PageMetricsDTO.WEEKLY_TOTAL_GET_DIRECTION_CLICK_COUNT_PER_PAGE:
                    if (dto.getWeekly_total_get_direction_click_count_per_page_total() != -1) {

                        dto.setWeekly_total_get_direction_click_count_per_page_total(i);
                    } else {
                        dto.setWeekly_total_get_direction_click_count_per_page(i);
                    }

                    break;
                case PageMetricsDTO.DAILY_TOTAL_PHONE_CALLS_CLICK_COUNT_PER_PAGE:
                    if (dto.getDaily_total_phone_calls_click_count_per_page() != -1) {

                        dto.setDaily_total_phone_calls_click_count_per_page_total(i);
                    } else {
                        dto.setDaily_total_phone_calls_click_count_per_page(i);
                    }

                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_PHONE_CALLS_CLICK_COUNT_PER_PAGE:
                    if (dto.getWeekly_total_phone_calls_click_count_per_page() != -1) {

                        dto.setWeekly_total_phone_calls_click_count_per_page_total(i);
                    } else {
                        dto.setWeekly_total_phone_calls_click_count_per_page(i);
                    }

                    break;

                case PageMetricsDTO.DAILY_TOTAL_WEBSITE_CLICK_COUNT_PER_PAGE:
                    if (dto.getDaily_total_website_click_count_per_page() != -1) {

                        dto.setDaily_total_website_click_count_per_page_total(i);
                    } else {
                        dto.setDaily_total_website_click_count_per_page(i);
                    }

                    break;
                case PageMetricsDTO.WEEKLY_TOTAL_WEBSITE_CLICK_COUNT_PER_PAGE:
                    if (dto.getWeekly_total_website_click_count_per_page() != -1) {

                        dto.setWeekly_total_website_click_count_per_page_total(i);
                    } else {
                        dto.setWeekly_total_website_click_count_per_page(i);
                    }

                    break;
                default:
                    sendEmailService.sendSimpleMessage(SendMailContent.getErrorField("post",fileImported.getFileName(),dto.SHEET_NAME,cellValue));
                    break;
            }
        }
        return dto;
    }

    /**
     * Lay gia tri cua hang trong file
     */
    private static PageMetrics getValue(PageMetricsDTO pageMetricsDTO, List row) {
        PageMetrics pageMetrics = new PageMetrics();
        for (int i = 0; i < row.size(); i++) {
            String cellValue = row.get(i).toString();
            if (cellValue == null || cellValue.equals("") || cellValue.equals(" ")) {
                continue;
            }
            if (i == pageMetricsDTO.getDate_id()) {
                pageMetrics.setDate_full(cellValue);
               Integer date =ValidateDatatypeUtil.validateDateString(cellValue);
                pageMetrics.setDate_id(date);
                continue;
            }
            if (i == pageMetricsDTO.getLifetime_total_likes()) {
                pageMetrics.setLifetime_total_likes(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_new_likes()) {
                pageMetrics.setDaily_new_likes(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_unlikes()) {
                pageMetrics.setDaily_unlikes(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_page_engaged_users()) {
                pageMetrics.setDaily_page_engaged_users(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_page_engaged_users()) {
                pageMetrics.setWeekly_page_engaged_users(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_page_engaged_users()) {
                pageMetrics.setDays_page_engaged_users(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_reach()) {
                pageMetrics.setDaily_total_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_reach()) {
                pageMetrics.setWeekly_total_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_reach()) {
                pageMetrics.setDays_total_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_organic_reach()) {
                pageMetrics.setDaily_organic_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_organic_reach()) {
                pageMetrics.setWeekly_organic_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_organic_reach()) {
                pageMetrics.setDays_organic_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_paid_reach()) {
                pageMetrics.setDaily_paid_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_paid_reach()) {
                pageMetrics.setWeekly_paid_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_paid_reach()) {
                pageMetrics.setDays_paid_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_viral_reach()) {
                pageMetrics.setDaily_viral_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_viral_reach()) {
                pageMetrics.setWeekly_viral_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_viral_reach()) {
                pageMetrics.setDays_viral_reach(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_impressions()) {
                pageMetrics.setDaily_total_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_impressions()) {
                pageMetrics.setWeekly_total_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_impressions()) {
                pageMetrics.setDays_total_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_organic_impressions()) {
                pageMetrics.setDaily_organic_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_organic_impressions()) {
                pageMetrics.setWeekly_organic_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_organic_impressions()) {
                pageMetrics.setDays_organic_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_paid_impressions()) {
                pageMetrics.setDaily_paid_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_paid_impressions()) {
                pageMetrics.setWeekly_paid_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_paid_impressions()) {
                pageMetrics.setDays_paid_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_viral_impressions()) {
                pageMetrics.setDaily_viral_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_viral_impressions()) {
                pageMetrics.setWeekly_viral_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_viral_impressions()) {
                pageMetrics.setDays_viral_impressions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_logged_in_page_views()) {
                pageMetrics.setDaily_logged_in_page_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_logged_in_page_views()) {
                pageMetrics.setWeekly_logged_in_page_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_logged_in_page_views_total()) {
                pageMetrics.setDaily_logged_in_page_views_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_logged_in_page_views_total()) {
                pageMetrics.setWeekly_logged_in_page_views_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_reach_of_page_posts()) {
                pageMetrics.setDaily_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_reach_of_page_posts()) {
                pageMetrics.setWeekly_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_reach_of_page_posts()) {
                pageMetrics.setDays_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_organic_reach_of_page_posts()) {
                pageMetrics.setDaily_organic_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_organic_reach_of_page_posts()) {
                pageMetrics.setWeekly_organic_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_organic_reach_of_page_posts()) {
                pageMetrics.setDays_organic_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_paid_reach_of_page_posts()) {
                pageMetrics.setDaily_paid_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_paid_reach_of_page_posts()) {
                pageMetrics.setWeekly_paid_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_paid_reach_of_page_posts()) {
                pageMetrics.setDays_paid_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_viral_reach_of_page_posts()) {
                pageMetrics.setDaily_viral_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_viral_reach_of_page_posts()) {
                pageMetrics.setWeekly_viral_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_viral_reach_of_page_posts()) {
                pageMetrics.setDays_viral_reach_of_page_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_impressions_of_your_posts()) {
                pageMetrics.setDaily_total_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_impressions_of_your_posts()) {
                pageMetrics.setWeekly_total_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_impressions_of_your_posts()) {
                pageMetrics.setDays_total_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_organic_impressions_of_your_posts()) {
                pageMetrics.setDaily_organic_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_organic_impressions_of_your_posts()) {
                pageMetrics.setWeekly_organic_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_organic_impressions_of_your_posts()) {
                pageMetrics.setDays_organic_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_paid_impressions_of_your_posts()) {
                pageMetrics.setDaily_paid_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_paid_impressions_of_your_posts()) {
                pageMetrics.setWeekly_paid_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_paid_impressions_of_your_posts()) {
                pageMetrics.setDays_paid_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_viral_impressions_of_your_posts()) {
                pageMetrics.setDaily_viral_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_viral_impressions_of_your_posts()) {
                pageMetrics.setWeekly_viral_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_viral_impressions_of_your_posts()) {
                pageMetrics.setDays_viral_impressions_of_your_posts(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_consumers()) {
                pageMetrics.setDaily_total_consumers(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_consumers()) {
                pageMetrics.setWeekly_total_consumers(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_consumers()) {
                pageMetrics.setDays_total_consumers(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_page_consumptions()) {
                pageMetrics.setDaily_page_consumptions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_page_consumptions()) {
                pageMetrics.setWeekly_page_consumptions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_page_consumptions()) {
                pageMetrics.setDays_page_consumptions(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_negative_feedback()) {
                pageMetrics.setDaily_negative_feedback(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_negative_feedback()) {
                pageMetrics.setWeekly_negative_feedback(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_negative_feedback()) {
                pageMetrics.setDays_negative_feedback(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_negative_feedback_from_users()) {
                pageMetrics.setDaily_negative_feedback_from_users(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_negative_feedback_from_users()) {
                pageMetrics.setWeekly_negative_feedback_from_users(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_negative_feedback_from_users()) {
                pageMetrics.setDays_negative_feedback_from_users(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_check_ins()) {
                pageMetrics.setDaily_total_check_ins(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_check_ins()) {
                pageMetrics.setWeekly_total_check_ins(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_check_ins()) {
                pageMetrics.setDays_total_check_ins(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_check_ins_total()) {
                pageMetrics.setDaily_total_check_ins_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_check_ins_total()) {
                pageMetrics.setWeekly_total_check_ins_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_check_ins_toal()) {
                pageMetrics.setDays_total_check_ins_toal(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_check_ins_using_mobile_devices()) {
                pageMetrics.setDaily_total_check_ins_using_mobile_devices(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_check_ins_using_mobile_devices()) {
                pageMetrics.setWeekly_total_check_ins_using_mobile_devices(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_check_ins_using_mobile_devices()) {
                pageMetrics.setDays_total_check_ins_using_mobile_devices(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_check_ins_using_mobile_devices_total()) {
                pageMetrics.setDaily_total_check_ins_using_mobile_devices_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_check_ins_using_mobile_devices_total()) {
                pageMetrics.setWeekly_total_check_ins_using_mobile_devices_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_check_ins_using_mobile_devices_total()) {
                pageMetrics.setDays_total_check_ins_using_mobile_devices_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_organic_views()) {
                pageMetrics.setDaily_total_organic_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_organic_views()) {
                pageMetrics.setWeekly_total_organic_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_organic_views()) {
                pageMetrics.setDays_total_organic_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_promoted_views()) {
                pageMetrics.setDaily_total_promoted_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_promoted_views()) {
                pageMetrics.setWeekly_total_promoted_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_promoted_views()) {
                pageMetrics.setDays_total_promoted_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_organic_30_second_views()) {
                pageMetrics.setDaily_total_organic_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_organic_30_second_views()) {
                pageMetrics.setWeekly_total_organic_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_organic_30_second_views()) {
                pageMetrics.setDays_total_organic_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_paid_30_second_views()) {
                pageMetrics.setDaily_paid_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_paid_30_second_views()) {
                pageMetrics.setWeekly_paid_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_paid_30_second_views()) {
                pageMetrics.setDays_paid_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_video_views()) {
                pageMetrics.setDaily_total_video_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_video_views()) {
                pageMetrics.setWeekly_total_video_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_video_views()) {
                pageMetrics.setDays_total_video_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_auto_played_views()) {
                pageMetrics.setDaily_total_auto_played_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_auto_played_views()) {
                pageMetrics.setWeekly_total_auto_played_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_auto_played_views()) {
                pageMetrics.setDays_total_auto_played_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_clicked_views()) {
                pageMetrics.setDaily_total_clicked_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_clicked_views()) {
                pageMetrics.setWeekly_total_clicked_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_clicked_views()) {
                pageMetrics.setDays_total_clicked_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_video_repeats()) {
                pageMetrics.setDaily_video_repeats(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_video_repeats()) {
                pageMetrics.setWeekly_video_repeats(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_video_repeats()) {
                pageMetrics.setDays_video_repeats(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_unique_video_views()) {
                pageMetrics.setDaily_total_unique_video_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_unique_video_views()) {
                pageMetrics.setWeekly_total_unique_video_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_unique_video_views()) {
                pageMetrics.setDays_total_unique_video_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_30_second_views()) {
                pageMetrics.setDaily_total_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_30_second_views()) {
                pageMetrics.setWeekly_total_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_30_second_views()) {
                pageMetrics.setDays_total_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_auto_played_30_second_views()) {
                pageMetrics.setDaily_auto_played_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_auto_played_30_second_views()) {
                pageMetrics.setWeekly_auto_played_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_auto_played_30_second_views()) {
                pageMetrics.setDays_auto_played_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_clicked_30_second_views()) {
                pageMetrics.setDaily_total_clicked_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_clicked_30_second_views()) {
                pageMetrics.setWeekly_total_clicked_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_clicked_30_second_views()) {
                pageMetrics.setDays_total_clicked_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_30_second_repeats()) {
                pageMetrics.setDaily_total_30_second_repeats(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_30_second_repeats()) {
                pageMetrics.setWeekly_total_30_second_repeats(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_30_second_repeats()) {
                pageMetrics.setDays_total_30_second_repeats(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_unique_30_second_views()) {
                pageMetrics.setDaily_total_unique_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_unique_30_second_views()) {
                pageMetrics.setWeekly_total_unique_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDays_total_unique_30_second_views()) {
                pageMetrics.setDays_total_unique_30_second_views(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_total_action_count_per_page()) {
                pageMetrics.setDaily_total_total_action_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_total_action_count_per_page()) {
                pageMetrics.setWeekly_total_total_action_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_get_direction_click_count_per_page()) {
                pageMetrics.setDaily_total_get_direction_click_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_get_direction_click_count_per_page()) {
                pageMetrics.setWeekly_total_get_direction_click_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_get_direction_click_count_per_page_toal()) {
                pageMetrics.setDaily_total_get_direction_click_count_per_page_toal(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_get_direction_click_count_per_page_total()) {
                pageMetrics.setWeekly_total_get_direction_click_count_per_page_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_phone_calls_click_count_per_page()) {
                pageMetrics.setDaily_total_phone_calls_click_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_phone_calls_click_count_per_page()) {
                pageMetrics.setWeekly_total_phone_calls_click_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_phone_calls_click_count_per_page_total()) {
                pageMetrics.setDaily_total_phone_calls_click_count_per_page_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_phone_calls_click_count_per_page_total()) {
                pageMetrics.setWeekly_total_phone_calls_click_count_per_page_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_website_click_count_per_page()) {
                pageMetrics.setDaily_total_website_click_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_website_click_count_per_page()) {
                pageMetrics.setWeekly_total_website_click_count_per_page(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getDaily_total_website_click_count_per_page_total()) {
                pageMetrics.setDaily_total_website_click_count_per_page_total(Integer.parseInt(cellValue));
                continue;
            }
            if (i == pageMetricsDTO.getWeekly_total_website_click_count_per_page_total()) {
                pageMetrics.setWeekly_total_website_click_count_per_page_total(Integer.parseInt(cellValue));
                continue;
            }

        }
        return pageMetrics;
    }

    public static PageMetricsDTO getTitleFromFileImport(FileImported fileImported, Sheets sheetservice,SendEmailService sendEmailService) throws QTTTException {
        String range = PageMetricsDTO.SHEET_NAME + PageMetricsDTO.TITLE;
        ValueRange response_title;
        try {
            response_title = sheetservice.spreadsheets().values().get(fileImported.getFileId(), range)
                    .execute();
        } catch (Exception e) {
            throw new QTTTException("File: " + fileImported.getFileName() + " can't find data in sheet name data");
        }
        List<List<Object>> values = response_title.getValues();
        if (values == null || values.isEmpty()) {
            log.info("Post: File " + PageMetricsDTO.SHEET_NAME + " No data found.");
            return null;
        } else {
            List row = values.get(0);
            if (ValidateDatatypeUtil.isBlankRow(row)) {
                return null;
            }

            PageMetricsDTO page_metrics_title = convertRowToTitleFileStructor(fileImported,row,sendEmailService);

            return page_metrics_title;
        }
    }
}
