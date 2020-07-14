package com.google.spreadsheet.facebook.model.DTO;

import lombok.Data;


@Data
public class MetricsDTO {

    private int id=-1;
    private int link=-1;
    private int content=-1;
    private int type=-1;
    private int created_at=-1;
    private int post_total_reach=-1;
    private int post_organic_reach=-1;
    private int post_paid_reach=-1;
    private int post_total_impressions=-1;
    private int post_organic_impressions=-1;
    private int post_paid_impressions=-1;
    private int engaged_users=-1;
    private int matched_audience_targeting_consumers_on_post=-1;
    private int matched_audience_targeting_consumptions_on_post=-1;
    private int negative_feedback_from_users=-1;
    private int negative_feedback=-1;
    private int post_impressions_by_people_who_have_liked_your_page=-1;
    private int post_reach_by_people_who_like_your_page=-1;
    private int post_paid_impressions_by_people_who_have_liked_your_page=-1;
    private int paid_reach_of_a_post_by_people_who_like_your_page=-1;
    private int people_who_have_liked_your_page_and_engaged_with_your_post=-1;
    private int organic_views_to_95=-1;
    private int organic_views_to_95_total=-1;
    private int paid_views_to_95=-1;
    private int paid_views_to_95_total=-1;
    private int organic_video_views=-1;
    private int organic_video_views_total=-1;
    private int paid_video_views=-1;
    private int paid_video_views_total=-1;
    private int average_time_video_viewed=-1;
    private int video_length=-1;

    public static final String RANGE = "!A3:AH";
    public static final String TITLE= "!A1:AH1";
    public static final String SHEET_NAME= "Key Metrics";


    public static final String COLUM_ID= "ID bài viết";
    public static final String LINK= "Liên kết vĩnh viễn";
    public static final String CONTENT= "Tin nhắn của bài viết";
    public static final String TYPE= "Loại";
    public static final String CREATED_AT= "Đã đăng";
    public static final String POST_TOTAL_REACH= "Lifetime Post Total Reach";
    public static final String POST_ORGANIC_REACH= "Lifetime Post organic reach";
    public static final String POST_PAID_REACH= "Lifetime Post Paid Reach";
    public static final String POST_TOTAL_IMPRESSIONS= "Lifetime Post Total Impressions";
    public static final String POST_ORGANIC_IMPRESSIONS= "Lifetime Post Organic Impressions";
    public static final String POST_PAID_IMPRESSIONS= "Lifetime Post Paid Impressions";
    public static final String ENGAGED_USERS= "Lifetime Engaged Users";
    public static final String MATCHED_AUDIENCE_TARGETING_CONSUMERS_ON_POST= "Lifetime Matched Audience Targeting Consumers on Post";
    public static final String MATCHED_AUDIENCE_TARGETING_CONSUMPTIONS_ON_POST= "Lifetime Matched Audience Targeting Consumptions on Post";
    public static final String NEGATIVE_FEEDBACK_FROM_USERS= "Lifetime Negative Feedback from Users";
    public static final String NEGATIVE_FEEDBACK= "Lifetime Negative Feedback";
    public static final String POST_IMPRESSIONS_BY_PEOPLE_WHO_HAVE_LIKED_YOUR_PAGE= "Lifetime Post Impressions by people who have liked your Page";
    public static final String POST_REACH_BY_PEOPLE_WHO_LIKE_YOUR_PAGE= "Lifetime Post reach by people who like your Page";
    public static final String POST_PAID_IMPRESSIONS_BY_PEOPLE_WHO_HAVE_LIKED_YOUR_PAGE= "Lifetime Post Paid Impressions by people who have liked your Page";
    public static final String PAID_REACH_OF_A_POST_BY_PEOPLE_WHO_LIKE_YOUR_PAGE= "Lifetime Paid reach of a post by people who like your Page";
    public static final String PEOPLE_WHO_HAVE_LIKED_YOUR_PAGE_AND_ENGAGED_WITH_YOUR_POST= "Lifetime People who have liked your Page and engaged with your post";
    public static final String ORGANIC_VIEWS_TO_95= "Lifetime Organic views to 95%";
    public static final String PAID_VIEWS_TO_95= "Lifetime Paid views to 95%";
    public static final String ORGANIC_VIDEO_VIEWS= "Lifetime Organic Video Views";
    public static final String PAID_VIDEO_VIEWS= "Lifetime Paid Video Views";
    public static final String AVERAGE_TIME_VIDEO_VIEWED= "Lifetime Average time video viewed";
    public static final String VIDEO_LENGTH= "Lifetime Video length";
}
