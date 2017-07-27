package org.hofi.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
  public static String readHTTPResponseToString(String parseUrl) throws IOException {
    URL url = new URL(parseUrl);
    URLConnection con = url.openConnection();
    InputStream in = con.getInputStream();
    String encoding = con.getContentEncoding();
    encoding = encoding == null ? "UTF-8" : encoding;
    return IOUtils.toString(in, encoding);
  }

  public static String parseResponse(String response, String from, String till) {
    if(!response.contains(from))
      throw new IllegalStateException("from not found: " + from);

    if(!response.contains(till))
      throw new IllegalStateException("till not found: " + till);

    int fromPos = response.indexOf(from) + from.length();
    int tillPos = response.indexOf(till, response.indexOf(from));
    return response.substring(fromPos, tillPos);
  }
}
