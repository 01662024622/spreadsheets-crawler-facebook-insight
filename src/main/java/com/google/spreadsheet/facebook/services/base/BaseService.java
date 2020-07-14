package com.google.spreadsheet.facebook.services.base;

import java.util.List;

public interface BaseService<T, ID>{

  void save(T object);

  void save(List<T> objects);

  T findById(ID id);

  List<T> findAllBySpreadsheet(String spreadsheet);

  void deleteAllBySpreadsheet(String spreadsheet);

  List<T> findAll();

}
