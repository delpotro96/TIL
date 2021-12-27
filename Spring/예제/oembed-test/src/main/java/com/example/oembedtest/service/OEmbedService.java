package com.example.oembedtest.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Service
public class OEmbedService {

  public static String getDomainName(String paramUrl) throws URISyntaxException {
    URI uri = new URI(paramUrl);
    String domain = uri.getHost();
    if (domain.startsWith("www.")) {
      domain = domain.substring(4);
    }
    if (domain.endsWith(".com")) {
      domain = domain.substring(0, domain.length() - 4);
    }
    return domain;
  }

  public String callEmbedProcess(String paramUrl) throws Exception {
    String domain = "";
    try {
      domain = getDomainName(paramUrl);
    } catch (URISyntaxException e) {
      return null;
    }

    String personalURL = "";

    if (domain.equals("youtube")) {
      personalURL =
          "https://www.youtube.com/oembed?url=" + paramUrl + "&feature=youtu.be&format=json";
    } else if (domain.equals("instagram")) {
      personalURL = "https://api.instagram.com/oembed?url=" + paramUrl;
    } else if (domain.equals("vimeo")) {
      personalURL = "https://vimeo.com/api/oembed.json?url=" + paramUrl;
    } else if (domain.equals("twitter")) {
      personalURL = "https://publish.twitter.com/oembed?url=" + paramUrl;
    }

    BufferedReader br;
    try {
      URL url = new URL(personalURL);

      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.setRequestMethod("GET");

      InputStream inputStream = (InputStream) httpURLConnection.getContent();
      InputStreamReader inputStreamReader =
          new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      br = new BufferedReader(inputStreamReader);

      String line;
      StringBuffer result = new StringBuffer();
      while ((line = br.readLine()) != null) {
        result.append(line);
      }
      System.out.println(result);
      return String.valueOf(result);
    } catch (IOException e) {
      e.printStackTrace();
    }

    throw new Exception("지원하지 않는 도메인입니다.");
  }
}
