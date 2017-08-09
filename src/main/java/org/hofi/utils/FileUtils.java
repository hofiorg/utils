package org.hofi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
  public static void deleteFile(String filename) throws FileNotFoundException {
    if(!new File(filename).delete())
      throw new IllegalStateException("error deleting file <" + filename + ">");
  }

  public static String readFile(String filename) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(filename));
    return new String(encoded, Charset.forName("UTF-8"));
  }

  public static void writeFile(String filename, String text) throws FileNotFoundException {
    try(PrintWriter out = new PrintWriter(filename)) {
      out.print( text );
    }
  }
}
