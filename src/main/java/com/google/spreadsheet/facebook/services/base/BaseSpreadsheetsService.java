package com.google.spreadsheet.facebook.services.base;

import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.FileImported;

import java.io.IOException;
import java.util.List;

public interface BaseSpreadsheetsService<T, ID> extends BaseService<T, ID> {


  void importData(String driveId, List<String> sheetNames) throws IOException;

  void importData(String spreadsheetId, String sheetName) throws IOException;

  void importData(FileImported fileImported) throws IOException, QTTTException;

}
