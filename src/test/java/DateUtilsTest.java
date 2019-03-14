import org.hofi.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

class DateUtilsTest {

  @Test
  void utilDateToInstant() {
    Date date = new Date(1552556576251L);
    Instant instant = DateUtils.utilDateToInstant(date);
    Assertions.assertEquals(1552556576251L, instant.toEpochMilli());
  }

  @Test
  void instantToUtilDate() {
    Instant instant = Instant.ofEpochMilli(1552556576251L);
    Date date = DateUtils.instantToUtilDate(instant);
    Assertions.assertEquals(1552556576251L, date.getTime());
  }

}