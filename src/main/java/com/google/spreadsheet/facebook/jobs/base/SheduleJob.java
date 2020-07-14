package com.google.spreadsheet.facebook.jobs.base;

import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.FileImported;

import java.io.IOException;

public interface SheduleJob {
    void importData() throws IOException, QTTTException;
    void importDataAfterCheckSheet(FileImported fileImported) throws IOException, QTTTException;
}
