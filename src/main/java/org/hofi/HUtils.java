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
    if(in != null) {
      prop.load(in);
      in.close();
    } else {
      throw new FileNotFoundException("file <" + propertiesFile + "> not found");
    }
    return prop;
  }

  public static String firstLetterToLowerCase(String string) {
    return Character.toLowerCase(string.charAt(0)) + string.substring(1);
  }

  public static String firstLetterToUpperCase(String string) {
    return Character.toUpperCase(string.charAt(0)) + string.substring(1);
  }

  public static String removeNumbers(String string) {
    return string.replaceAll("[0-9]", "");
  }

  public static void deleteFile(String filename) throws FileNotFoundException {
    File f = new File(filename);
    if(!f.delete())
      throw new IllegalStateException("error deleting file <" + filename + ">");
  }

  public static String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }
}