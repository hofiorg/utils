import org.hofi.utils.NumberUtils;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberUtilsTest {

  @org.junit.jupiter.api.Test
  void formatEuroNumber() {
    assertEquals("12,34€", NumberUtils.formatEuroNumber(12.34d));
  }

  @org.junit.jupiter.api.Test
  void formatNumber() {
    assertEquals("12,34", NumberUtils.formatNumber(12.34d));
  }

  @org.junit.jupiter.api.Test
  void parseNumber() throws ParseException {
    assertEquals(12.34d, NumberUtils.parseNumber("12,34"));
  }
}