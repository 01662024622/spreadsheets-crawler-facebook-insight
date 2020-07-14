package com.google.spreadsheet.facebook.model.page;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "page_metrics")
public class PageMetrics {

    @Id
    private Integer date_id;
    private String date_full;

    private Integer lifetime_total_likes;
    private Integer daily_new_likes;
    private Integer daily_unlikes;
    private Integer daily_page_engaged_users;
    private Integer weekly_page_engaged_users;
    private Integer days_page_engaged_users;
    private Integer daily_total_reach;
    private Integer weekly_total_reach;
    private Integer days_total_reach;
    private Integer daily_organic_reach;
    private Integer weekly_organic_reach;
    private Integer days_organic_reach;
    private Integer daily_paid_reach;
    private Integer weekly_paid_reach;
    private Integer days_paid_reach;
    private Integer daily_viral_reach;
    private Integer weekly_viral_reach;
    private Integer days_viral_reach;
    private Integer daily_total_impressions;
    private Integer weekly_total_impressions;
    private Integer days_total_impressions;
    private Integer daily_organic_impressions;
    private Integer weekly_organic_impressions;
    private Integer days_organic_impressions;
    private Integer daily_paid_impressions;
    private Integer weekly_paid_impressions;
    private Integer days_paid_impressions;
    private Integer daily_viral_impressions;
    private Integer weekly_viral_impressions;
    private Integer days_viral_impressions;
    private Integer daily_logged_in_page_views;
    private Integer weekly_logged_in_page_views;
    private Integer daily_logged_in_page_views_total;
    private Integer weekly_logged_in_page_views_total;
    private Integer daily_reach_of_page_posts;
    private Integer weekly_reach_of_page_posts;
    private Integer days_reach_of_page_posts;
    private Integer daily_organic_reach_of_page_posts;
    private Integer weekly_organic_reach_of_page_posts;
    private Integer days_organic_reach_of_page_posts;
    private Integer daily_paid_reach_of_page_posts;
    private Integer weekly_paid_reach_of_page_posts;
    private Integer days_paid_reach_of_page_posts;
    private Integer daily_viral_reach_of_page_posts;
    private Integer weekly_viral_reach_of_page_posts;
    private Integer days_viral_reach_of_page_posts;
    private Integer daily_total_impressions_of_your_posts;
    private Integer weekly_total_impressions_of_your_posts;
    private Integer days_total_impressions_of_your_posts;
    private Integer daily_organic_impressions_of_your_posts;
    private Integer weekly_organic_impressions_of_your_posts;
    private Integer days_organic_impressions_of_your_posts;
    private Integer daily_paid_impressions_of_your_posts;
    private Integer weekly_paid_impressions_of_your_posts;
    private Integer days_paid_impressions_of_your_posts;
    private Integer daily_viral_impressions_of_your_posts;
    private Integer weekly_viral_impressions_of_your_posts;
    private Integer days_viral_impressions_of_your_posts;
    private Integer daily_total_consumers;
    private Integer weekly_total_consumers;
    private Integer days_total_consumers;
    private Integer daily_page_consumptions;
    private Integer weekly_page_consumptions;
    private Integer days_page_consumptions;
    private Integer daily_negative_feedback;
    private Integer weekly_negative_feedback;
    private Integer days_negative_feedback;
    private Integer daily_negative_feedback_from_users;
    private Integer weekly_negative_feedback_from_users;
    private Integer days_negative_feedback_from_users;
    private Integer daily_total_check_ins;
    private Integer weekly_total_check_ins;
    private Integer days_total_check_ins;
    private Integer daily_total_check_ins_total;
    private Integer weekly_total_check_ins_total;
    private Integer days_total_check_ins_toal;
    private Integer daily_total_check_ins_using_mobile_devices;
    private Integer weekly_total_check_ins_using_mobile_devices;
    private Integer days_total_check_ins_using_mobile_devices;
    private Integer daily_total_check_ins_using_mobile_devices_total;
    private Integer weekly_total_check_ins_using_mobile_devices_total;
    private Integer days_total_check_ins_using_mobile_devices_total;
    private Integer daily_total_organic_views;
    private Integer weekly_total_organic_views;
    private Integer days_total_organic_views;
    private Integer daily_total_promoted_views;
    private Integer weekly_total_promoted_views;
    private Integer days_total_promoted_views;
    private Integer daily_total_organic_30_second_views;
    private Integer weekly_total_organic_30_second_views;
    private Integer days_total_organic_30_second_views;
    private Integer daily_paid_30_second_views;
    private Integer weekly_paid_30_second_views;
    private Integer days_paid_30_second_views;
    private Integer daily_total_video_views;
    private Integer weekly_total_video_views;
    private Integer days_total_video_views;
    private Integer daily_total_auto_played_views;
    private Integer weekly_total_auto_played_views;
    private Integer days_total_auto_played_views;
    private Integer daily_total_clicked_views;
    private Integer weekly_total_clicked_views;
    private Integer days_total_clicked_views;
    private Integer daily_video_repeats;
    private Integer weekly_video_repeats;
    private Integer days_video_repeats;
    private Integer daily_total_unique_video_views;
    private Integer weekly_total_unique_video_views;
    private Integer days_total_unique_video_views;
    private Integer daily_total_30_second_views;
    private Integer weekly_total_30_second_views;
    private Integer days_total_30_second_views;
    private Integer daily_auto_played_30_second_views;
    private Integer weekly_auto_played_30_second_views;
    private Integer days_auto_played_30_second_views;
    private Integer daily_total_clicked_30_second_views;
    private Integer weekly_total_clicked_30_second_views;
    private Integer days_total_clicked_30_second_views;
    private Integer daily_total_30_second_repeats;
    private Integer weekly_total_30_second_repeats;
    private Integer days_total_30_second_repeats;
    private Integer daily_total_unique_30_second_views;
    private Integer weekly_total_unique_30_second_views;
    private Integer days_total_unique_30_second_views;
    private Integer daily_total_total_action_count_per_page;
    private Integer weekly_total_total_action_count_per_page;
    private Integer daily_total_get_direction_click_count_per_page;
    private Integer weekly_total_get_direction_click_count_per_page;
    private Integer daily_total_get_direction_click_count_per_page_toal;
    private Integer weekly_total_get_direction_click_count_per_page_total;
    private Integer daily_total_phone_calls_click_count_per_page;
    private Integer weekly_total_phone_calls_click_count_per_page;
    private Integer daily_total_phone_calls_click_count_per_page_total;
    private Integer weekly_total_phone_calls_click_count_per_page_total;
    private Integer daily_total_website_click_count_per_page;
    private Integer weekly_total_website_click_count_per_page;
    private Integer daily_total_website_click_count_per_page_total;
    private Integer weekly_total_website_click_count_per_page_total;

    private String spreadsheet;
    private Integer type_sheet;
    private Integer page_export_day;

}
