import org.hofi.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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