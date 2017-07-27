import org.hofi.utils.TemplateSupplant;
import org.hofi.utils.Utils;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class TemplateSupplantTest {

  private final static String TEMPLATE_FILE = "src/test/resources/HTemplateSupplantTestTemplate.java.template";

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
  void replace() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE);
    supplant.replace("CLASSNAME", "HelloWorld");
    supplant.replace("TEXT", "World");
    String s = supplant.get();
    assertEquals(true, s.contains("HelloWorld"));
    assertEquals(true, s.contains("World"));
    assertEquals(false, s.contains("CLASSNAME"));
    assertEquals(false, s.contains("TEXT"));
  }

  @Test
  void replaceNoTrim() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE, 6);
    supplant.replaceNoTrim("TEXT", "   World   ");
    String s = supplant.get();
    assertEquals(true, s.contains("   World   "));
    assertEquals(false, s.contains("TEXT"));
  }

  @Test
  void out() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE, 6);
    supplant.replaceNoTrim("TEXT", "   World   ");
    supplant.out();
    assertEquals("  System.out.println(\"Hello    World   \");\r\n", outContent.toString());
  }

  @Test
  void replace_StringBuilder() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE);
    supplant.replace("CLASSNAME", new StringBuilder("HelloWorld"));
    supplant.replace("TEXT", new StringBuilder("World"));
    String s = supplant.get();
    assertEquals(true, s.contains("HelloWorld"));
    assertEquals(true, s.contains("World"));
    assertEquals(false, s.contains("CLASSNAME"));
    assertEquals(false, s.contains("TEXT"));
  }

  @Test
  void getNL() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE, 5, 8);
    supplant.replace("TEXT", "   World   ");
    String s = supplant.getNL();
    assertEquals(true, s.endsWith("\n"));
  }

  @Test
  void tagNotFound() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE, 5, 8);
    try {
      supplant.replace("TEXT2", "   World   ");
    } catch(IllegalArgumentException iae) {
      assertEquals("tag <TEXT2> not found", iae.getMessage());
    }
  }

  @Test
  void write() throws IOException {
    write("Test1.java");
    write("Test2.java");
  }

  private void write(String filename) throws IOException {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE, 6);
    supplant.write(filename);
    String newFile = Utils.readFile(filename, Charset.forName("UTF-8"));
    assertEquals("  System.out.println(\"Hello %TEXT%\");\r\n", newFile);
    Utils.deleteFile(filename);
  }

  @Test
  void replaceWithDollarSign() {
    TemplateSupplant supplant = new TemplateSupplant(TEMPLATE_FILE);
    supplant.replace("CLASSNAME", "Hello$World");
    String s = supplant.get();
    assertEquals(true, s.contains("Hello$World"));
    assertEquals(false, s.contains("CLASSNAME"));
  }
}