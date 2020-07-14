package com.google.spreadsheet.facebook.repository;

import com.google.spreadsheet.facebook.model.Even;
import com.google.spreadsheet.facebook.model.Feedback;
import com.google.spreadsheet.facebook.model.FileImported;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenRepository extends JpaRepository<Even,Long> {
    @Query("select m from Even m where m.spreadsheet= ?1")
    List<Even> findAllBySpreadsheet(String spreadsheet);

    @Query("delete from Even m where m.spreadsheet= ?1")
    void deleteAllBySpreadsheet(String spreadsheet);
}
