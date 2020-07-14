package com.google.spreadsheet.facebook.model;



import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="metrics")
public class Metrics {

    @Id
    private Long id;
    private String link;
//    @Column(columnDefinition ="TEXT")
//    private String content;
    private Integer created_at;
    private String created_date;

    private String spreadsheet;
    private String type;
    private Integer type_sheet;
    private Integer page_export_day;

    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_total_reach=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_organic_reach=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_paid_reach=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_total_impressions=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_organic_impressions=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_paid_impressions=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer engaged_users=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer matched_audience_targeting_consumers_on_post=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer matched_audience_targeting_consumptions_on_post=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer negative_feedback_from_users=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer negative_feedback=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_impressions_by_people_who_have_liked_your_page=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_reach_by_people_who_like_your_page=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer post_paid_impressions_by_people_who_have_liked_your_page=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer paid_reach_of_a_post_by_people_who_like_your_page=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer people_who_have_liked_your_page_and_engaged_with_your_post=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer organic_views_to_95=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer organic_views_to_95_total=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer paid_views_to_95=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer paid_views_to_95_total=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer organic_video_views=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer organic_video_views_total=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer paid_video_views=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer paid_video_views_total=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer average_time_video_viewed=0;
    @Column(columnDefinition ="INTEGER DEFAULT 0")
    private Integer video_length=0;


}
