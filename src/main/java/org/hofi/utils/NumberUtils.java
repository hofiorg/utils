package org.hofi.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberUtils {
  public static String formatNumber(Number number) {
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.format(number);
  }

  public static Number parseNumber(String s) throws ParseException {
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.parse(s);
  }

  public static String formatEuroNumber(Double number) {
    return formatNumber(number) + "€";
  }
}
