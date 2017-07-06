import org.hofi.HUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class HUtilsTest {

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

  @Test
  void printAllProperties() {
    Properties p = new Properties();
    p.setProperty("hello", "1");
    p.setProperty("world", "2");
    HUtils.printAllProperties(p);

    assertEquals("hello: 1\r\nworld: 2\r\n", outContent.toString());
  }

  @Test
  void removeLastLetterIf() {
    assertEquals("Hello", HUtils.removeLastLetterIf("Hellos", 's'));
    assertEquals("Hellso", HUtils.removeLastLetterIf("Hellso", 's'));
  }
}