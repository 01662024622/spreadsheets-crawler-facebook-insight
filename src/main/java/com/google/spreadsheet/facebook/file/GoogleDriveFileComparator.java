package com.google.spreadsheet.facebook.file;

import java.util.Comparator;

public class GoogleDriveFileComparator implements Comparator<GoogleDriveFile> {

  @Override
  public int compare(GoogleDriveFile fileA, GoogleDriveFile fileB) {
    String fileACreatedTime = fileA.getCreatedTime();
    String fileBCreatedTime = fileB.getCreatedTime();

    String fileAModifiedTime = fileA.getModifiedTime();
    String fileBModifiedTime = fileB.getModifiedTime();

    return (fileACreatedTime.compareTo(fileBCreatedTime) != 0) ? (fileACreatedTime
        .compareTo(fileBCreatedTime)) : (fileAModifiedTime.compareTo(fileBModifiedTime));
  }
}