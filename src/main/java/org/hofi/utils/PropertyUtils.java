package org.hofi.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

public class PropertyUtils {

  private PropertyUtils() {}

  public static Properties readProperties(String propertiesFile) throws IOException {
    Properties prop = new Properties();
    InputStream in = Utils.class.getResourceAsStream(propertiesFile);
    if(in == null)
      throw new FileNotFoundException("file <" + propertiesFile + "> not found");
    prop.load(in);
    in.close();
    return prop;
  }

  public static void printAllProperties(Properties p) {
    Collections.list(p.keys()).forEach(o -> System.out.println(o + ": " + p.getProperty(o.toString())));
  }
}
