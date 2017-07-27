import org.hofi.utils.PropertyUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyUtilsTest {

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
  void readProperties() throws IOException {
    Properties p = PropertyUtils.readProperties("/HUtilsTest.properties");
    assertEquals("Hello", p.getProperty("PROPERTY1"));
    assertEquals("World", p.getProperty("PROPERTY2"));

    try {
      PropertyUtils.readProperties("/UtilsTest.properties.not_found");
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
    PropertyUtils.printAllProperties(p);

    assertEquals("hello: 1\r\nworld: 2\r\n", outContent.toString());
  }
}
