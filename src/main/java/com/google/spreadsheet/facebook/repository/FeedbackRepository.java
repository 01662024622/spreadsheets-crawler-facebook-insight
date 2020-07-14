package com.google.spreadsheet.facebook.repository;

import com.google.spreadsheet.facebook.model.Feedback;
import com.google.spreadsheet.facebook.model.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    @Query("select m from Feedback m where m.spreadsheet= ?1")
    List<Feedback> findAllBySpreadsheet(String spreadsheet);

    @Query("delete from Feedback m where m.spreadsheet= ?1")
    void deleteAllBySpreadsheet(String spreadsheet);
}
