package org.hofi.utils;

public class Utils {
  public static String firstLetterToLowerCase(String value) {
    return Character.toLowerCase(value.charAt(0)) + value.substring(1);
  }

  public static String firstLetterToUpperCase(String value) {
    return Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }

  public static String removeNumbers(String value) {
    return value.replaceAll("[0-9]", "");
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

  public static String indent(int indentation) {
    return indent(indentation, 2);
  }

  public static String indent(int indentation, int spaces) {
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < indentation; ++i) {
      for(int a = 0; a < spaces; ++a) {
        result.append(" ");
      }
    }
    return result.toString();
  }

  public static String snipBefore(String s, String before) {
    if(!s.contains(before))
      return s;
    return s.substring(0, s.indexOf(before));
  }

  public static String snipAfter(String s, String last) {
    if(!s.contains(last))
      return s;
    return s.substring(s.indexOf(last) + 1);
  }

  public static String snipAfterLast(String s, String last) {
    if(!s.contains(last))
      return s;
    return s.substring(s.lastIndexOf(last) + 1);
  }
}