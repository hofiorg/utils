package org.hofi.utils;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

  private DateUtils() {}

  public static Instant utilDateToInstant(Date utilDate) {
    return utilDate.toInstant();
  }

  public static Date instantToUtilDate(Instant utilDate) {
    return Date.from(utilDate);
  }

  public static ZonedDateTime utilDateToZonedDateTime(Date utilDate, ZoneId zoneId) {
    return ZonedDateTime.ofInstant(utilDate.toInstant(), zoneId);
  }

  public static LocalDate utilDateToLocalDate(Date utilDate, ZoneId zoneId) {
    return utilDateToLocalDateTime(utilDate, zoneId).toLocalDate();
  }

  static LocalDateTime utilDateToLocalDateTime(Date utilDate, ZoneId zoneId) {
    return LocalDateTime.ofInstant(utilDate.toInstant(), zoneId);
  }

  public static Date localDateTimeToUtilDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date zonedDateTimeToUtilDate(ZonedDateTime zonedDateTime) {
    return Date.from(zonedDateTime.toInstant());
  }

  public static boolean isBefore(Date utilDate1, Date utilDate2) {
    return utilDateToInstant(utilDate1).isBefore(utilDateToInstant(utilDate2));
  }

  public static boolean isAfterAmount(Date utilDate1, Date utilDate2, long amountToAdd, TemporalUnit unit) {
    return utilDateToInstant(utilDate1).isAfter(utilDateToInstant(utilDate2).plus(amountToAdd, unit));
  }

  public static Date plus(Date utilDate1, long amountToAdd, TemporalUnit unit) {
    return localDateTimeToUtilDate(utilDateToLocalDateTime(utilDate1, ZoneId.systemDefault()).plus(amountToAdd, unit));
  }

  public static Date minus(Date utilDate1, long amountToAdd, TemporalUnit unit) {
    return localDateTimeToUtilDate(utilDateToLocalDateTime(utilDate1, ZoneId.systemDefault()).minus(amountToAdd, unit));
  }

  public static ZonedDateTime toDateTime(LocalTime localTime, ZonedDateTime zonedDateTime) {
    ZonedDateTime zonedDateTime1 = ZonedDateTime.from(zonedDateTime);
    return zonedDateTime1.with(localTime);
  }

  public static XMLGregorianCalendar instantToXMLGregorianCalendar(DatatypeFactory datatypeFactory, Instant instant) {
    return datatypeFactory.newXMLGregorianCalendar(zonedDateTimeToGregorianCalendar(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())));
  }

  public static XMLGregorianCalendar utilDateToXMLGregorianCalendar(DatatypeFactory datatypeFactory, Date date) {
    return localDateToXMLGregorianCalendar(datatypeFactory, utilDateToLocalDate(date, ZoneId.systemDefault()));
  }

  public static XMLGregorianCalendar localDateToXMLGregorianCalendar(DatatypeFactory datatypeFactory, LocalDate date) {
    return datatypeFactory.newXMLGregorianCalendarDate(
      date.getYear(), date.getMonthValue(), date.getDayOfMonth(), DatatypeConstants.FIELD_UNDEFINED );
  }

  public static XMLGregorianCalendar localTimeToXMLGregorianCalendar(DatatypeFactory datatypeFactory, LocalTime time) {
    return datatypeFactory.newXMLGregorianCalendarTime(
      time.getHour(), time.getMinute(), time.getSecond(), DatatypeConstants.FIELD_UNDEFINED);
  }

  public static GregorianCalendar zonedDateTimeToGregorianCalendar(ZonedDateTime dateTime) {
    return GregorianCalendar.from(dateTime);
  }

  public static GregorianCalendar localDateToGregorianCalendar(LocalDate date) {
    return new GregorianCalendar(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth(), 0, 0, 0);
  }

  public static GregorianCalendar localTimeToGregorianCalendar(LocalTime time) {
    return new GregorianCalendar(1970, 0, 1, time.getHour(), time.getMinute(), time.getSecond());
  }

  public static LocalTime xmlGregorianCalendarToLocalTime(XMLGregorianCalendar xmlTime) {
    return LocalTime.of(xmlTime.getHour(), xmlTime.getMinute(), xmlTime.getSecond());
  }

  public static ZonedDateTime gregorianCalendarToZonedDateTime(GregorianCalendar gregorianCalendar) {
    return gregorianCalendar.toZonedDateTime();
  }

  public static long localDateTimeToMillis(LocalDateTime dbEarlyTradingSessionEnd) {
    ZonedDateTime zdt = dbEarlyTradingSessionEnd.atZone(ZoneId.systemDefault());
    return zdt.toInstant().toEpochMilli();
  }

  public static LocalDateTime millisToLocalDateTime(long millis) {
    return millisToZonedDateTime(millis).toLocalDateTime();
  }

  public static ZonedDateTime millisToZonedDateTime(long millis) {
    Instant instant = Instant.ofEpochMilli(millis);
    return instant.atZone(ZoneId.systemDefault());
  }

  public static Date localDateToUtilDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}