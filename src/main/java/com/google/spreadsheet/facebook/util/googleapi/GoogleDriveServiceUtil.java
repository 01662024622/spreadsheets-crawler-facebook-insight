package com.google.spreadsheet.facebook.util.googleapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleDriveServiceUtil {

  private static final String APPLICATION_NAME = "Google Drive";

  /**
   * Create google drive service
   */
  public static Drive getDriveService() throws GeneralSecurityException, IOException {
    Credential credential = GoogleAuthorizeUtil.authorize();
    return new Drive.Builder(
        GoogleNetHttpTransport.newTrustedTransport(),
        JacksonFactory.getDefaultInstance(), GoogleAuthorizeUtil.setHttpTimeout(credential))
        .setApplicationName(APPLICATION_NAME)
        .build();
  }

}
