package club.myfpl;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * User: Saket
 * Date: 09/02/17
 * Time: 1:04 PM
 */
public class ExampleTest {
    @Test
    public void awsTest() throws Exception {
        String key = "AKIAIXTXOXU4IPTQQTAA";
        String secret = "HA3ibjIIdieL+sCqq70ZkFvAusHzqyXZ7vW8FhrJ";
    }

    @Test
    public void base64() throws Exception {
        boolean result = Base64.isBase64("LW81Y3Nnajc5MmRl");
    }
}