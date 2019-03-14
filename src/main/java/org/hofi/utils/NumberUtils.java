package org.hofi.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtils {

  private NumberUtils() {}

  public static String formatNumber(Number number) {
    Locale.setDefault(new Locale("de", "DE"));
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.format(number);
  }

  public static Number parseNumber(String s) throws ParseException {
    Locale.setDefault(new Locale("de", "DE"));
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.parse(s);
  }

  public static String formatEuroNumber(Double number) {
    return formatNumber(number) + "€";
  }
}
