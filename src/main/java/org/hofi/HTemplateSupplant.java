package org.hofi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HTemplateSupplant {

  private String templateFile;

  public HTemplateSupplant(String templateFilename) throws UncheckedIOException {
    try {
      templateFile = HUtils.readFile(templateFilename, Charset.forName("UTF-8"));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public HTemplateSupplant(String templateFilename, int line) throws UncheckedIOException {
    this(templateFilename, line, line);
  }

  public HTemplateSupplant(String templateFilename, int startLine, int endLine) throws UncheckedIOException {
    try {
      templateFile = readFile(templateFilename, startLine, endLine);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void replace(String tag, StringBuilder replace) {
    this.replace(tag, replace.toString());
  }

  public void replace(String tag, String replace) {
    replace = replace.replaceAll("\\$", "\\\\\\$");
    replace = replace.replaceAll("^\\s+|\\s+$", "");
    replaceNoTrim(tag, replace);
  }

  public void replaceNoTrim(String tag, String replace) {
    String repl = "%" + tag + "%";
    if(!templateFile.contains(repl))
      throw new IllegalArgumentException("tag <" + tag + "> not found");
    templateFile = templateFile.replaceAll(repl, replace);
  }

  public void write(String filename) throws FileNotFoundException {
    PrintWriter out = new PrintWriter(filename);
    out.println(templateFile);
    out.close();
  }

  public void out() {
    System.out.println(templateFile);
  }

  public String getNL() {
    return "\n" + templateFile + "\n";
  }

  public String get() {
    return templateFile + "\n";
  }

  private static String readFile(String path, int startLine, int endLine) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(path));
    lines = lines.subList(startLine - 1, endLine);
    return String.join("\n", lines);
  }
}