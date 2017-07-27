package org.hofi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;

public class Utils {
  public static Properties readProperties(String propertiesFile) throws IOException {
    Properties prop = new Properties();
    InputStream in = Utils.class.getResourceAsStream(propertiesFile);
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

  public static void printAllProperties(Properties p) {
    Collections.list(p.keys()).forEach((o) -> System.out.println(o + ": " + p.getProperty(o.toString())));
  }

  public static String removeLastLetterIf(String value, char letter) {
    if(value.charAt(value.length() - 1) == letter) {
      return value.substring(0, value.length() - 1);
    }
    return value;
  }

  public static void printNewlines(int numberOfLines) {
    for(int i = 0; i < numberOfLines; i++){
      System.out.println("");
    }
  }
}