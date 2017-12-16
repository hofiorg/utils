import org.hofi.utils.HttpUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilsTest {

  @org.junit.jupiter.api.Test
  void parseResponse() {
    assertEquals("123", HttpUtils.parseResponse("hello123world", "hello", "world"));
  }

  /* FIXME: connection problems on jenkins (proxy?)
  @org.junit.jupiter.api.Test
  void readHTTPResponseToString() throws IOException {

    String s = HttpUtils.readHTTPResponseToString("http://www.google.de");
    assertEquals(true, s.contains("google"));
  }*/
}