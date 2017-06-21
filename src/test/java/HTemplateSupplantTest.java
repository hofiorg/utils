import org.hofi.HTemplateSupplant;
import org.hofi.HUtils;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class HTemplateSupplantTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  @Test
  void replace1() {
    HTemplateSupplant supplant = new HTemplateSupplant("src/test/resources/HTemplateSupplantTestTemplate.java.template");
    supplant.replace("CLASSNAME", "HelloWorld");
    supplant.replace("TEXT", "World");
    String s = supplant.get();
    assertEquals(true, s.contains("HelloWorld"));
    assertEquals(true, s.contains("World"));
    assertEquals(false, s.contains("CLASSNAME"));
    assertEquals(false, s.contains("TEXT"));
  }

  @Test
  void replace2() {
    HTemplateSupplant supplant = new HTemplateSupplant("src/test/resources/HTemplateSupplantTestTemplate.java.template", 6);
    supplant.replaceNoTrim("TEXT", "   World   ");
    String s = supplant.get();
    assertEquals(true, s.contains("   World   "));
    assertEquals(false, s.contains("TEXT"));

    supplant.out();
    assertEquals("  System.out.println(\"Hello    World   \");\r\n", outContent.toString());
  }

  @Test
  void replace3() {
    HTemplateSupplant supplant = new HTemplateSupplant("src/test/resources/HTemplateSupplantTestTemplate.java.template", 5, 8);
    StringBuilder sb = new StringBuilder("   World   ");
    supplant.replace("TEXT", sb);
    try {
      supplant.replace("TEXT2", sb);
    } catch(IllegalArgumentException iae) {
      assertEquals("tag <TEXT2> not found", iae.getMessage());
    }
    String s = supplant.get();
    assertEquals(true, s.contains("World"));
    assertEquals(false, s.contains("   World   "));
    assertEquals(false, s.contains("TEXT"));

    s = supplant.getNL();
    assertEquals(true, s.endsWith("\n"));
  }

  @Test
  void write() throws IOException {
    HTemplateSupplant supplant = new HTemplateSupplant("src/test/resources/HTemplateSupplantTestTemplate.java.template", 6);
    supplant.write("Test.java");
    String newFile = HUtils.readFile("Test.java", Charset.forName("UTF-8"));
    assertEquals("  System.out.println(\"Hello %TEXT%\");\r\n", newFile);
    HUtils.deleteFile("Test.java");

    supplant.write("Test2.java");
    newFile = HUtils.readFile("Test2.java", Charset.forName("UTF-8"));
    assertEquals("  System.out.println(\"Hello %TEXT%\");\r\n", newFile);
    HUtils.deleteFile("Test2.java");
  }
}