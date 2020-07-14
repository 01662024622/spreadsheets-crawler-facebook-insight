package com.google.spreadsheet.facebook.repository;

import com.google.spreadsheet.facebook.model.Action;
import com.google.spreadsheet.facebook.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {
    @Query("select m from Action m where m.spreadsheet= ?1")
    List<Action> findAllBySpreadsheet(String spreadsheet);

    @Query("delete from Action m where m.spreadsheet= ?1")
    void deleteAllBySpreadsheet(String spreadsheet);
}
