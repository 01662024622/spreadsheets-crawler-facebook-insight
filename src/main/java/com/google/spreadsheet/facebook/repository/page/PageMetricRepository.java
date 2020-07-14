package com.google.spreadsheet.facebook.repository.page;

import com.google.spreadsheet.facebook.model.page.PageMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PageMetricRepository extends JpaRepository<PageMetrics,Long> {
    @Query("delete from PageMetrics m where m.spreadsheet= ?1")
    void deleteAllBySpreadsheet(String spreadsheet);
}
