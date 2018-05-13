package org.hofi.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FileUtils {
  public static void makeDir(String dir) throws IOException {
    Path path = Paths.get(dir);
    Files.createDirectory(path);
  }

  public static void deleteDirRecursive(String dir) throws IOException {
    Path path = Paths.get(dir);
    Files.walk(path, FileVisitOption.FOLLOW_LINKS)
      .sorted(Comparator.reverseOrder())
      .forEach(FileUtils::deleteFile);
  }

  public static void deleteFile(String filename) throws IOException {
    Path path = Paths.get(filename);
    Files.delete(path);
  }

  private static void deleteFile(Path path) {
    try {
      Files.delete(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
