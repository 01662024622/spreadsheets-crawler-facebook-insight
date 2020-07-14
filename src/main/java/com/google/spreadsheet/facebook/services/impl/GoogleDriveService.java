package com.google.spreadsheet.facebook.services.impl;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.spreadsheet.facebook.constants.GoogleDriveMimeType;
import com.google.spreadsheet.facebook.file.GoogleDriveFile;
import com.google.spreadsheet.facebook.file.GoogleDriveFileComparator;
import com.google.spreadsheet.facebook.util.googleapi.GoogleDriveServiceUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class GoogleDriveService {

  private static Drive driveService;

  static {
    try {
      driveService = GoogleDriveServiceUtil.getDriveService();
    } catch (GeneralSecurityException | IOException e) {
      e.printStackTrace();
    }
  }

  public static List<GoogleDriveFile> listAllSpreadsheetsFiles(String folderID, String pattern)
      throws IOException {
    String pageToken = null;
    String folderIDQuery = "'" + folderID + "' in parents";
    String spreadsheetsMimeTypeQuery = GoogleDriveMimeType.SPREADSHEET;
    String query = folderIDQuery + " and " + spreadsheetsMimeTypeQuery;

    List<GoogleDriveFile> listFiles = new ArrayList<>();

    do {
      FileList result = driveService.files().list()
          .setQ(query)
          .setSpaces("drive")
          .setFields("nextPageToken, files(id, name, createdTime, modifiedTime, trashed)")
          .setPageToken(pageToken)
          .execute();
      for (File file : result.getFiles()) {
        if (file.getTrashed()) {
          continue;
        }
        String fileName = file.getName();

        if (fileName.matches(pattern)) {
          String fileId = file.getId();
          String createdTime = file.getCreatedTime().toStringRfc3339();
          String modifiedTime = file.getModifiedTime().toStringRfc3339();
          GoogleDriveFile newFile = new GoogleDriveFile(fileId, fileName, folderID,
              createdTime, modifiedTime);

          listFiles.add(newFile);
        }
      }
      pageToken = result.getNextPageToken();
    } while (pageToken != null);

    listFiles.sort(new GoogleDriveFileComparator());
    return listFiles;
  }
}
