package org.hofi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class HUtils {
  public static Properties readProperties(String propertiesFile) throws IOException {
    Properties prop = new Properties();
    InputStream in = HUtils.class.getResourceAsStream(propertiesFile);
    if(in == null)
      throw new FileNotFoundException("file <" + propertiesFile + "> not found");
    prop.load(in);
    in.close();
    return prop;
  }

  public static String firstLetterToLowerCase(String value) {
    return Character.toLowerCase(value.charAt(0)) + value.substring(1);
  }

  public static String firstLetterToUpperCase(String value) {
    return Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }

  public static String removeNumbers(String value) {
    return value.replaceAll("[0-9]", "");
  }

  public static void deleteFile(String filename) throws FileNotFoundException {
    if(!new File(filename).delete())
      throw new IllegalStateException("error deleting file <" + filename + ">");
  }

  public static String readFile(String filename, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(filename));
    return new String(encoded, encoding);
  }
}