package com.google.spreadsheet.facebook.util.data;

import com.google.spreadsheet.facebook.file.GoogleDriveFile;
import com.google.spreadsheet.facebook.model.FileImported;

import java.util.ArrayList;
import java.util.List;

public class GoogleDriveFileUtil {

  private GoogleDriveFileUtil() {
  }

  public static FileImported convertGoogleDriveFileToFileImported(GoogleDriveFile file) {

      FileImported newFile = new FileImported();

      newFile.setFileId(file.getFileId());
      newFile.setFileName(file.getFileName());
      newFile.setDriveId(file.getFolderId());
      newFile.setCreatedTime(file.getCreatedTime());
      newFile.setModifiedTime(file.getModifiedTime());



    return newFile;
  }

  public static void importDrive(){

  }
}
