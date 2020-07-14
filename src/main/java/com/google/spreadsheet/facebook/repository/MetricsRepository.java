package com.google.spreadsheet.facebook.repository;
import com.google.spreadsheet.facebook.model.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MetricsRepository extends JpaRepository<Metrics,Long>{

    @Query("select m from Metrics m where m.spreadsheet= ?1")
    List<Metrics> findAllBySpreadsheet(String spreadsheet);

    @Query("delete from Metrics m where m.spreadsheet= ?1")
    void deleteAllBySpreadsheet(String spreadsheet);
}
