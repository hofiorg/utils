import org.hofi.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

  @Test
  void readWriteDeleteFile() throws IOException {
    FileUtils.writeFile("readWriteFile.test", "helloWorld");
    String s = FileUtils.readFile("readWriteFile.test");
    assertEquals("helloWorld", s);
    FileUtils.deleteFile("readWriteFile.test");
  }
}