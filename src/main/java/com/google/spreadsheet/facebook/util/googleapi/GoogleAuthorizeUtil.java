package com.google.spreadsheet.facebook.util.googleapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GoogleAuthorizeUtil {

  private static final File CREDENTIALS_FOLDER = new File(System.getProperty("user.home"),
      "credentials");

  public static Credential authorize() throws GeneralSecurityException, IOException {
    //Build GoogleClientSecrets from a JSON file
    InputStream in = GoogleAuthorizeUtil.class
        .getResourceAsStream("/credential.json");
    GoogleClientSecrets clientSecrets = GoogleClientSecrets
        .load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

    List<String> scopes = Arrays
        .asList(SheetsScopes.SPREADSHEETS, DriveScopes.DRIVE_READONLY, DriveScopes.DRIVE_FILE);

    //Build Credential object
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
        clientSecrets, scopes)
        .setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
        .setAccessType("offline")
        .build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
        .authorize("user");

    return credential;
  }

  public static HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer) {
    return new HttpRequestInitializer() {
      @Override
      public void initialize(HttpRequest httpRequest) throws IOException {
        requestInitializer.initialize(httpRequest);
        httpRequest.setConnectTimeout(60 * 60000);  // 60 minutes connect timeout
        httpRequest.setReadTimeout(60 * 60000);  // 60 minutes read timeout
      }
    };
  }
}
