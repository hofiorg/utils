import org.hofi.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void cleanUpStreams() {
    System.setOut(null);
  }

  @Test
  void firstLetterToLowerCase() {
    Assertions.assertEquals("hELLO", Utils.firstLetterToLowerCase("HELLO"));
    Assertions.assertEquals("hELLO WORLD", Utils.firstLetterToLowerCase("HELLO WORLD"));
  }

  @Test
  void firstLetterToUpperCase() {
    assertEquals("Hello", Utils.firstLetterToUpperCase("hello"));
    assertEquals("Hello World", Utils.firstLetterToUpperCase("hello World"));
  }

  @Test
  void removeNumbers() {
    assertEquals("AB", Utils.removeNumbers("A1234B"));
    assertEquals("", Utils.removeNumbers("1234"));
  }

  @Test
  void readProperties() throws IOException {
    Properties p = Utils.readProperties("/HUtilsTest.properties");
    assertEquals("Hello", p.getProperty("PROPERTY1"));
    assertEquals("World", p.getProperty("PROPERTY2"));

    try {
      Utils.readProperties("/UtilsTest.properties.not_found");
    }
    catch (FileNotFoundException fnfe) {
      assertEquals("file </UtilsTest.properties.not_found> not found", fnfe.getMessage());
    }
  }

  @Test
  void printAllProperties() {
    Properties p = new Properties();
    p.setProperty("hello", "1");
    p.setProperty("world", "2");
    Utils.printAllProperties(p);

    assertEquals("hello: 1\r\nworld: 2\r\n", outContent.toString());
  }

  @Test
  void removeLastLetterIf() {
    assertEquals("Hello", Utils.removeLastLetterIf("Hellos", 's'));
    assertEquals("Hello", Utils.removeLastLetterIf("Hellox", 'x'));
    assertEquals("Hellso", Utils.removeLastLetterIf("Hellso", 's'));
  }

  @Test
  void printNewlines() {
    Utils.printNewlines(5);
    assertEquals("\r\n\r\n\r\n\r\n\r\n", outContent.toString());
  }
}