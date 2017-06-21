import org.hofi.HUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class HUtilsTest {
  @Test
  void firstLetterToLowerCase() {
    Assertions.assertEquals("hELLO", HUtils.firstLetterToLowerCase("HELLO"));
    Assertions.assertEquals("hELLO WORLD", HUtils.firstLetterToLowerCase("HELLO WORLD"));
  }

  @Test
  void firstLetterToUpperCase() {
    assertEquals("Hello", HUtils.firstLetterToUpperCase("hello"));
    assertEquals("Hello World", HUtils.firstLetterToUpperCase("hello World"));
  }

  @Test
  void removeNumbers() {
    assertEquals("AB", HUtils.removeNumbers("A1234B"));
    assertEquals("", HUtils.removeNumbers("1234"));
  }

  @Test
  void readProperties() throws IOException {
    Properties p = HUtils.readProperties("/HUtilsTest.properties");
    assertEquals("Hello", p.getProperty("PROPERTY1"));
    assertEquals("World", p.getProperty("PROPERTY2"));

    try {
      HUtils.readProperties("/HUtilsTest.properties.not_found");
    }
    catch (FileNotFoundException fnfe) {
      assertEquals("file </HUtilsTest.properties.not_found> not found", fnfe.getMessage());
    }
  }
}